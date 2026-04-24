package com.vsu.odinaev.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.io.Serializable;
import java.util.Objects;


@JsonTypeName("PaginatedTasksResponse_meta")
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2026-04-24T17:54:57.038006426+03:00[Europe/Moscow]", comments = "Generator version: 7.7.0")
public class PaginatedTasksResponseMeta  implements Serializable {
  private Integer page;
  private Integer limit;
  private Integer total;

  /**
   **/
  public PaginatedTasksResponseMeta page(Integer page) {
    this.page = page;
    return this;
  }

  
  @JsonProperty("page")
  public Integer getPage() {
    return page;
  }

  @JsonProperty("page")
  public void setPage(Integer page) {
    this.page = page;
  }

  /**
   **/
  public PaginatedTasksResponseMeta limit(Integer limit) {
    this.limit = limit;
    return this;
  }

  
  @JsonProperty("limit")
  public Integer getLimit() {
    return limit;
  }

  @JsonProperty("limit")
  public void setLimit(Integer limit) {
    this.limit = limit;
  }

  /**
   **/
  public PaginatedTasksResponseMeta total(Integer total) {
    this.total = total;
    return this;
  }

  
  @JsonProperty("total")
  public Integer getTotal() {
    return total;
  }

  @JsonProperty("total")
  public void setTotal(Integer total) {
    this.total = total;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PaginatedTasksResponseMeta paginatedTasksResponseMeta = (PaginatedTasksResponseMeta) o;
    return Objects.equals(this.page, paginatedTasksResponseMeta.page) &&
        Objects.equals(this.limit, paginatedTasksResponseMeta.limit) &&
        Objects.equals(this.total, paginatedTasksResponseMeta.total);
  }

  @Override
  public int hashCode() {
    return Objects.hash(page, limit, total);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PaginatedTasksResponseMeta {\n");
    
    sb.append("    page: ").append(toIndentedString(page)).append("\n");
    sb.append("    limit: ").append(toIndentedString(limit)).append("\n");
    sb.append("    total: ").append(toIndentedString(total)).append("\n");
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

