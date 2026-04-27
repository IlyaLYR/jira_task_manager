package com.vsu.odinaev.model;

import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * Тело запроса для создания нового проекта.
 *
 * <p>Используется в эндпоинте {@code POST /v1/projects}.</p>
 */
public class ProjectsPostRequest {

    /**
     * Название нового проекта. Обязательное поле.
     */
    @NotNull
    private String name;

    /**
     * Описание проекта. Необязательное поле.
     */
    private String description;

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
        ProjectsPostRequest that = (ProjectsPostRequest) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }

    @Override
    public String toString() {
        return "ProjectsPostRequest{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}