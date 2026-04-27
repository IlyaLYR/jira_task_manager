package com.vsu.odinaev.model;

import java.util.Objects;

/**
 * Тело запроса для частичного обновления задачи (PATCH-семантика).
 *
 * <p>Используется в эндпоинте {@code PATCH /v1/tasks/{taskId}}.
 * Все поля необязательны: {@code null}-значения не перезаписывают существующие данные.</p>
 */
public class UpdateTaskRequest {

    /**
     * Новый заголовок задачи. {@code null} — без изменений.
     */
    private String title;
    /**
     * Новое описание задачи. {@code null} — без изменений.
     */
    private String description;
    /**
     * UUID нового исполнителя. {@code null} — без изменений.
     */
    private String assigneeId;

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
        UpdateTaskRequest that = (UpdateTaskRequest) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(description, that.description) &&
                Objects.equals(assigneeId, that.assigneeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, assigneeId);
    }

    @Override
    public String toString() {
        return "UpdateTaskRequest{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", assigneeId='" + assigneeId + '\'' +
                '}';
    }
}