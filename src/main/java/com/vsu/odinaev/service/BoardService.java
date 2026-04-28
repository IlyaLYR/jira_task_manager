package com.vsu.odinaev.service;

import com.vsu.odinaev.entity.Project;
import com.vsu.odinaev.entity.Task;
import com.vsu.odinaev.mapper.BoardMapper;
import com.vsu.odinaev.mapper.TaskMapper;
import com.vsu.odinaev.model.BoardResponse;
import com.vsu.odinaev.model.TaskResponse;
import com.vsu.odinaev.model.TaskStatus;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import org.jspecify.annotations.NonNull;

import java.util.*;

/**
 * Сервис для работы с доской задач (Kanban-доской) проекта.
 *
 * <p>Формирует представление доски: задачи проекта группируются по статусам
 * и возвращаются в виде колонок. Порядок колонок соответствует порядку
 * значений в перечислении {@link com.vsu.odinaev.model.TaskStatus}, а задачи
 * внутри каждой колонки отсортированы по номеру ({@code number}).</p>
 */
@ApplicationScoped
@Transactional
public class BoardService {

    @Inject
    TaskMapper taskMapper;

    @Inject
    BoardMapper boardMapper;

    /**
     * Возвращает доску задач по строковому идентификатору проекта.
     *
     * @param projectId UUID проекта в виде строки; не может быть {@code null}
     * @return DTO доски с задачами, сгруппированными по статусам
     * @throws jakarta.ws.rs.BadRequestException если {@code projectId} равен {@code null} или не является валидным UUID
     * @throws jakarta.ws.rs.NotFoundException   если проект не найден
     */
    public BoardResponse getBoardByProjectId(String projectId) {
        if (projectId == null) {
            throw new BadRequestException("projectId is required");
        }
        return buildBoard(parseUuid(projectId));
    }

    /**
     * Возвращает доску задач по строковому идентификатору доски/проекта.
     *
     * @param id UUID проекта в виде строки
     * @return DTO доски с задачами, сгруппированными по статусам
     * @throws jakarta.ws.rs.BadRequestException если {@code id} не является валидным UUID
     * @throws jakarta.ws.rs.NotFoundException   если проект не найден
     */
    public BoardResponse getBoardById(String id) {
        return buildBoard(parseUuid(id));
    }

    /**
     * Загружает проект и его задачи из БД, группирует задачи по статусам.
     *
     * @param projectId UUID проекта
     * @return заполненный DTO доски
     */
    private BoardResponse buildBoard(UUID projectId) {
        Project project = Project.findById(projectId);
        if (project == null) {
            throw new NotFoundException("Project not found");
        }

        List<Task> tasks = Task.find(
                "project.id = ?1", Sort.by("number"), projectId
        ).list();

        Map<String, List<TaskResponse>> columns = new LinkedHashMap<>();
        for (TaskStatus status : TaskStatus.values()) {
            columns.put(status.name(), new ArrayList<>());
        }
        tasks.forEach(t -> columns.get(t.status.name())
                .add(taskMapper.toResponse(t)));

        return boardMapper.toResponse(project, columns);
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
