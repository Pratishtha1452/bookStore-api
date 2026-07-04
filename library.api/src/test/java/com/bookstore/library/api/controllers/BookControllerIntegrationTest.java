package com.bookstore.library.api.controllers;

import com.bookstore.library.api.TestDataUtil;
import com.bookstore.library.api.domain.entities.AuthorsEntity;
import com.bookstore.library.api.domain.entities.BooksEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ExtendWith(SpringExtension.class)
public class BookControllerIntegrationTest {
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Autowired
    public BookControllerIntegrationTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    public void testThatCreateBooksSuccesfullyReturns201Created() throws Exception {
        AuthorsEntity authorsEntity = TestDataUtil.createTestAuthor();
        authorsEntity.setId(null);
        BooksEntity booksEntity = TestDataUtil.createTestBooks(authorsEntity);
        String json = objectMapper.writeValueAsString(booksEntity);
        mockMvc.perform(
                put("/books/" + booksEntity.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        ).andDo(print()
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatCreateBooksSuccessfullyReturnsSavedBook() throws Exception {
        AuthorsEntity authorsEntity = TestDataUtil.createTestAuthor();
        authorsEntity.setId(null);
        BooksEntity booksEntity = TestDataUtil.createTestBooks(authorsEntity);
        String json = objectMapper.writeValueAsString(booksEntity);

        mockMvc.perform(
                put("/books/" + booksEntity.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value(booksEntity.getIsbn())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value(booksEntity.getTitle())
        );
    }
}
