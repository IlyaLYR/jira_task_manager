package com.vsu.odinaev.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Objects;


@JsonTypeName("UserResponse")
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2026-04-24T17:54:57.038006426+03:00[Europe/Moscow]", comments = "Generator version: 7.7.0")
public class UserResponse  implements Serializable {
  private String id;
  private String login;

  /**
   **/
  public UserResponse id(String id) {
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
  public UserResponse login(String login) {
    this.login = login;
    return this;
  }

  
  @JsonProperty("login")
  @NotNull public String getLogin() {
    return login;
  }

  @JsonProperty("login")
  public void setLogin(String login) {
    this.login = login;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserResponse userResponse = (UserResponse) o;
    return Objects.equals(this.id, userResponse.id) &&
        Objects.equals(this.login, userResponse.login);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, login);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserResponse {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    login: ").append(toIndentedString(login)).append("\n");
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

