package com.app.blog.controllers;

import java.util.List;

import com.app.blog.dto.UserDto;
import com.app.blog.response.GenericResponse;
import com.app.blog.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    final String USER_DELETED = "User deleted successfully";
    @PostMapping(value = "/api/users", produces = "application/json", consumes = "application/json")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDto));
    }

    @PutMapping(value = "/api/users/{userId}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @NotNull @PathVariable Integer userId) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(userDto,userId));
    }

    @GetMapping(value = "/api/users/{userId}", produces = "application/json")
    public ResponseEntity<UserDto> getUserById(@NotNull @PathVariable Integer userId) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(userId));
    }

    @DeleteMapping(value = "/api/users/{userId}", produces = "application/json")
    public ResponseEntity<GenericResponse> deleteUser(@NotNull @PathVariable Integer userId) {
        userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(GenericResponse.builder().meta(USER_DELETED).build());
    }

    @GetMapping(value = "/api/users", produces = "application/json")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }
}
