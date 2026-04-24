package com.vsu.odinaev.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Objects;


@JsonTypeName("_v1_auth_register_post_request")
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2026-04-24T17:54:57.038006426+03:00[Europe/Moscow]", comments = "Generator version: 7.7.0")
public class V1AuthRegisterPostRequest  implements Serializable {
  private String login;
  private String password;

  /**
   **/
  public V1AuthRegisterPostRequest login(String login) {
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

  /**
   **/
  public V1AuthRegisterPostRequest password(String password) {
    this.password = password;
    return this;
  }

  
  @JsonProperty("password")
  @NotNull public String getPassword() {
    return password;
  }

  @JsonProperty("password")
  public void setPassword(String password) {
    this.password = password;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    V1AuthRegisterPostRequest v1AuthRegisterPostRequest = (V1AuthRegisterPostRequest) o;
    return Objects.equals(this.login, v1AuthRegisterPostRequest.login) &&
        Objects.equals(this.password, v1AuthRegisterPostRequest.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(login, password);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class V1AuthRegisterPostRequest {\n");
    
    sb.append("    login: ").append(toIndentedString(login)).append("\n");
    sb.append("    password: ").append("*").append("\n");
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

