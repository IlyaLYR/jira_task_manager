package com.vsu.odinaev.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Objects;


@JsonTypeName("CreateTaskRequest")
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2026-04-24T17:54:57.038006426+03:00[Europe/Moscow]", comments = "Generator version: 7.7.0")
public class CreateTaskRequest  implements Serializable {
  private String title;
  private String description;
  private String projectId;
  private String assigneeId;

  /**
   **/
  public CreateTaskRequest title(String title) {
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
  public CreateTaskRequest description(String description) {
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
  public CreateTaskRequest projectId(String projectId) {
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
  public CreateTaskRequest assigneeId(String assigneeId) {
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


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateTaskRequest createTaskRequest = (CreateTaskRequest) o;
    return Objects.equals(this.title, createTaskRequest.title) &&
        Objects.equals(this.description, createTaskRequest.description) &&
        Objects.equals(this.projectId, createTaskRequest.projectId) &&
        Objects.equals(this.assigneeId, createTaskRequest.assigneeId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, description, projectId, assigneeId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateTaskRequest {\n");
    
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    projectId: ").append(toIndentedString(projectId)).append("\n");
    sb.append("    assigneeId: ").append(toIndentedString(assigneeId)).append("\n");
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

