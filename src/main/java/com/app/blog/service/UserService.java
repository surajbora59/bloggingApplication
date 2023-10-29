package com.app.blog.service;

import java.util.List;

import com.app.blog.dto.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto getUserById(Integer id);
    UserDto updateUser(UserDto userDto, Integer id);
    List<UserDto> getAllUsers();
    void deleteUser(Integer id);
}
