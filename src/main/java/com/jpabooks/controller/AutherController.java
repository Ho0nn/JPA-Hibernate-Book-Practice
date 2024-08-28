package com.jpabooks.controller;

import com.jpabooks.entity.Auther;
import com.jpabooks.service.AutherService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
@Validated
@RestController
@RequestMapping("/auther")
public class AutherController {
    private final AutherService authService;

    public AutherController(AutherService authService) {
        this.authService = authService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable @Min(value = 2) @Max(value = 200) Long id) {
        return ResponseEntity.ok(authService.findById(id));
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(authService.findAll());
    }

    @PostMapping("")
    public ResponseEntity<?> insert(@RequestBody @Valid Auther entity) {
        return ResponseEntity.ok(authService.insert(entity));
    }

    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody @Valid Auther entity) {
        return ResponseEntity.ok(authService.update(entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        authService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
