package com.vsu.odinaev.model;

import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * DTO доски задач для ответов API.
 *
 * <p>Возвращается эндпоинтами {@code GET /v1/board} и {@code GET /v1/board/{id}}.
 * Задачи сгруппированы по статусам — каждая колонка соответствует одному статусу
 * из {@link TaskStatus}.</p>
 */
public class BoardResponse {

    /**
     * UUID проекта, которому принадлежит доска.
     */
    private String projectId;

    /**
     * Колонки доски: ключ — имя статуса (например, {@code "TODO"}, {@code "IN_PROGRESS"}),
     * значение — список задач с данным статусом.
     */
    private Map<String, List<@Valid TaskResponse>> columns = new HashMap<>();

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public Map<String, List<@Valid TaskResponse>> getColumns() {
        return columns;
    }

    public void setColumns(Map<String, List<@Valid TaskResponse>> columns) {
        this.columns = columns;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardResponse that = (BoardResponse) o;
        return Objects.equals(projectId, that.projectId) &&
                Objects.equals(columns, that.columns);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, columns);
    }

    @Override
    public String toString() {
        return "BoardResponse{" +
                "projectId='" + projectId + '\'' +
                ", columns=" + columns +
                '}';
    }
}