package com.vsu.odinaev.mapper;

import com.vsu.odinaev.entity.Project;
import com.vsu.odinaev.model.ProjectResponse;
import com.vsu.odinaev.model.ProjectsPostRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface ProjectMapper {

    ProjectResponse toResponse(Project project);

    List<ProjectResponse> toResponseList(List<Project> projects);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "tasks", ignore = true)
    @Mapping(target = "members", ignore = true)
    Project toEntity(ProjectsPostRequest request);
}