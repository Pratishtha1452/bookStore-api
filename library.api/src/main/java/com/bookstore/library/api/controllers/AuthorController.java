package com.bookstore.library.api.controllers;

import com.bookstore.library.api.domain.entities.AuthorsEntity;
import com.bookstore.library.api.domain.dto.AuthorDto;
import com.bookstore.library.api.mappers.Mapper;
import com.bookstore.library.api.services.AuthorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public AuthorDto createAuthor(@RequestBody AuthorDto authors){
        AuthorsEntity authorsEntity = authorMapper.mapFrom(authors);
        AuthorsEntity savedAuthorsEntity = authorService.createAuthor(authorsEntity);
        return authorMapper.mapTo(savedAuthorsEntity);
    }
}
