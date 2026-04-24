package com.vsu.odinaev.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Objects;


@JsonTypeName("ProjectResponse")
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2026-04-24T17:54:57.038006426+03:00[Europe/Moscow]", comments = "Generator version: 7.7.0")
public class ProjectResponse  implements Serializable {
  private String id;
  private String name;
  private String description;

  /**
   **/
  public ProjectResponse id(String id) {
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
  public ProjectResponse name(String name) {
    this.name = name;
    return this;
  }

  
  @JsonProperty("name")
  @NotNull public String getName() {
    return name;
  }

  @JsonProperty("name")
  public void setName(String name) {
    this.name = name;
  }

  /**
   **/
  public ProjectResponse description(String description) {
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


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProjectResponse projectResponse = (ProjectResponse) o;
    return Objects.equals(this.id, projectResponse.id) &&
        Objects.equals(this.name, projectResponse.name) &&
        Objects.equals(this.description, projectResponse.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProjectResponse {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
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

