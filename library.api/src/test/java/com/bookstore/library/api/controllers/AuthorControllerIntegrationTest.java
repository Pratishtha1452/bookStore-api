package com.bookstore.library.api.controllers;

import com.bookstore.library.api.TestDataUtil;
import com.bookstore.library.api.domain.dto.AuthorDto;
import com.bookstore.library.api.domain.entities.AuthorsEntity;
import com.bookstore.library.api.services.AuthorService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        authorService.saveAuthor(authorsEntity);
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

    @Test
    public void testThatGetAuthorsReturnsHttpsStatus200WhenAuthorExists() throws Exception {
        AuthorsEntity authorsEntity = TestDataUtil.createTestAuthor();
        authorService.saveAuthor(authorsEntity);

        mockMvc.perform(
                get("/authors/" + authorsEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatGetAuthorsReturnsHttpsStatus404WhenAuthorDoesNotExists() throws Exception {

        mockMvc.perform(
                get("/authors/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound()
        );
    }

    @Test
    public void testThatGetAuthorsReturnsAuthorWhenAuthorExists() throws Exception {
        AuthorsEntity authorsEntity = TestDataUtil.createTestAuthor();
        authorService.saveAuthor(authorsEntity);

        mockMvc.perform(
                get("/authors/" + authorsEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value("1")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("Abegale rose")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value("20")
        );
    }

    @Test
    public void testThatPutAuthorsReturnsHttpsStatus404WhenAuthorDoesNotExists() throws Exception {
        AuthorsEntity authorsEntity = TestDataUtil.createTestAuthor();
        authorsEntity.setId(null);
        String json = objectmapper.writeValueAsString(authorsEntity);

        mockMvc.perform(
                put("/authors/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testThatPutAuthorsReturnsHttpsStatus200WhenAuthorExists() throws Exception {
        AuthorsEntity authorsEntity = TestDataUtil.createTestAuthor();
        String json = objectmapper.writeValueAsString(authorsEntity);
        authorService.saveAuthor(authorsEntity);
        mockMvc.perform(
                put("/authors/" + authorsEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        ).andExpect(MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatPutAuhthorsUpdatesExistingAuthor() throws Exception {
        AuthorsEntity authorsEntity = TestDataUtil.createTestAuthor();
        AuthorsEntity savedAuthor = authorService.saveAuthor(authorsEntity);

        AuthorsEntity authorsEntity2 = TestDataUtil.createTestAuthorB();
        authorsEntity2.setId(authorsEntity.getId());
        String json2 = objectmapper.writeValueAsString(authorsEntity2);

        mockMvc.perform(
                put("/authors/" + authorsEntity2.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json2)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(authorsEntity.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value(authorsEntity2.getName())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value(authorsEntity2.getAge())
        );
    }

    @Test
    public void testThatPartialUpdateExistingAuthorReturnsHttpsStatus200OOKAndUpdates() throws Exception {
        AuthorsEntity authorsEntity = TestDataUtil.createTestAuthor();
        authorService.saveAuthor(authorsEntity);

        AuthorDto authorDto = new AuthorDto();
        authorDto.setName("UPDATED");
        String json = objectmapper.writeValueAsString(authorDto);
        mockMvc.perform(
                patch("/authors/" + authorsEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        ).andExpect(MockMvcResultMatchers.status().isOk()
        ).andExpect(MockMvcResultMatchers.jsonPath("$.id").value(authorsEntity.getId())
        ).andExpect(MockMvcResultMatchers.jsonPath("$.name").value(authorDto.getName())
        ).andExpect(MockMvcResultMatchers.jsonPath("$.age").value(authorsEntity.getAge())
        );
    }

}
