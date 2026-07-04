package com.bookstore.library.api.controllers;

import com.bookstore.library.api.domain.entities.AuthorsEntity;
import com.bookstore.library.api.domain.dto.AuthorDto;
import com.bookstore.library.api.mappers.Mapper;
import com.bookstore.library.api.services.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AuthorController {

    private AuthorService authorService;

    private Mapper<AuthorsEntity, AuthorDto> authorMapper;

    public AuthorController(AuthorService authorService, Mapper<AuthorsEntity, AuthorDto> authorMapper){
        this.authorService = authorService;
        this.authorMapper = authorMapper;
    }

    //CREATE
    @PostMapping(path = "/authors")
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorDto authors){
        AuthorsEntity authorsEntity = authorMapper.mapFrom(authors);
        AuthorsEntity savedAuthorsEntity = authorService.createAuthor(authorsEntity);
        return new ResponseEntity<>(authorMapper.mapTo(savedAuthorsEntity), HttpStatus.CREATED);
    }

    //READ MANY
    @GetMapping(path = "/authors")
    public List<AuthorDto> listOfAuthors(){
        List<AuthorsEntity> authors = authorService.findAll();
        //Modern JAVA...since we are using devtiro's approach which includes that the method returns authorEntitiy instead of Dtos
        return authors.stream()
                .map(authorMapper::mapTo)
                .collect(Collectors.toList());
    }

}
