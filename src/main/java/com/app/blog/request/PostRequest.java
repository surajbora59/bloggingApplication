package com.app.blog.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostRequest {
    private Integer postId;
    private String title;
    private String content;
    private Integer categoryId;
    private Integer userId;
}
