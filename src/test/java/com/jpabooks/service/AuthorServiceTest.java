package com.jpabooks.service;

import com.jpabooks.entity.Author;
import com.jpabooks.repository.AuthorRepo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AuthorServiceTest {
    @InjectMocks
    private AuthorService authorService;

     @MockBean
    AuthorRepo authorRepo;

    @Test
    void findByEmailNotFoundTest() {

        Optional<Author> authParam = Optional.of(new Author("Haneen","12.45.61.95","Hanona@gmail.com",null));
        Mockito.when(authorRepo.findByEmail(Mockito.anyString())).thenReturn(authParam);
        Optional<Author> author = authorService.findByEmail("mail@gmail.com");
        assertEquals(true, author.isPresent());
        assertEquals("Hanona@gmail.com",author.get().getEmail());

    }
    @Test
    void findByEmailFoundTest() {
        Optional<Author> author = authorService.findByEmail("john@gmail.com");
        assertEquals(true, author.isPresent());
    }

}
