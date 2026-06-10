package com.bookstore.library.api.controllers;

import com.bookstore.library.api.domain.Authors;
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
    public Authors createAuthor(@RequestBody Authors authors){
        return authorService.createAuthor(authors);

    }
}
