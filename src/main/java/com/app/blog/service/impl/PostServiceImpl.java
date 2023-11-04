package com.app.blog.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import com.app.blog.dto.PostDto;
import com.app.blog.entity.Category;
import com.app.blog.entity.Posts;
import com.app.blog.entity.User;
import com.app.blog.exception.PostDeletedException;
import com.app.blog.exception.ResourceNotFoundException;
import com.app.blog.repository.CategoryRepository;
import com.app.blog.repository.PostRepository;
import com.app.blog.repository.UserRepository;
import com.app.blog.request.PostRequest;
import com.app.blog.response.GenericResponse;
import com.app.blog.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ModelMapper modelMapper;

    public enum IsDeleted {
        ZERO,
        ONE
    }

    @Override
    public GenericResponse addPost(PostRequest postRequest) {

        CompletableFuture<User> userFuture = CompletableFuture.supplyAsync(()-> userRepository.findById(Long.valueOf(postRequest.getUserId()))
                                                                                                .orElseThrow(() -> new ResourceNotFoundException("User", "id", postRequest.getUserId())));
        CompletableFuture<Category> categoryFuture = CompletableFuture.supplyAsync(()-> categoryRepository.findById(postRequest.getCategoryId())
                                                                                                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", postRequest.getCategoryId())));
        CompletableFuture.allOf(userFuture, categoryFuture).join();
        User user = userFuture.join();
        Category category = categoryFuture.join();
        Posts post = Posts.builder()
            .title(postRequest.getTitle())
            .postUuid(UUID.randomUUID())
            .content(postRequest.getContent())
            .user(user)
            .category(category)
            .imageUrl("https://picsum.photos/200/300")
            .isDeleted(IsDeleted.ZERO.ordinal())
            .build();
        setCreatedAtDate(post);
        postRepository.save(post);
        return GenericResponse.builder().meta("Post added successfully").build();
    }

    @Override
    public PostDto getPostById(Integer id) {
        Posts post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public GenericResponse updatePost(PostRequest postRequest, Integer id) {
        if (postRequest.getTitle() == null || postRequest.getContent() == null || postRequest.getCategoryId() == null) {
            throw new IllegalArgumentException();
        }
    
        Posts post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
    
        if (postRequest.getCategoryId() != null && !postRequest.getCategoryId().equals(post.getCategory().getCategoryId())) {
            Category category = categoryRepository.findById(postRequest.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", postRequest.getCategoryId()));
            post.setCategory(category);
        }
    
        if (postRequest.getTitle() != null) {
            post.setTitle(postRequest.getTitle());
        }
    
        if (postRequest.getContent() != null) {
            post.setContent(postRequest.getContent());
        }

        setUpdatedAt(post);
        postRepository.save(post);
    
        return GenericResponse.builder().meta("Post updated successfully").build();
    }

    @Override
    public void deletePostById(Integer id) {
        Posts post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        if(post.getIsDeleted() == IsDeleted.ONE.ordinal()){
            throw new PostDeletedException();
        }
        post.setIsDeleted(IsDeleted.ONE.ordinal());
        setUpdatedAt(post);
        postRepository.save(post);
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Posts> posts = postRepository.findAll();
        return posts.stream().map(post -> modelMapper.map(post, PostDto.class)).toList();
    }

    @Override
    public List<PostDto> getAllPostsByUserId(Integer userId) {
        return null;
    }

    @Override
    public List<PostDto> getAllPostsByCategoryId(Integer categoryId) {
        return null;
    }

    @Override
    public List<PostDto> getAllPostsByUserIdAndCategoryId(Integer userId, Integer categoryId) {
        return null;
    }

    @Override
    public List<PostDto> searchPostsByTitle(String title) {
        return null;
    }

    private void setCreatedAtDate(Posts post) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.format(new Date());
        post.setCreatedAt(sdf.format(new Date()));
        post.setUpdatedAt(sdf.format(new Date()));
    }

    private void setUpdatedAt(Posts post) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.format(new Date());
        post.setUpdatedAt(sdf.format(new Date()));
    }
}
