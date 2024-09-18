package com.jpabooks.config;

import com.jpabooks.entity.Author;
import com.jpabooks.entity.Book;
import com.jpabooks.service.AuthorService;
import com.jpabooks.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class StartUpApp implements CommandLineRunner {
    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookService bookService;

    @Override
    public void run(String... args) throws Exception {

        if (authorService.findAll().isEmpty()) {
            Author author1 = new Author();
            author1.setName("Haneen");
            author1.setEmail("HAno@gmail.com");
            Author author2 = new Author();
            author2.setName("Mohamed");
            author2.setEmail("Mohamed@gmail.com");
            Author author3 = new Author();
            author3.setName("Ahmed");
            author3.setEmail("Ahmed@gmail.com");
            authorService.insertAll(Arrays.asList(author1, author2, author3));
        }
        if (bookService.findAll().isEmpty()&&bookService.findAll()==null) {
            Book book1 = new Book();
            book1.setName("JPA");
            book1.setPrice(300);
            book1.setAuthor(authorService.getById(1L));

            Book book2 = new Book();
            book2.setName("Coding");
            book2.setPrice(500);
            book2.setAuthor(authorService.getById(2L));

            Book book3 = new Book();
            book3.setName("Java");
            book3.setPrice(100);
            book3.setAuthor(authorService.getById(3L));

            bookService.insertAll(Arrays.asList(book1, book2, book3));
        }
    }
}
