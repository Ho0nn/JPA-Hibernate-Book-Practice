package com.jpabooks.config;

import com.jpabooks.entity.Auther;
import com.jpabooks.entity.Book;
import com.jpabooks.service.AutherService;
import com.jpabooks.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class StartUpApp implements CommandLineRunner {
    @Autowired
    private AutherService autherService;

    @Autowired
    private BookService bookService;

    @Override
    public void run(String... args) throws Exception {
        Auther auther1 = new Auther();
        auther1.setName("Haneen");
        Auther auther2 = new Auther();
        auther2.setName("Mohamed");
        Auther auther3 = new Auther();
        auther3.setName("Ahmed");
        autherService.insertAll(Arrays.asList(auther1, auther2, auther3));

        Book book1 = new Book();
        book1.setName("JPA");
        book1.setPrice(300);
        book1.setAuther(auther1);

        Book book2 = new Book();
        book2.setName("Coding");
        book2.setPrice(500);
        book2.setAuther(auther2);

        Book book3 = new Book();
        book3.setName("Java");
        book3.setPrice(100);
        book3.setAuther(auther2);

        bookService.insertAll(Arrays.asList(book1, book2, book3));
    }
}
