package com.bookstore.library.api.controllers;

import com.bookstore.library.api.TestDataUtil;
import com.bookstore.library.api.domain.dto.BookDto;
import com.bookstore.library.api.domain.entities.AuthorsEntity;
import com.bookstore.library.api.domain.entities.BooksEntity;
import com.bookstore.library.api.services.BookService;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ExtendWith(SpringExtension.class)
public class BookControllerIntegrationTest {
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private BookService bookService;

    @Autowired
    public BookControllerIntegrationTest(MockMvc mockMvc, ObjectMapper objectMapper, BookService bookService) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.bookService = bookService;
    }

    @Test
    public void testThatCreateBooksSuccesfullyReturns201Created() throws Exception {

        BooksEntity booksEntity = TestDataUtil.createTestBooks(null);
        String json = objectMapper.writeValueAsString(booksEntity);
        mockMvc.perform(
                put("/books/" + booksEntity.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatUpdateBooksSuccesfullyReturns200OK() throws Exception {

        //book saved(og)
        BooksEntity booksEntity = TestDataUtil.createTestBooks(null);
        BooksEntity savedBook = bookService.createUpdateBook(booksEntity.getIsbn(), booksEntity);

        //book json to be passed - book values(new)
        BooksEntity booksEntity2 = TestDataUtil.createTestBooksB(null);
        //set the id of the book object with new data as og id... so that it remains same
        booksEntity2.setIsbn(booksEntity.getIsbn());

        String json2 = objectMapper.writeValueAsString(booksEntity2);

        //pass the id that is to be there after updation in the path
        mockMvc.perform(
                put("/books/" + booksEntity2.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json2)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
                //the result isbn should be the same as og isbn... and should not be changed
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value(booksEntity.getIsbn())
        ).andExpect(
                //the result title should be same as the book object whose json was provided
                MockMvcResultMatchers.jsonPath("$.title").value(booksEntity2.getTitle())
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

    @Test
    public void testThatListBooksReturnsHttpsStatus200Ok() throws Exception {
        mockMvc.perform(
                get("/books")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatListBooksSuccessfullyReturnsListOfBooks() throws Exception {
        AuthorsEntity authorsEntity = TestDataUtil.createTestAuthor();
        BooksEntity booksEntity = TestDataUtil.createTestBooks(authorsEntity);
        bookService.createUpdateBook(booksEntity.getIsbn(), booksEntity);

        mockMvc.perform(
                get("/books")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].isbn").value(booksEntity.getIsbn())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].title").value(booksEntity.getTitle())
        );
    }

    @Test
    public void testThatGetBooksReturnsHttpsStatus200WhenBookExists() throws Exception {
        AuthorsEntity authorsEntity = TestDataUtil.createTestAuthor();
        BooksEntity booksEntity = TestDataUtil.createTestBooks(authorsEntity);
        bookService.createUpdateBook(booksEntity.getIsbn(), booksEntity);

        mockMvc.perform(
                get("/books/" + booksEntity.getIsbn())
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatGetBooksReturnsHttpsStatus404WhenBookDoesNotExists() throws Exception {
        mockMvc.perform(
                get("/books/978-1-2345-6789-1")
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );
    }

    @Test
    public void testThatGetBooksReturnsBookWhenBookExists() throws Exception {
        AuthorsEntity authorsEntity = TestDataUtil.createTestAuthor();
        BooksEntity booksEntity = TestDataUtil.createTestBooks(authorsEntity);
        bookService.createUpdateBook(booksEntity.getIsbn(), booksEntity);

        mockMvc.perform(
                get("/books/" + booksEntity.getIsbn())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value(booksEntity.getIsbn())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value(booksEntity.getTitle())
        );
    }

    @Test
    public void testThatPartialUpdateExistingBookReturnsHttpsStatus2002OKAndUpdates() throws Exception {
        BooksEntity booksEntity = TestDataUtil.createTestBooks(null);
        bookService.createUpdateBook(booksEntity.getIsbn(), booksEntity);

        BookDto bookDto = new BookDto();
        bookDto.setTitle("The Bell Jar");

        String json = objectMapper.writeValueAsString(bookDto);

        mockMvc.perform(
                patch("/books/" + booksEntity.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        ).andExpect(MockMvcResultMatchers.status().isOk()
        ).andExpect(MockMvcResultMatchers.jsonPath("$.isbn").value(booksEntity.getIsbn())
        ).andExpect(MockMvcResultMatchers.jsonPath("$.title").value(bookDto.getTitle())
        );
    }


}
