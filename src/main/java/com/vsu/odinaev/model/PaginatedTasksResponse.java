package com.vsu.odinaev.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@JsonTypeName("PaginatedTasksResponse")
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2026-04-24T17:54:57.038006426+03:00[Europe/Moscow]", comments = "Generator version: 7.7.0")
public class PaginatedTasksResponse  implements Serializable {
  private @Valid List<@Valid TaskResponse> items = new ArrayList<>();
  private PaginatedTasksResponseMeta meta;

  /**
   **/
  public PaginatedTasksResponse items(List<@Valid TaskResponse> items) {
    this.items = items;
    return this;
  }

  
  @JsonProperty("items")
  @NotNull @Valid public List<@Valid TaskResponse> getItems() {
    return items;
  }

  @JsonProperty("items")
  public void setItems(List<@Valid TaskResponse> items) {
    this.items = items;
  }

  public PaginatedTasksResponse addItemsItem(TaskResponse itemsItem) {
    if (this.items == null) {
      this.items = new ArrayList<>();
    }

    this.items.add(itemsItem);
    return this;
  }

  public PaginatedTasksResponse removeItemsItem(TaskResponse itemsItem) {
    if (itemsItem != null && this.items != null) {
      this.items.remove(itemsItem);
    }

    return this;
  }
  /**
   **/
  public PaginatedTasksResponse meta(PaginatedTasksResponseMeta meta) {
    this.meta = meta;
    return this;
  }

  
  @JsonProperty("meta")
  @NotNull @Valid public PaginatedTasksResponseMeta getMeta() {
    return meta;
  }

  @JsonProperty("meta")
  public void setMeta(PaginatedTasksResponseMeta meta) {
    this.meta = meta;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PaginatedTasksResponse paginatedTasksResponse = (PaginatedTasksResponse) o;
    return Objects.equals(this.items, paginatedTasksResponse.items) &&
        Objects.equals(this.meta, paginatedTasksResponse.meta);
  }

  @Override
  public int hashCode() {
    return Objects.hash(items, meta);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PaginatedTasksResponse {\n");
    
    sb.append("    items: ").append(toIndentedString(items)).append("\n");
    sb.append("    meta: ").append(toIndentedString(meta)).append("\n");
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

