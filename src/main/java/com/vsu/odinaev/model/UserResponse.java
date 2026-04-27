package com.vsu.odinaev.model;

import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * DTO пользователя для ответов API.
 *
 * <p>Возвращается эндпоинтом {@code POST /v1/auth/register}.
 * Хэш пароля в ответ не включается.</p>
 */
public class UserResponse {

    /**
     * Уникальный идентификатор пользователя (UUID в виде строки).
     */
    @NotNull
    private String id;

    /**
     * Логин пользователя.
     */
    @NotNull
    private String login;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserResponse that = (UserResponse) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(login, that.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login);
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "id='" + id + '\'' +
                ", login='" + login + '\'' +
                '}';
    }
}