package com.vsu.odinaev.mapper;

import com.vsu.odinaev.entity.Project;
import com.vsu.odinaev.model.ProjectResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface ProjectMapper {

    ProjectResponse toResponse(Project project);

    List<ProjectResponse> toResponseList(List<Project> projects);
}