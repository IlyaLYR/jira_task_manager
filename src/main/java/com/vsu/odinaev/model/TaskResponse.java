package com.vsu.odinaev.model;

import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;
import java.util.Objects;

public class TaskResponse {

    @NotNull
    private String id;

    @NotNull
    private String number;

    @NotNull
    private String title;

    private String description;

    @NotNull
    private TaskStatus status;

    @NotNull
    private String authorId;

    private String assigneeId;

    @NotNull
    private String projectId;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

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

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(String assigneeId) {
        this.assigneeId = assigneeId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskResponse that = (TaskResponse) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(number, that.number) &&
                Objects.equals(title, that.title) &&
                Objects.equals(description, that.description) &&
                status == that.status &&
                Objects.equals(authorId, that.authorId) &&
                Objects.equals(assigneeId, that.assigneeId) &&
                Objects.equals(projectId, that.projectId) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, title, description, status, authorId, assigneeId, projectId, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "TaskResponse{" +
                "id='" + id + '\'' +
                ", number='" + number + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", authorId='" + authorId + '\'' +
                ", assigneeId='" + assigneeId + '\'' +
                ", projectId='" + projectId + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}