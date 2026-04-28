package com.vsu.odinaev.service;

import com.vsu.odinaev.entity.User;
import com.vsu.odinaev.mapper.UserMapper;
import com.vsu.odinaev.model.AuthTokenResponse;
import com.vsu.odinaev.model.RegisterRequest;
import com.vsu.odinaev.model.UserResponse;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.jspecify.annotations.NonNull;

import java.time.Duration;
import java.util.Set;

/**
 * Сервис аутентификации и регистрации пользователей.
 *
 * <p>Отвечает за создание новых учётных записей и выдачу JWT-токенов.
 * Пароли хранятся в виде bcrypt-хэша. Токен действителен 3600 секунд (1 час).</p>
 */
@ApplicationScoped
@Transactional
public class AuthService {

    @Inject
    UserMapper userMapper;

    /**
     * Регистрирует нового пользователя.
     *
     * @param request данные для регистрации (логин и пароль)
     * @return DTO с информацией о созданном пользователе
     * @throws WebApplicationException со статусом 409, если логин уже занят
     */
    public UserResponse register(@NonNull RegisterRequest request) {
        if (User.count("login", request.getLogin()) > 0) {
            throw new WebApplicationException(Response.Status.CONFLICT);
        }
        User user = new User();
        user.login = request.getLogin();
        user.passwordHash = BcryptUtil.bcryptHash(request.getPassword());
        user.persist();
        return userMapper.toResponse(user);
    }

    /**
     * Проверяет учётные данные и выдаёт JWT-токен.
     *
     * @param username логин пользователя
     * @param password пароль в открытом виде (сравнивается с bcrypt-хэшем)
     * @return DTO с JWT-токеном, типом и временем жизни
     * @throws WebApplicationException со статусом 401, если логин или пароль неверны
     */
    public AuthTokenResponse generateToken(String username, String password) {
        User user = User.find("login", username).firstResult();
        if (user == null || !BcryptUtil.matches(password, user.passwordHash)) {
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        }

        int expiresIn = 3600;
        String token = Jwt.issuer("jira-api")
                .upn(user.login)
                .groups(Set.of("user"))
                .expiresIn(Duration.ofSeconds(expiresIn))
                .sign();

        AuthTokenResponse response = new AuthTokenResponse();
        response.setAccessToken(token);
        response.setTokenType("Bearer");
        response.setExpiresIn(expiresIn);
        return response;
    }
}
