package com.app.blog.dto;

import java.io.Serial;
import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Integer categoryId;
    @NotNull
    @NotBlank(message = "category title cannot be blank")
    private String categoryTitle;
    @NotNull
    @NotBlank(message = "category description cannot be blank")
    private String categoryDescription;
}
