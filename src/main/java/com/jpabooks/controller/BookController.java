package com.jpabooks.controller;

import com.jpabooks.entity.Book;
import com.jpabooks.dto.BookDTO;
import com.jpabooks.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Book book = bookService.findById(id);
        BookDTO dto =new BookDTO();
        dto.setId(book.getId());
        dto.setName(book.getName());
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @PostMapping("")
    public ResponseEntity<?> insert(@RequestBody @Valid BookDTO entity) {
        Book book = new Book();
        book.setName(entity.getName());
        book.setPrice(entity.getPrice());
       // book.setAuthor(entity.getAuthor());

        return ResponseEntity.ok(bookService.insert(book));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody @Valid Book entity) {
        return ResponseEntity.ok(bookService.update(entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        bookService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/auther/{id}")
    public ResponseEntity<?> deleteByAuther(@PathVariable Long id){
        return ResponseEntity.ok(bookService.deleteByAuthor(id));
    }
}
