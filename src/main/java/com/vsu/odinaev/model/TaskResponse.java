package com.vsu.odinaev.model;

import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;
import java.util.Objects;

/**
 * DTO задачи для ответов API.
 *
 * <p>Возвращается эндпоинтами {@code GET /v1/tasks}, {@code POST /v1/tasks},
 * {@code GET /v1/tasks/{taskId}}, {@code PATCH /v1/tasks/{taskId}} и
 * {@code POST /v1/tasks/{taskId}/status}.</p>
 *
 * <p>Вложенные сущности (автор, исполнитель, проект) представлены только
 * своими идентификаторами для уменьшения объёма ответа.</p>
 */
public class TaskResponse {

    /**
     * Уникальный идентификатор задачи (UUID в виде строки).
     */
    @NotNull
    private String id;

    /**
     * Порядковый номер задачи в проекте.
     */
    @NotNull
    private String number;

    /**
     * Заголовок задачи.
     */
    @NotNull
    private String title;

    /**
     * Подробное описание задачи. Может быть {@code null}.
     */
    private String description;

    /**
     * Текущий статус задачи.
     */
    @NotNull
    private TaskStatus status;

    /**
     * UUID пользователя, создавшего задачу.
     */
    @NotNull
    private String authorId;

    /**
     * UUID исполнителя задачи. Может быть {@code null}, если не назначен.
     */
    private String assigneeId;

    /**
     * UUID проекта, которому принадлежит задача.
     */
    @NotNull
    private String projectId;

    /**
     * Дата и время создания задачи (UTC).
     */
    private OffsetDateTime createdAt;

    /**
     * Дата и время последнего обновления задачи (UTC).
     */
    private OffsetDateTime updatedAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

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

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(String assigneeId) {
        this.assigneeId = assigneeId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskResponse that = (TaskResponse) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(number, that.number) &&
                Objects.equals(title, that.title) &&
                Objects.equals(description, that.description) &&
                status == that.status &&
                Objects.equals(authorId, that.authorId) &&
                Objects.equals(assigneeId, that.assigneeId) &&
                Objects.equals(projectId, that.projectId) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, title, description, status, authorId, assigneeId, projectId, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "TaskResponse{" +
                "id='" + id + '\'' +
                ", number='" + number + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", authorId='" + authorId + '\'' +
                ", assigneeId='" + assigneeId + '\'' +
                ", projectId='" + projectId + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}