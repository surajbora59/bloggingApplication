package com.app.blog.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private Integer id;
    @NotNull
    @NotBlank(message = "username cannot be blank")
    private String username;
    @Email
    @NotBlank
    private String email;
    @NotNull
    @NotBlank(message = "about cannot be blank")
    private String about;
    @NotNull
    @NotBlank(message = "password cannot be blank")
    private String password;
}