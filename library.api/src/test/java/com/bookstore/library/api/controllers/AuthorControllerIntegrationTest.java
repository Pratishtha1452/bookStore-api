package com.bookstore.library.api.controllers;

import com.bookstore.library.api.TestDataUtil;
import com.bookstore.library.api.domain.entities.AuthorsEntity;
import com.bookstore.library.api.services.AuthorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class AuthorControllerIntegrationTest {

    private AuthorService authorService;

    private MockMvc mockMvc;

    private ObjectMapper objectmapper;

    @Autowired
    public AuthorControllerIntegrationTest(MockMvc mockMvc, ObjectMapper objectmapper, AuthorService authorService) {
        this.mockMvc = mockMvc;
        this.objectmapper = objectmapper;
        this.authorService = authorService;
    }

    @Test
    public void testThatCreateAuthorsSuccessfullyReturnsHttp201Created() throws Exception {
        AuthorsEntity authorsEntity = TestDataUtil.createTestAuthor();
        authorsEntity.setId(null);
        String json = objectmapper.writeValueAsString(authorsEntity);
        mockMvc.perform(
                post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatCreateAuthorsSuccessfullyReturnsSavedAuthor() throws Exception {
        AuthorsEntity authorsEntity = TestDataUtil.createTestAuthor();
        authorsEntity.setId(null);
        String json = objectmapper.writeValueAsString(authorsEntity);
        mockMvc.perform(
                post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("Abegale rose")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value("20")
        );
    }

    @Test
    public void testThatListAuthorsReturnsHttpsStatus200() throws Exception {
        mockMvc.perform(
                get("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatListAuthorsSuccessfullyReturnsListOfAuthors() throws Exception {
        AuthorsEntity authorsEntity = TestDataUtil.createTestAuthor();
        authorService.createAuthor(authorsEntity);
        mockMvc.perform(
                get("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].name").value("Abegale rose")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].age").value("20")
        );
    }
}
