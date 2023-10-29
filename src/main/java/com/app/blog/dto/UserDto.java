package com.app.blog.dto;

import java.io.Serial;
import java.io.Serializable;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @NotNull
    private Integer id;
    @NotNull
    @Max(value = 20)
    @Min(value = 5)
    @NotBlank(message = "username cannot be blank")
    private String username;
    @Email
    @NotBlank
    private String email;
    @NotNull
    @NotBlank(message = "about cannot be blank")
    private String about;
    @NotNull
    @Max(value = 20)
    @Min(value = 5)
    @NotBlank(message = "password cannot be blank")
    private String password;
}