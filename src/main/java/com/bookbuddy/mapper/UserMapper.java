package com.bookbuddy.mapper;

import com.bookbuddy.dto.UserCreateDto;
import com.bookbuddy.dto.UserDto;
import com.bookbuddy.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    User toEntity(UserCreateDto dto);
}
