package com.jpabooks.controller;
import com.jpabooks.dto.AuthorDTO;
import com.jpabooks.entity.Author;
import com.jpabooks.entity.AuthorSearch;
import com.jpabooks.mapper.AuthorMapper;
import com.jpabooks.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
@Validated
public class AuthorController {

    private final AuthorService authorService;
    private final AuthorMapper authorMapper;
    @Operation(summary = "Find Author by ID")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200",description = "Found Author",
                    content = {@Content(mediaType="application/json",
                            schema=@Schema(implementation = Author.class))}),
            @ApiResponse(responseCode = "400",description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Author not found",content=@Content)})

    @GetMapping("/id/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Author author = authorService.findById(id);
        AuthorDTO authorDTO = authorMapper.maptoDTO(author);
        return ResponseEntity.ok(authorDTO);
    }
    @Operation(summary = "Find Author by Email")
    @GetMapping("/email/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable String email) throws ExecutionException, InterruptedException {
        Author author = authorService.findByEmail(email).get();
        AuthorDTO authorDTO = authorMapper.maptoDTO(author);
        return ResponseEntity.ok(authorDTO);
    }

    @Operation(summary = "Get All Authors")
    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(authorService.findAll());
    }
    @Operation(summary = "Add Author")
    @PostMapping("")
    public ResponseEntity<?> insert(@RequestBody @Valid AuthorDTO dto) {
        Author author = new Author(dto.getName(),  dto.getIpAddress(),dto.getEmail(), dto.getImgPath());
        Author entity = authorService.insert(author);
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(entity.getId());
        authorDTO.setName(entity.getName());
        authorDTO.setIpAddress(entity.getIpAddress ());
        authorDTO.setEmail(entity.getEmail());
        authorDTO.setImgPath(entity.getImgPath());
        return ResponseEntity.ok(authorDTO);
    }

    @Operation(summary = "Update Author info")
    @PutMapping("")
     public ResponseEntity<?> update(@RequestBody @Valid AuthorDTO dto) {
    Author author = new Author(dto.getName(),  dto.getIpAddress(),dto.getEmail(), dto.getImgPath());
    Author entity = authorService.update(author);
    AuthorDTO authorDTO = authorMapper.maptoDTO(entity);
    return ResponseEntity.ok(authorDTO);
    }

    @Operation(summary = "Delete Author by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        authorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Find Author by search")
    @PostMapping("/spec")
    public ResponseEntity<?> findByAuthorSpec(@RequestBody AuthorSearch search) {
        return ResponseEntity.ok(authorService.findByAuthorSpec(search));
    }
}
