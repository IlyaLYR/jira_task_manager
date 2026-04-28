package com.vsu.odinaev.service;

import com.vsu.odinaev.entity.Project;
import com.vsu.odinaev.entity.Task;
import com.vsu.odinaev.entity.User;
import com.vsu.odinaev.mapper.TaskMapper;
import com.vsu.odinaev.model.*;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.UUID;

/**
 * Сервис управления задачами.
 *
 * <p>Реализует полный CRUD для задач: постраничный список с фильтрацией,
 * создание, получение по ID, частичное обновление (PATCH) и смену статуса.</p>
 *
 * <p>При создании задачи номер ({@code number}) генерируется автоматически —
 * как количество задач в проекте + 1. Автором задачи становится текущий
 * аутентифицированный пользователь.</p>
 */
@ApplicationScoped
@Transactional
public class TaskService {

    @Inject
    TaskMapper taskMapper;

    /**
     * Возвращает постраничный список задач с необязательной фильтрацией.
     *
     * <p>Задачи сортируются по дате создания. Страницы индексируются с 1.</p>
     *
     * @param page      номер страницы (начиная с 1)
     * @param limit     максимальное количество задач на странице
     * @param projectId UUID проекта для фильтрации; {@code null} — без фильтра
     * @param status    статус задачи для фильтрации; {@code null} — без фильтра
     * @return DTO с задачами текущей страницы и метаданными пагинации
     */
    public PaginatedTasksResponse getTasks(int page, int limit, String projectId, TaskStatus status) {
        Sort sort = Sort.by("createdAt");
        PanacheQuery<Task> query;

        if (projectId != null && status != null) {
            UUID pid = parseUuid(projectId);
            query = Task.find("project.id = ?1 and status = ?2", sort, pid, status);
        } else if (projectId != null) {
            UUID pid = parseUuid(projectId);
            query = Task.find("project.id = ?1", sort, pid);
        } else if (status != null) {
            query = Task.find("status = ?1", sort, status);
        } else {
            query = Task.findAll(sort);
        }

        long total = query.count();
        List<Task> tasks = query.page(page - 1, limit).list();
        return taskMapper.toResponse(tasks, page, limit, total);
    }

    /**
     * Создаёт новую задачу в указанном проекте.
     *
     * @param request     данные новой задачи (заголовок, описание, projectId, опциональный assigneeId)
     * @param authorLogin логин текущего пользователя, извлечённый из JWT
     * @return DTO созданной задачи
     * @throws jakarta.ws.rs.NotFoundException   если автор, проект или исполнитель не найдены
     * @throws jakarta.ws.rs.BadRequestException если UUID имеет неверный формат
     */
    public TaskResponse createTask(CreateTaskRequest request, String authorLogin) {
        User author = User.find("login", authorLogin).firstResult();
        if (author == null) {
            throw new NotFoundException("User not found");
        }

        Project project = Project.findById(parseUuid(request.getProjectId()));
        if (project == null) {
            throw new NotFoundException("Project not found");
        }

        User assignee = null;
        if (request.getAssigneeId() != null) {
            assignee = User.findById(parseUuid(request.getAssigneeId()));
            if (assignee == null) {
                throw new NotFoundException("Assignee not found");
            }
        }

        Task task = taskMapper.toEntity(request);
        task.author = author;
        task.project = project;
        task.assignee = assignee;
        task.number = nextNumber(project);
        task.persist();

        return taskMapper.toResponse(task);
    }

    /**
     * Возвращает задачу по её UUID.
     *
     * @param taskId UUID задачи в виде строки
     * @return DTO задачи
     * @throws jakarta.ws.rs.NotFoundException   если задача не найдена
     * @throws jakarta.ws.rs.BadRequestException если UUID имеет неверный формат
     */
    public TaskResponse getTaskById(String taskId) {
        Task task = Task.findById(parseUuid(taskId));
        if (task == null) {
            throw new NotFoundException("Task not found");
        }
        return taskMapper.toResponse(task);
    }

    /**
     * Частично обновляет задачу (PATCH-семантика).
     *
     * <p>Поля с {@code null} в запросе не перезаписываются
     * (стратегия {@code NullValuePropertyMappingStrategy.IGNORE} в маппере).</p>
     *
     * @param taskId  UUID задачи в виде строки
     * @param request объект с полями для обновления (все поля необязательны)
     * @return DTO обновлённой задачи
     * @throws jakarta.ws.rs.NotFoundException   если задача или новый исполнитель не найдены
     * @throws jakarta.ws.rs.BadRequestException если UUID имеет неверный формат
     */
    public TaskResponse updateTask(String taskId, UpdateTaskRequest request) {
        Task task = Task.findById(parseUuid(taskId));
        if (task == null) {
            throw new NotFoundException("Task not found");
        }

        taskMapper.update(task, request);

        if (request.getAssigneeId() != null) {
            User assignee = User.findById(parseUuid(request.getAssigneeId()));
            if (assignee == null) {
                throw new NotFoundException("Assignee not found");
            }
            task.assignee = assignee;
        }

        return taskMapper.toResponse(task);
    }

    /**
     * Устанавливает новый статус задачи.
     *
     * @param taskId    UUID задачи в виде строки
     * @param newStatus новый статус из перечисления {@link com.vsu.odinaev.model.TaskStatus}
     * @return DTO задачи с обновлённым статусом
     * @throws jakarta.ws.rs.NotFoundException   если задача не найдена
     * @throws jakarta.ws.rs.BadRequestException если UUID имеет неверный формат
     */
    public TaskResponse changeStatus(String taskId, TaskStatus newStatus) {
        Task task = Task.findById(parseUuid(taskId));
        if (task == null) {
            throw new NotFoundException("Task not found");
        }
        task.status = newStatus;
        return taskMapper.toResponse(task);
    }

    /**
     * Вычисляет следующий порядковый номер задачи для данного проекта.
     *
     * @param project проект, в котором создаётся задача
     * @return строка с номером (текущее количество задач + 1)
     */
    private @NonNull String nextNumber(@NonNull Project project) {
        long count = Task.count("project.id = ?1", project.id);
        return String.valueOf(count + 1);
    }

    /**
     * Преобразует строку в UUID, выбрасывая 400 при некорректном формате.
     *
     * @param value строковое представление UUID
     * @return разобранный {@link UUID}
     * @throws jakarta.ws.rs.BadRequestException если строка не является валидным UUID
     */
    private @NonNull UUID parseUuid(String value) {
        try {
            return UUID.fromString(value);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Invalid UUID: " + value);
        }
    }
}
