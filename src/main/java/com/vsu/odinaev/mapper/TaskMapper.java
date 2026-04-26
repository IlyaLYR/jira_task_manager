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

@Mapper(componentModel = "cdi")
public interface TaskMapper {

    // ========================
    // Entity -> Response
    // ========================

    @Mapping(target = "authorId", source = "author.id")
    @Mapping(target = "assigneeId", source = "assignee.id")
    @Mapping(target = "projectId", source = "project.id")
    TaskResponse toResponse(Task task);

    List<TaskResponse> toResponseList(List<Task> tasks);

    // ========================
    // CreateRequest -> Entity
    // ========================

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "number", ignore = true)
    @Mapping(target = "status", constant = "TODO")
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "assignee", ignore = true)
    @Mapping(target = "project", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Task toEntity(CreateTaskRequest request);

    // ========================
    // UpdateRequest -> Entity (PATCH)
    // ========================

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

    default OffsetDateTime map(Instant value) {
        return value == null ? null : value.atOffset(ZoneOffset.UTC);
    }

    default Instant map(OffsetDateTime value) {
        return value == null ? null : value.toInstant();
    }

    @Mapping(target = "removeItemsItem", ignore = true)
    @Mapping(target = "items", source = "tasks")
    @Mapping(target = "meta", expression = "java(createMeta(page, limit, total))")
    PaginatedTasksResponse toResponse(
            List<Task> tasks,
            int page,
            int limit,
            long total
    );

    default PaginatedTasksResponse.Meta createMeta(int page, int limit, long total) {
        PaginatedTasksResponse.Meta meta = new PaginatedTasksResponse.Meta();
        meta.setPage(page);
        meta.setLimit(limit);
        meta.setTotal(total);
        return meta;
    }
}