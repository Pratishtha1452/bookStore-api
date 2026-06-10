package com.bookstore.library.api.controllers;

import com.bookstore.library.api.domain.entities.AuthorsEntity;
import com.bookstore.library.api.domain.dto.AuthorDto;
import com.bookstore.library.api.services.AuthorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController {

    private AuthorService authorService;

    public AuthorController(AuthorService authorService){
        this.authorService = authorService;
    }

    //CREATE
    @PostMapping(path = "/authors")
    public AuthorsEntity createAuthor(@RequestBody AuthorDto authors){
        return authorService.createAuthor(authors);

    }
}
