package com.vsu.odinaev.model;

import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * DTO проекта для ответов API.
 *
 * <p>Возвращается эндпоинтами {@code GET /v1/projects} и {@code POST /v1/projects}.</p>
 */
public class ProjectResponse {

    /**
     * Уникальный идентификатор проекта (UUID в виде строки).
     */
    @NotNull
    private String id;

    /**
     * Название проекта.
     */
    @NotNull
    private String name;

    /**
     * Описание проекта. Может быть {@code null}.
     */
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectResponse that = (ProjectResponse) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }

    @Override
    public String toString() {
        return "ProjectResponse{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}