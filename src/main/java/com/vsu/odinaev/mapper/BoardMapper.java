package com.vsu.odinaev.mapper;

import com.vsu.odinaev.entity.Project;
import com.vsu.odinaev.model.BoardResponse;
import com.vsu.odinaev.model.TaskResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Map;

/**
 * MapStruct-маппер для формирования DTO доски задач.
 *
 * <p>Является CDI-бином ({@code componentModel = "cdi"}) — внедряется через {@code @Inject}.</p>
 */
@Mapper(componentModel = "cdi")
public interface BoardMapper {

    /**
     * Создаёт DTO доски задач из сущности проекта и сгруппированных по статусу задач.
     *
     * @param project проект, которому принадлежит доска
     * @param columns колонки доски: ключ — имя статуса, значение — список DTO задач
     * @return заполненный DTO доски
     */
    @Mapping(target = "projectId", source = "project.id")
    @Mapping(target = "columns", source = "columns")
    BoardResponse toResponse(Project project, Map<String, List<TaskResponse>> columns);
}
