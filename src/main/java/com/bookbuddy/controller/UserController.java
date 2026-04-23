package com.bookbuddy.controller;

import com.bookbuddy.api.UserApi;
import com.bookbuddy.dto.UserCreateDto;
import com.bookbuddy.dto.UserDto;
import com.bookbuddy.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService userService;

    @Override
    @GetMapping
    public List<UserDto> getAllUsers(){
        return userService.getAll();
    }

    @Override
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable UUID id){
        return userService.getById(id);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody @Valid UserCreateDto dto){
        return userService.create(dto);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
