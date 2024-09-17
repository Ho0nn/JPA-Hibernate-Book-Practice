package com.jpabooks.service;

import com.jpabooks.repository.BookRepo;
import com.jpabooks.base.BaseService;
import com.jpabooks.entity.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService extends BaseService<Book, Long> {
    private final BookRepo bookRepo;

    @Override
    public List<Book> findAll() {
        List<Book> books = bookRepo.findAll();
        return books != null ? books : Collections.emptyList();
    }

    @Override
    public List<Book> insertAll(List<Book> entities) {
        return bookRepo.saveAll(entities);
    }

    @Override
    public Book update(Book entity) {
        Book book = findById(entity.getId());
        book.setName(entity.getName());
        book.setPrice(entity.getPrice());
        book.setAuther(entity.getAuther());
        return super.update(book);
    }

    public int deleteByAuther(Long id) {
        return bookRepo.deleteByAutherId(id);
    }
}
