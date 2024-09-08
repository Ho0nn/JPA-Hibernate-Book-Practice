package com.jpabooks.controller;
import com.jpabooks.entity.Auther;
import com.jpabooks.entity.AutherSearch;
import com.jpabooks.service.AutherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Find Author by ID")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200",description = "Found Author",
            content = {@Content (mediaType="application/json",
            schema=@Schema(implementation =Auther.class))}),
                    @ApiResponse(responseCode = "400",description = "Invalid id supplied",
                    content = @Content),
                            @ApiResponse(responseCode = "404",
                                    description = "Author not found",content=@Content)})
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable @Min(value = 2) @Max(value = 200) Long id) {
        return ResponseEntity.ok(authService.findById(id));
    }

    @Operation(summary = "Find Author by Email")
    @GetMapping("/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable String email){
        return ResponseEntity.ok(authService.findByEmail(email));
    }

    @Operation(summary = "Get All Authors")

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(authService.findAll());
    }

    @Operation(summary = "Add Author")

    @PostMapping("")
    public ResponseEntity<?> insert(@RequestBody @Valid Auther entity) {
        return ResponseEntity.ok(authService.insert(entity));
    }
    @Operation(summary = "Update Author info")

    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody @Valid Auther entity) {
        return ResponseEntity.ok(authService.update(entity));
    }

    @Operation(summary = "Delete Author by ID")

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        authService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Find Author by search")
    @PostMapping("/spec")
    public ResponseEntity<?> findByAutherSpec(@RequestBody AutherSearch search) {
        return ResponseEntity.ok(authService.findByAutherSpec(search));
    }
}
