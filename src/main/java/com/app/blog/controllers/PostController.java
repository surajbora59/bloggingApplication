package com.app.blog.controllers;

import java.util.List;

import com.app.blog.dto.PostDto;
import com.app.blog.request.PostRequest;
import com.app.blog.response.GenericResponse;
import com.app.blog.service.PostService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping("/add")
    public ResponseEntity<GenericResponse> addPost(@RequestBody @Valid PostRequest postRequest) {
        System.out.println(postRequest.getUserId());
        return ResponseEntity.ok(postService.addPost(postRequest));
    }

    @GetMapping("")
    public ResponseEntity<PostDto> getPostById(@PathParam("id") @NotBlank @NotNull Integer id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<GenericResponse> updatePost(@RequestBody PostRequest postRequest, @PathParam("id") @NotBlank @NotNull Integer id) {
        return ResponseEntity.ok(postService.updatePost(postRequest, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GenericResponse> deletePostbyId(@PathVariable("id") @NotBlank @NotNull Integer id) {
        postService.deletePostById(id);
        return ResponseEntity.ok(GenericResponse.builder().meta("Post deleted successfully").build());
    }

    @GetMapping("/all")
    public ResponseEntity<List<PostDto>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }
}
