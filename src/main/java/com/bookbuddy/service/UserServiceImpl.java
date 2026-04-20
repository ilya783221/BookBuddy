package com.bookbuddy.service;

import com.bookbuddy.dto.UserCreateDto;
import com.bookbuddy.dto.UserDto;
import com.bookbuddy.exception.notfound.UserNotFoundException;
import com.bookbuddy.mapper.UserMapper;
import com.bookbuddy.model.User;
import com.bookbuddy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Override
    public UserDto create(UserCreateDto dto) {
        User user = userMapper.toEntity(dto);
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public UserDto getById(UUID id) {
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Override
    public void delete(UUID id) {
        userRepository.deleteById(id);
    }
}
