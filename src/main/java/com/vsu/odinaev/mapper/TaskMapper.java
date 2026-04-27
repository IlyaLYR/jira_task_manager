package com.vsu.odinaev.mapper;

import com.vsu.odinaev.entity.Task;
import com.vsu.odinaev.model.CreateTaskRequest;
import com.vsu.odinaev.model.PaginatedTasksResponse;
import com.vsu.odinaev.model.TaskResponse;
import com.vsu.odinaev.model.UpdateTaskRequest;
import org.mapstruct.*;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

/**
 * MapStruct-маппер для преобразования задач между слоями.
 *
 * <p>Является CDI-бином ({@code componentModel = "cdi"}) — внедряется через {@code @Inject}.</p>
 *
 * <p>Особенности реализации:</p>
 * <ul>
 *   <li>Преобразование {@link java.time.Instant} ↔ {@link java.time.OffsetDateTime} осуществляется
 *       через методы {@link #map(java.time.Instant)} и {@link #map(java.time.OffsetDateTime)}.</li>
 *   <li>Вложенные сущности ({@code author}, {@code assignee}, {@code project}) не маппятся автоматически —
 *       их устанавливает сервис после вызова маппера.</li>
 *   <li>Метод {@link #update} использует стратегию {@code IGNORE} для {@code null}-полей,
 *       обеспечивая PATCH-семантику.</li>
 * </ul>
 */
@Mapper(componentModel = "cdi")
public interface TaskMapper {

    /**
     * Преобразует сущность {@link Task} в DTO ответа.
     *
     * <p>Извлекает идентификаторы вложенных сущностей: {@code author.id → authorId},
     * {@code assignee.id → assigneeId}, {@code project.id → projectId}.</p>
     *
     * @param task сущность задачи
     * @return DTO задачи
     */
    @Mapping(target = "authorId", source = "author.id")
    @Mapping(target = "assigneeId", source = "assignee.id")
    @Mapping(target = "projectId", source = "project.id")
    TaskResponse toResponse(Task task);

    /**
     * Преобразует список сущностей {@link Task} в список DTO.
     *
     * @param tasks список задач
     * @return список DTO задач
     */
    List<TaskResponse> toResponseList(List<Task> tasks);

    /**
     * Создаёт новую сущность {@link Task} из запроса на создание.
     *
     * <p>Поля {@code id}, {@code number}, {@code author}, {@code assignee},
     * {@code project}, {@code createdAt}, {@code updatedAt} игнорируются —
     * их устанавливает сервис. Статус фиксируется как {@code TODO}.</p>
     *
     * @param request запрос на создание задачи
     * @return частично заполненная сущность задачи
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "number", ignore = true)
    @Mapping(target = "status", constant = "TODO")
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "assignee", ignore = true)
    @Mapping(target = "project", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Task toEntity(CreateTaskRequest request);

    /**
     * Обновляет существующую сущность {@link Task} данными из запроса (PATCH-семантика).
     *
     * <p>Поля с {@code null} в запросе не перезаписываются. Поля {@code id}, {@code number},
     * {@code status}, {@code author}, {@code assignee}, {@code project},
     * {@code createdAt}, {@code updatedAt} игнорируются.</p>
     *
     * @param task    целевая сущность (изменяется на месте)
     * @param request запрос с частичными данными для обновления
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "number", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "assignee", ignore = true)
    @Mapping(target = "project", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void update(@MappingTarget Task task, UpdateTaskRequest request);

    /**
     * Конвертирует {@link Instant} в {@link OffsetDateTime} с часовым поясом UTC.
     *
     * @param value момент времени в UTC
     * @return дата-время со смещением UTC, либо {@code null}
     */
    default OffsetDateTime map(Instant value) {
        return value == null ? null : value.atOffset(ZoneOffset.UTC);
    }

    /**
     * Конвертирует {@link OffsetDateTime} в {@link Instant}.
     *
     * @param value дата-время со смещением
     * @return момент времени, либо {@code null}
     */
    default Instant map(OffsetDateTime value) {
        return value == null ? null : value.toInstant();
    }

    /**
     * Формирует постраничный ответ из списка задач и параметров пагинации.
     *
     * @param tasks список задач текущей страницы
     * @param page  номер страницы
     * @param limit размер страницы
     * @param total общее количество задач, соответствующих фильтру
     * @return DTO с задачами и метаданными пагинации
     */
    @Mapping(target = "removeItemsItem", ignore = true)
    @Mapping(target = "items", source = "tasks")
    @Mapping(target = "meta", expression = "java(createMeta(page, limit, total))")
    PaginatedTasksResponse toResponse(
            List<Task> tasks,
            int page,
            int limit,
            long total
    );

    /**
     * Создаёт объект метаданных пагинации.
     *
     * @param page  номер страницы
     * @param limit размер страницы
     * @param total общее количество записей
     * @return заполненный объект {@link PaginatedTasksResponse.Meta}
     */
    default PaginatedTasksResponse.Meta createMeta(int page, int limit, long total) {
        PaginatedTasksResponse.Meta meta = new PaginatedTasksResponse.Meta();
        meta.setPage(page);
        meta.setLimit(limit);
        meta.setTotal(total);
        return meta;
    }
}