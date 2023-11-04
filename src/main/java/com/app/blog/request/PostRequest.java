package com.app.blog.request;

import jakarta.validation.constraints.Digits;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostRequest {
//    private Integer postId;
    private String title;
    private String content;
    private Integer categoryId;
    @Digits(integer = 10, fraction = 0, message = "invalid user id")
    private Integer userId;
}
