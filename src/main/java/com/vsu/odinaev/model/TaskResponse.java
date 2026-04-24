package com.vsu.odinaev.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;


@JsonTypeName("TaskResponse")
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2026-04-24T17:54:57.038006426+03:00[Europe/Moscow]", comments = "Generator version: 7.7.0")
public class TaskResponse  implements Serializable {
  private String id;
  private String number;
  private String title;
  private String description;
  private TaskStatus status;
  private String authorId;
  private String assigneeId;
  private String projectId;
  private OffsetDateTime createdAt;
  private OffsetDateTime updatedAt;

  /**
   **/
  public TaskResponse id(String id) {
    this.id = id;
    return this;
  }

  
  @JsonProperty("id")
  @NotNull public String getId() {
    return id;
  }

  @JsonProperty("id")
  public void setId(String id) {
    this.id = id;
  }

  /**
   **/
  public TaskResponse number(String number) {
    this.number = number;
    return this;
  }

  
  @JsonProperty("number")
  @NotNull public String getNumber() {
    return number;
  }

  @JsonProperty("number")
  public void setNumber(String number) {
    this.number = number;
  }

  /**
   **/
  public TaskResponse title(String title) {
    this.title = title;
    return this;
  }

  
  @JsonProperty("title")
  @NotNull public String getTitle() {
    return title;
  }

  @JsonProperty("title")
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   **/
  public TaskResponse description(String description) {
    this.description = description;
    return this;
  }

  
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  @JsonProperty("description")
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   **/
  public TaskResponse status(TaskStatus status) {
    this.status = status;
    return this;
  }

  
  @JsonProperty("status")
  @NotNull public TaskStatus getStatus() {
    return status;
  }

  @JsonProperty("status")
  public void setStatus(TaskStatus status) {
    this.status = status;
  }

  /**
   **/
  public TaskResponse authorId(String authorId) {
    this.authorId = authorId;
    return this;
  }

  
  @JsonProperty("authorId")
  @NotNull public String getAuthorId() {
    return authorId;
  }

  @JsonProperty("authorId")
  public void setAuthorId(String authorId) {
    this.authorId = authorId;
  }

  /**
   **/
  public TaskResponse assigneeId(String assigneeId) {
    this.assigneeId = assigneeId;
    return this;
  }

  
  @JsonProperty("assigneeId")
  public String getAssigneeId() {
    return assigneeId;
  }

  @JsonProperty("assigneeId")
  public void setAssigneeId(String assigneeId) {
    this.assigneeId = assigneeId;
  }

  /**
   **/
  public TaskResponse projectId(String projectId) {
    this.projectId = projectId;
    return this;
  }

  
  @JsonProperty("projectId")
  @NotNull public String getProjectId() {
    return projectId;
  }

  @JsonProperty("projectId")
  public void setProjectId(String projectId) {
    this.projectId = projectId;
  }

  /**
   **/
  public TaskResponse createdAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  
  @JsonProperty("createdAt")
  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  @JsonProperty("createdAt")
  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }

  /**
   **/
  public TaskResponse updatedAt(OffsetDateTime updatedAt) {
    this.updatedAt = updatedAt;
    return this;
  }

  
  @JsonProperty("updatedAt")
  public OffsetDateTime getUpdatedAt() {
    return updatedAt;
  }

  @JsonProperty("updatedAt")
  public void setUpdatedAt(OffsetDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TaskResponse taskResponse = (TaskResponse) o;
    return Objects.equals(this.id, taskResponse.id) &&
        Objects.equals(this.number, taskResponse.number) &&
        Objects.equals(this.title, taskResponse.title) &&
        Objects.equals(this.description, taskResponse.description) &&
        Objects.equals(this.status, taskResponse.status) &&
        Objects.equals(this.authorId, taskResponse.authorId) &&
        Objects.equals(this.assigneeId, taskResponse.assigneeId) &&
        Objects.equals(this.projectId, taskResponse.projectId) &&
        Objects.equals(this.createdAt, taskResponse.createdAt) &&
        Objects.equals(this.updatedAt, taskResponse.updatedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, number, title, description, status, authorId, assigneeId, projectId, createdAt, updatedAt);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TaskResponse {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    number: ").append(toIndentedString(number)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    authorId: ").append(toIndentedString(authorId)).append("\n");
    sb.append("    assigneeId: ").append(toIndentedString(assigneeId)).append("\n");
    sb.append("    projectId: ").append(toIndentedString(projectId)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    updatedAt: ").append(toIndentedString(updatedAt)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }


}

