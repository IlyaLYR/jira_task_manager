package com.vsu.odinaev.model;

import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * Тело запроса для создания новой задачи.
 *
 * <p>Используется в эндпоинте {@code POST /v1/tasks}.
 * Автором задачи автоматически становится текущий аутентифицированный пользователь.</p>
 */
public class CreateTaskRequest {

    /**
     * Заголовок задачи. Обязательное поле.
     */
    @NotNull
    private String title;

    /**
     * Подробное описание задачи. Необязательное поле.
     */
    private String description;

    /**
     * UUID проекта, в котором создаётся задача. Обязательное поле.
     */
    @NotNull
    private String projectId;

    /**
     * UUID пользователя-исполнителя. Необязательное поле.
     */
    private String assigneeId;

    // Геттеры и сеттеры (без аннотаций, @NotNull уже на полях)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(String assigneeId) {
        this.assigneeId = assigneeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateTaskRequest that = (CreateTaskRequest) o;
        return Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(projectId, that.projectId) && Objects.equals(assigneeId, that.assigneeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, projectId, assigneeId);
    }

    @Override
    public String toString() {
        return "CreateTaskRequest{" + "title='" + title + '\'' + ", description='" + description + '\'' + ", projectId='" + projectId + '\'' + ", assigneeId='" + assigneeId + '\'' + '}';
    }
}