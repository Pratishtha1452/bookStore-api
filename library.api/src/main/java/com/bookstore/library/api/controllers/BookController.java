package com.bookstore.library.api.controllers;

import com.bookstore.library.api.domain.dto.BookDto;
import com.bookstore.library.api.domain.entities.BooksEntity;
import com.bookstore.library.api.mappers.Mapper;
import com.bookstore.library.api.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    private Mapper<BooksEntity, BookDto> bookMapper;
    private BookService bookService;

    public BookController(BookService bookService, Mapper<BooksEntity, BookDto> bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    //CREATE
    @PutMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDto> createBook(@PathVariable(name = "isbn") String isbn, @RequestBody BookDto bookDto){
        BooksEntity booksEntity = bookMapper.mapFrom(bookDto);
        BooksEntity savedBook = bookService.createBook(isbn, booksEntity);
        BookDto savedBookDto = bookMapper.mapTo(savedBook);
        return new ResponseEntity<>(savedBookDto, HttpStatus.CREATED);
    }
}
