package com.vsu.odinaev.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.validation.Valid;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@JsonTypeName("BoardResponse")
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2026-04-24T17:54:57.038006426+03:00[Europe/Moscow]", comments = "Generator version: 7.7.0")
public class BoardResponse  implements Serializable {
  private String projectId;
  private @Valid Map<String, List<@Valid TaskResponse>> columns = new HashMap<>();

  /**
   **/
  public BoardResponse projectId(String projectId) {
    this.projectId = projectId;
    return this;
  }

  
  @JsonProperty("projectId")
  public String getProjectId() {
    return projectId;
  }

  @JsonProperty("projectId")
  public void setProjectId(String projectId) {
    this.projectId = projectId;
  }

  /**
   **/
  public BoardResponse columns(Map<String, List<@Valid TaskResponse>> columns) {
    this.columns = columns;
    return this;
  }

  
  @JsonProperty("columns")
  @Valid public Map<String, List<@Valid TaskResponse>> getColumns() {
    return columns;
  }

  @JsonProperty("columns")
  public void setColumns(Map<String, List<@Valid TaskResponse>> columns) {
    this.columns = columns;
  }

  public BoardResponse putColumnsItem(String key, List<@Valid TaskResponse> columnsItem) {
    if (this.columns == null) {
      this.columns = new HashMap<>();
    }

    this.columns.put(key, columnsItem);
    return this;
  }

  public BoardResponse removeColumnsItem(String key) {
    if (this.columns != null) {
      this.columns.remove(key);
    }

    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BoardResponse boardResponse = (BoardResponse) o;
    return Objects.equals(this.projectId, boardResponse.projectId) &&
        Objects.equals(this.columns, boardResponse.columns);
  }

  @Override
  public int hashCode() {
    return Objects.hash(projectId, columns);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BoardResponse {\n");
    
    sb.append("    projectId: ").append(toIndentedString(projectId)).append("\n");
    sb.append("    columns: ").append(toIndentedString(columns)).append("\n");
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

