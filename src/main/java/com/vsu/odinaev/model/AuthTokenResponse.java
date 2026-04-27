package com.vsu.odinaev.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * DTO ответа при успешной аутентификации.
 *
 * <p>Возвращается эндпоинтом {@code POST /v1/auth/token}. Содержит JWT-токен,
 * тип токена и время жизни в секундах.</p>
 */
public class AuthTokenResponse {

    /**
     * JWT access-токен для авторизации запросов. Передаётся в заголовке {@code Authorization: Bearer <token>}.
     */
    @JsonProperty("access_token")
    @NotNull
    private String accessToken;

    /**
     * Тип токена. Всегда {@code "Bearer"}.
     */
    @JsonProperty("token_type")
    @NotNull
    private String tokenType;

    /**
     * Время жизни токена в секундах (обычно 3600).
     */
    @JsonProperty("expires_in")
    private Integer expiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthTokenResponse that = (AuthTokenResponse) o;
        return Objects.equals(accessToken, that.accessToken) &&
                Objects.equals(tokenType, that.tokenType) &&
                Objects.equals(expiresIn, that.expiresIn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accessToken, tokenType, expiresIn);
    }

    @Override
    public String toString() {
        return "AuthTokenResponse{" +
                "accessToken='" + accessToken + '\'' +
                ", tokenType='" + tokenType + '\'' +
                ", expiresIn=" + expiresIn +
                '}';
    }
}