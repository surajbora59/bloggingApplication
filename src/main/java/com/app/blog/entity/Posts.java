package com.app.blog.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Posts")
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;
    private UUID postUuid; // Using byte array to store UUID as BINARY(16)
    private String ImageUrl;
    private Integer categoryId;

    @Column(name = "UserId")
    private Integer userId;

    private String Title;

    private String Content;

    @Column(name = "CreatedAt")
    private String createdAt;

    @Column(name = "UpdatedAt")
    private String updatedAt;
    private Integer isDeleted;
}
