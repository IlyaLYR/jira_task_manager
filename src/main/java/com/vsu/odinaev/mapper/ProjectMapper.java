package com.vsu.odinaev.mapper;

import com.vsu.odinaev.entity.Project;
import com.vsu.odinaev.model.ProjectResponse;
import com.vsu.odinaev.model.ProjectsPostRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * MapStruct-маппер для преобразования проектов между слоями.
 *
 * <p>Является CDI-бином ({@code componentModel = "cdi"}) — внедряется через {@code @Inject}.</p>
 */
@Mapper(componentModel = "cdi")
public interface ProjectMapper {

    /**
     * Преобразует сущность {@link com.vsu.odinaev.entity.Project} в DTO ответа.
     *
     * @param project сущность проекта
     * @return DTO проекта
     */
    ProjectResponse toResponse(Project project);

    /**
     * Преобразует список сущностей в список DTO.
     *
     * @param projects список проектов
     * @return список DTO проектов
     */
    List<ProjectResponse> toResponseList(List<Project> projects);

    /**
     * Создаёт новую сущность {@link com.vsu.odinaev.entity.Project} из запроса на создание.
     *
     * <p>Поля {@code id}, {@code owner}, {@code createdAt}, {@code updatedAt},
     * {@code tasks}, {@code members} игнорируются — их устанавливает сервис.</p>
     *
     * @param request запрос с данными нового проекта
     * @return частично заполненная сущность проекта
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "tasks", ignore = true)
    @Mapping(target = "members", ignore = true)
    Project toEntity(ProjectsPostRequest request);
}