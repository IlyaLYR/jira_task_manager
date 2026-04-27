package com.vsu.odinaev.mapper;

import com.vsu.odinaev.entity.User;
import com.vsu.odinaev.model.UserResponse;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * MapStruct-маппер для преобразования пользователей между слоями.
 *
 * <p>Является CDI-бином ({@code componentModel = "cdi"}) — внедряется через {@code @Inject}.</p>
 */
@Mapper(componentModel = "cdi")
public interface UserMapper {

    /**
     * Преобразует сущность {@link com.vsu.odinaev.entity.User} в DTO ответа.
     *
     * @param user сущность пользователя
     * @return DTO пользователя (без хэша пароля)
     */
    UserResponse toResponse(User user);

    /**
     * Преобразует список сущностей в список DTO.
     *
     * @param users список пользователей
     * @return список DTO пользователей
     */
    List<UserResponse> toResponseList(List<User> users);
}