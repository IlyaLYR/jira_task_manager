package com.vsu.odinaev.service;

import com.vsu.odinaev.entity.Project;
import com.vsu.odinaev.entity.User;
import com.vsu.odinaev.mapper.ProjectMapper;
import com.vsu.odinaev.model.ProjectResponse;
import com.vsu.odinaev.model.ProjectsPostRequest;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

/**
 * Сервис управления проектами.
 *
 * <p>Предоставляет операции получения списка проектов и создания нового проекта.
 * Владельцем нового проекта автоматически становится текущий аутентифицированный
 * пользователь, логин которого передаётся из слоя ресурса.</p>
 */
@ApplicationScoped
@Transactional
public class ProjectService {

    @Inject
    ProjectMapper projectMapper;

    /**
     * Возвращает список всех проектов, отсортированных по дате создания (от старых к новым).
     *
     * @return список DTO проектов
     */
    public List<ProjectResponse> getProjects() {
        return projectMapper.toResponseList(
                Project.findAll(Sort.by("createdAt")).list()
        );
    }

    /**
     * Создаёт новый проект и назначает владельца по логину.
     *
     * @param request    данные нового проекта (название, описание)
     * @param ownerLogin логин текущего пользователя, извлечённый из JWT
     * @return DTO созданного проекта
     * @throws jakarta.ws.rs.NotFoundException если пользователь с указанным логином не найден
     */
    public ProjectResponse createProject(ProjectsPostRequest request, String ownerLogin) {
        User owner = User.find("login", ownerLogin).firstResult();
        if (owner == null) {
            throw new NotFoundException("User not found");
        }
        Project project = projectMapper.toEntity(request);
        project.owner = owner;
        project.persist();
        return projectMapper.toResponse(project);
    }
}
