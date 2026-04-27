package com.vsu.odinaev.model;

import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * Тело запроса для смены статуса задачи.
 *
 * <p>Используется в эндпоинте {@code POST /v1/tasks/{taskId}/status}.</p>
 */
public class TasksTaskIdStatusPostRequest {

    /**
     * Новый статус задачи. Обязательное поле.
     */
    @NotNull
    private TaskStatus status;

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TasksTaskIdStatusPostRequest that = (TasksTaskIdStatusPostRequest) o;
        return status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(status);
    }

    @Override
    public String toString() {
        return "TasksTaskIdStatusPostRequest{" +
                "status=" + status +
                '}';
    }
}