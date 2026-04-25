package com.vsu.odinaev.model;

import java.util.Objects;

public class UpdateTaskRequest {

    private String title;
    private String description;
    private String assigneeId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(String assigneeId) {
        this.assigneeId = assigneeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateTaskRequest that = (UpdateTaskRequest) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(description, that.description) &&
                Objects.equals(assigneeId, that.assigneeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, assigneeId);
    }

    @Override
    public String toString() {
        return "UpdateTaskRequest{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", assigneeId='" + assigneeId + '\'' +
                '}';
    }
}