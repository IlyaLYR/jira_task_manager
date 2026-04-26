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

import java.util.List;
import java.util.UUID;

@ApplicationScoped
@Transactional
public class TaskService {

    @Inject
    TaskMapper taskMapper;

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

    public TaskResponse getTaskById(String taskId) {
        Task task = Task.findById(parseUuid(taskId));
        if (task == null) {
            throw new NotFoundException("Task not found");
        }
        return taskMapper.toResponse(task);
    }

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

    public TaskResponse changeStatus(String taskId, TaskStatus newStatus) {
        Task task = Task.findById(parseUuid(taskId));
        if (task == null) {
            throw new NotFoundException("Task not found");
        }
        task.status = newStatus;
        return taskMapper.toResponse(task);
    }

    private String nextNumber(Project project) {
        long count = Task.count("project.id = ?1", project.id);
        return String.valueOf(count + 1);
    }

    private UUID parseUuid(String value) {
        try {
            return UUID.fromString(value);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Invalid UUID: " + value);
        }
    }
}
