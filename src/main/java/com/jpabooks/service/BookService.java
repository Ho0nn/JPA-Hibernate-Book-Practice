package com.jpabooks.service;

import com.jpabooks.repository.BookRepo;
import com.jpabooks.base.BaseService;
import com.jpabooks.entity.Book;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookService extends BaseService<Book,Long> {
    private final BookRepo bookRepo;
    @Autowired
    private EntityManager em;
    @Autowired
    private Environment env;

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
        return bookRepo.deleteByAutherId(id);
    }

}
