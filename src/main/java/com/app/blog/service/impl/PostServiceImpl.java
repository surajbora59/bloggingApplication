package com.app.blog.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.app.blog.dto.PostDto;
import com.app.blog.entity.Posts;
import com.app.blog.exception.ResourceNotFoundException;
import com.app.blog.repository.PostRepository;
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
    ModelMapper modelMapper;

    public enum IsDeleted {
        ZERO,
        ONE
    }

    @Override
    public GenericResponse addPost(PostRequest postRequest) {
        Posts post = new Posts();
        post.setTitle(postRequest.getTitle());
        post.setPostUuid(UUID.randomUUID());
        post.setContent(postRequest.getContent());
        post.setUserId(postRequest.getUserId());
        post.setCategoryId(postRequest.getCategoryId());
//        post.setImageUrl(postRequest.getImageUrl());
        post.setIsDeleted(IsDeleted.ZERO.ordinal());
        setCreatedAt(post);
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
        Optional<Posts> post = Optional.ofNullable(postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id)));
        post.ifPresent(this::setUpdatedAt);
        if(postRequest.getTitle()!= null){
            post.get().setTitle(postRequest.getTitle());
        }
        if(postRequest.getContent()!= null){
            post.get().setContent(postRequest.getContent());
        }
        if(postRequest.getCategoryId()!= null){
            post.get().setCategoryId(postRequest.getCategoryId());
        }
        postRepository.save(post.get());
        return GenericResponse.builder().meta("Post updated successfully").build();
    }

    @Override
    public void deletePostById(Integer id) {
        Optional<Posts> post = Optional.ofNullable(postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id)));
        post.ifPresent(this::setUpdatedAt);
        post.get().setIsDeleted(IsDeleted.ONE.ordinal());
    }

    @Override
    public List<PostDto> getAllPosts() {
        return null;
    }

    private void setCreatedAt(Posts post) {
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
