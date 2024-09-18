package com.jpabooks.repository;


import com.jpabooks.entity.Author;
import com.jpabooks.service.AuthorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AuthorRepoTest {

    AuthorRepo authorRepo;

    @Test
    void findByEmailNotFoundTest() {
        Optional<Author> author = authorRepo.findByEmail("Ahmed@gmail.com");
        assertEquals(false, author.isPresent());
    }
    @Test
    void findByEmailFoundTest() {
        Optional<Author> author = authorRepo.findByEmail("john@gmail.com");
        assertEquals(true, author.isPresent());
    }
}
