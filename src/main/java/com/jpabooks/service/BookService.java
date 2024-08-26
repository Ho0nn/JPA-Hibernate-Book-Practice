package com.jpabooks.service;

import com.jpabooks.Repository.BookRepo;
import com.jpabooks.entity.Book;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class BookService {
    private final BookRepo bookRepo;

    public BookService(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    public Book findById(Long id) {
        return bookRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with ID: " + id));
    }

    public List<Book> findAll() {
        return bookRepo.findAll();
    }

    public Book insert(Book entity) {
        if (entity.getId() != null) {
            throw new IllegalArgumentException("ID should be null when inserting a new book.");
        }
        return bookRepo.save(entity);
    }

    public List<Book> insertAll(List<Book> entities) {
        return bookRepo.saveAll(entities);
    }

    public Book update(Book entity) {
        Book book = findById(entity.getId());
        book.setName(entity.getName());
        book.setPrice(entity.getPrice());
        book.setAuther(entity.getAuther());
        return bookRepo.save(book);
    }

    public void deleteById(Long id) {
        bookRepo.deleteById(id);
    }

    public int deleteByAuther(Long id){
        return bookRepo.deleteByAuther(id);
    }

}
