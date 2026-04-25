package com.vsu.odinaev.model;

import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class BoardResponse {

    private String projectId;

    private Map<String, List<@Valid TaskResponse>> columns = new HashMap<>();

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public Map<String, List<@Valid TaskResponse>> getColumns() {
        return columns;
    }

    public void setColumns(Map<String, List<@Valid TaskResponse>> columns) {
        this.columns = columns;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardResponse that = (BoardResponse) o;
        return Objects.equals(projectId, that.projectId) &&
                Objects.equals(columns, that.columns);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, columns);
    }

    @Override
    public String toString() {
        return "BoardResponse{" +
                "projectId='" + projectId + '\'' +
                ", columns=" + columns +
                '}';
    }
}