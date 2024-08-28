package com.jpabooks.service;

import com.jpabooks.Repository.BookRepo;
import com.jpabooks.base.BaseService;
import com.jpabooks.entity.Book;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
@Service
public class BookService extends BaseService<Book,Long> {
    private final BookRepo bookRepo;

    public BookService(BookRepo bookRepo) {
        super(bookRepo);
        this.bookRepo = bookRepo;
    }

    public List<Book> findAll() {
        return bookRepo.findAll();
    }

    public List<Book> insertAll(List<Book> entities) {
        return bookRepo.saveAll(entities);
    }

    public Book update(Book entity) {
        Book book = findById(entity.getId());
        book.setName(entity.getName());
        book.setPrice(entity.getPrice());
        book.setAuther(entity.getAuther());
        return update(book);
    }
    public int deleteByAuther(Long id){
        return bookRepo.deleteByAuther(id);
    }

}
