package com.vsu.odinaev.mapper;

import com.vsu.odinaev.entity.Project;
import com.vsu.odinaev.model.BoardResponse;
import com.vsu.odinaev.model.TaskResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Map;

@Mapper(componentModel = "cdi")
public interface BoardMapper {

    @Mapping(target = "projectId", source = "project.id")
    @Mapping(target = "columns", source = "columns")
    BoardResponse toResponse(Project project, Map<String, List<TaskResponse>> columns);
}
