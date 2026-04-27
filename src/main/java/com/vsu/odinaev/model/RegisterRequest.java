package com.vsu.odinaev.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * Тело запроса для регистрации нового пользователя.
 *
 * <p>Используется в эндпоинте {@code POST /v1/auth/register}.</p>
 */
public class RegisterRequest {

    /**
     * Логин нового пользователя. Обязательное поле, должен быть уникальным.
     */
    @NotNull
    private String login;

    /**
     * Пароль в открытом виде. Обязательное поле.
     * Не включается в JSON-ответы (аннотация {@code WRITE_ONLY}).
     */
    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)   // не возвращается в JSON
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisterRequest that = (RegisterRequest) o;
        return Objects.equals(login, that.login) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }

    @Override
    public String toString() {
        // Не включаем пароль в toString!
        return "RegisterRequest{" +
                "login='" + login + '\'' +
                '}';
    }
}