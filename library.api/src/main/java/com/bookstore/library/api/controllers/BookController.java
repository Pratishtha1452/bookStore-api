package com.bookstore.library.api.controllers;

import com.bookstore.library.api.domain.dto.BookDto;
import com.bookstore.library.api.domain.entities.BooksEntity;
import com.bookstore.library.api.mappers.Mapper;
import com.bookstore.library.api.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    //FIND ALL
    @GetMapping(path = "/books")
    public List<BookDto> listOfBooks(){
        List<BooksEntity> books = bookService.findAll();

        return books.stream().map(bookMapper::mapTo).collect(Collectors.toList());
    }

    //FIND ONE
    @GetMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDto> getBook(@PathVariable("isbn") String isbn){
        Optional<BooksEntity> foundbook = bookService.findOne(isbn);
        return foundbook.map(booksEntity -> {
            BookDto bookDto = bookMapper.mapTo(booksEntity);
            return new ResponseEntity<>(bookDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

}
