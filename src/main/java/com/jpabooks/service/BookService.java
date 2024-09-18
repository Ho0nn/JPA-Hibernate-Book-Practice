package com.jpabooks.service;

import com.jpabooks.dto.BookDTO;
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
        BookDTO bookDTO = BookDTO.builder().name("Java Book")
                .price(300)
                .build();
        return bookRepo.saveAll(entities);
    }

    @Override
    public Book update(Book entity) {
        Book book = findById(entity.getId());
        book.setName(entity.getName());
        book.setPrice(entity.getPrice());
        book.setAuthor(entity.getAuthor());
        return super.update(book);
    }

    public int deleteByAuthor(Long id) {
        return bookRepo.deleteByAuthorId(id);
    }
}
