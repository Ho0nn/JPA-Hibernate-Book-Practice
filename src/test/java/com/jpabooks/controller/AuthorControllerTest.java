package com.jpabooks.controller;


import static io.micrometer.core.instrument.binder.http.HttpRequestTags.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.Optional;

import com.jpabooks.dto.AuthorDTO;
import com.jpabooks.entity.Author;
import com.jpabooks.service.AuthorService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AuthorControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AuthorService authorService;
//    public void findByEmailNotFound () throws Exception {
//
//        Optional<Author> authParam = Optional.of(new Author("Haneen", "12.45.61.95", "Hanona@gmail.com", null));
//        Mockito.when(authorService.findByEmail(Mockito.anyString())).thenReturn(authParam);
//
//        String email = "Hanona@gmail.com";
//        mockMvc.perform(get("/author/email/{email}"))
//                .contentType("application/json")
//                .param("sendWelcomeMail")
//                .content(objectMapper.writeValueAsString())
//                .andExpect(status().isOK());
//
//    }

    @Test
    public void insertAutherTest() throws Exception {

        Optional<Author> authParam = Optional.of(new Author("Haneen", "12.45.61.95", "Hanona@gmail.com", null));


        AuthorDTO dto = new AuthorDTO();

        Mockito.when(authorService.insert(Mockito.any(Author.class))).thenReturn(authParam.get());

        mockMvc.perform(post("/author")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }



//    @Test
//    public void findByEmailNotFound (){
//
//        Optional<Author> authParam = Optional.of(new Author("Haneen","12.45.61.95","Hanona@gmail.com",null));
//        Mockito.when(authorService.findByEmail(Mockito.anyString())).thenReturn(authParam);
//
//        String email ="Hanona@gmail.com";
//        ResponseEntity<AuthorDTO> response = restTemplate.getForEntity("/author/email/"+email, AuthorDTO.class);
//        assertThat(response.getStatusCode().equals(HttpStatus.OK));
//        assertEquals(HttpStatus.OK,response.getStatusCode());
//     //   assertThat(response.getBody().equals("OK"));
//
//    }

}
