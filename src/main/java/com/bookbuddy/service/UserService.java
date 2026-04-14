package com.bookbuddy.service;

import com.bookbuddy.dto.UserDto;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserDto create(UserDto dto);

    UserDto getById(UUID id);

    List<UserDto> getAll();

    void delete(UUID id);
}
