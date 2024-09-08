package com.jpabooks.controller;

import com.jpabooks.entity.Auther;
import com.jpabooks.entity.PostDTO;
import com.jpabooks.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("/{id}")
    public ResponseEntity<?>getPostById(@PathVariable Long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }
    @GetMapping
    public ResponseEntity<?> getAllPosts()  {
        return ResponseEntity.ok(postService.getAllPosts());
    }
    @PostMapping
    public ResponseEntity<?> addPost(@RequestBody PostDTO postDTO) {
        return ResponseEntity.ok(postService.addPost(postDTO));
    }
    @PutMapping("")
    public ResponseEntity<?> updatePost(@RequestBody PostDTO postDTO) {
            postService.updatePost(postDTO);
            return ResponseEntity.ok("Post updated successfully.");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

}
