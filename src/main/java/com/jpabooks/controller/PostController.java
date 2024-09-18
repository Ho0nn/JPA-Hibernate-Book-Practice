package com.jpabooks.controller;

import com.jpabooks.dto.PostDTO;
import com.jpabooks.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
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
