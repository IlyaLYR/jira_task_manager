package com.vsu.odinaev.mapper;

import com.vsu.odinaev.entity.User;
import com.vsu.odinaev.model.UserResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface UserMapper {

    UserResponse toResponse(User user);

    List<UserResponse> toResponseList(List<User> users);
}