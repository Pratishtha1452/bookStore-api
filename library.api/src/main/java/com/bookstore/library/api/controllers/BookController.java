package com.bookstore.library.api.controllers;

import com.bookstore.library.api.domain.dto.BookDto;
import com.bookstore.library.api.domain.entities.BooksEntity;
import com.bookstore.library.api.mappers.Mapper;
import com.bookstore.library.api.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    //CREATE_OR_UPDATE
    @PutMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDto> createUpdateBook(@PathVariable(name = "isbn") String isbn, @RequestBody BookDto bookDto){
        BooksEntity booksEntity = bookMapper.mapFrom(bookDto);
        boolean bookExists = bookService.isExists(isbn);
        BooksEntity savedBook = bookService.createUpdateBook(isbn, booksEntity);
        BookDto savedBookDto = bookMapper.mapTo(savedBook);
        if(bookExists){
            //update
            return new ResponseEntity<>(savedBookDto, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(savedBookDto, HttpStatus.CREATED);
        }
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
