package com.vsu.odinaev.api;

import com.vsu.odinaev.model.AuthTokenResponse;
import com.vsu.odinaev.model.RegisterRequest;
import com.vsu.odinaev.model.UserResponse;
import com.vsu.odinaev.service.AuthService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

/**
 * JAX-RS ресурс для аутентификации и регистрации пользователей.
 *
 * <p>Базовый путь: {@code /v1}. Эндпоинты не требуют JWT-токена.</p>
 *
 * <ul>
 *   <li>{@code POST /v1/auth/register} — регистрация нового пользователя</li>
 *   <li>{@code POST /v1/auth/token}    — получение JWT-токена по логину и паролю</li>
 * </ul>
 */
@Path("/v1")
public class AuthResource {

    @Inject
    AuthService authService;

    /**
     * Регистрирует нового пользователя.
     *
     * @param registerRequest тело запроса с логином и паролем (валидируется)
     * @return HTTP 201 с DTO нового пользователя, либо 409 если логин занят
     */
    @Operation(summary = "Регистрация пользователя")
    @APIResponse(responseCode = "201",
            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = UserResponse.class)))
    @APIResponse(responseCode = "409", description = "Login already taken")
    @POST
    @Path("/auth/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response v1AuthRegisterPost(@Valid RegisterRequest registerRequest) {
        return Response.status(Response.Status.CREATED)
                .entity(authService.register(registerRequest))
                .build();
    }

    /**
     * Выдаёт JWT-токен по учётным данным пользователя.
     *
     * <p>Параметры передаются как форма ({@code application/x-www-form-urlencoded}).
     * Параметр {@code grant_type} принимается, но не используется в текущей реализации.</p>
     *
     * @param grantType тип авторизации (ожидается {@code password})
     * @param username  логин пользователя
     * @param password  пароль в открытом виде
     * @return HTTP 200 с JWT-токеном, либо 401 при неверных учётных данных
     */
    @Operation(summary = "Получить JWT токен")
    @APIResponse(responseCode = "200",
            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = AuthTokenResponse.class)))
    @APIResponse(responseCode = "401", description = "Invalid credentials")
    @POST
    @Path("/auth/token")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response v1AuthTokenPost(
            @FormParam("grant_type") String grantType,
            @FormParam("username") String username,
            @FormParam("password") String password) {
        return Response.ok(authService.generateToken(username, password)).build();
    }
}
