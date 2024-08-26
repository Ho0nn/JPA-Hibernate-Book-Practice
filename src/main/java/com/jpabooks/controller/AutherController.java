package com.jpabooks.controller;

import com.jpabooks.entity.Auther;
import com.jpabooks.service.AutherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
public class AutherController { // Consider renaming to "AuthorController"
    private final AutherService authService;

    public AutherController(AutherService authService) {
        this.authService = authService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(authService.findById(id));
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(authService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody Auther entity) {
        return ResponseEntity.ok(authService.insert(entity));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Auther entity) {
        return ResponseEntity.ok(authService.update(entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        authService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
