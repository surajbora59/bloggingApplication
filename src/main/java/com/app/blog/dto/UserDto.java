package com.app.blog.dto;

import java.io.Serial;
import java.io.Serializable;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Integer id;
    @NotNull
    @Size(min = 5, max = 20)
    @NotBlank(message = "username cannot be blank")
    private String username;
    @Email
    @NotBlank
    private String email;
    @NotNull
    @NotBlank(message = "about cannot be blank")
    private String about;
    @NotNull
    @Size(min = 5, max = 20)
    @NotBlank(message = "password cannot be blank")
    private String password;
}