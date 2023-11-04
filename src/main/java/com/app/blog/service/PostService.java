package com.app.blog.service;

import java.util.List;

import com.app.blog.dto.PostDto;
import com.app.blog.request.PostRequest;
import com.app.blog.response.GenericResponse;

public interface PostService {
    GenericResponse addPost(PostRequest postRequest);
    PostDto getPostById(Integer id);
    GenericResponse updatePost(PostRequest postRequest, Integer id);
    void deletePostById(Integer id);
    List<PostDto> getAllPosts();

    List<PostDto> getAllPostsByUserId(Integer userId);
    List<PostDto> getAllPostsByCategoryId(Integer categoryId);
    List<PostDto> getAllPostsByUserIdAndCategoryId(Integer userId, Integer categoryId);
    List<PostDto> searchPostsByTitle(String title);
}
