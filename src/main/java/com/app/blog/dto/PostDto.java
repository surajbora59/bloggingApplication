package com.app.blog.dto;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
    private Integer postId;
    private UUID postUuid;
    private String title;
    private String content;
    private Integer categoryId;
    private Integer userId;
    private String ImageUrl;
    private String createdAt;
    private String updatedAt;
    private Integer isDeleted;
}
