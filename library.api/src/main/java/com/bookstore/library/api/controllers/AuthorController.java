package com.bookstore.library.api.controllers;

import com.bookstore.library.api.domain.entities.AuthorsEntity;
import com.bookstore.library.api.domain.dto.AuthorDto;
import com.bookstore.library.api.mappers.Mapper;
import com.bookstore.library.api.services.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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
        AuthorsEntity savedAuthorsEntity = authorService.saveAuthor(authorsEntity);
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

    //READ ONE
    @GetMapping(path = "/authors/{id}")
    public ResponseEntity<AuthorDto>  getAuthor(@PathVariable("id") Long id){
        Optional<AuthorsEntity> foundAuthor = authorService.findOne(id);
        return foundAuthor.map(authorsEntity -> {
                AuthorDto authorDto = authorMapper.mapTo(authorsEntity);
                return new ResponseEntity<>(authorDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //FULL UPDATE
    @PutMapping(path = "/authors/{id}")
    public ResponseEntity<AuthorDto> fullUpdateAuthor(@PathVariable("id") Long id,
                                                      @RequestBody AuthorDto authorDto){
        if(!authorService.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        authorDto.setId(id);
        AuthorsEntity authorsEntity = authorMapper.mapFrom(authorDto);
        AuthorsEntity savedAuthorEntity = authorService.saveAuthor(authorsEntity);
        AuthorDto savedAuthorDto = authorMapper.mapTo(savedAuthorEntity);
        return new ResponseEntity<>(savedAuthorDto, HttpStatus.OK);
    }

    //PARTIAL UPDATE
    @PatchMapping(path = "authors/{id}")
    public ResponseEntity<AuthorDto> partialUpdate(@PathVariable("id") Long id,
                                                   @RequestBody AuthorDto authorDto){

        if(!authorService.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        AuthorsEntity authorsEntity = authorMapper.mapFrom(authorDto);
        AuthorsEntity updatedAuthorEntity = authorService.partialUpdate(id, authorsEntity);
        AuthorDto updatedAuthorDto = authorMapper.mapTo(updatedAuthorEntity);
        return new ResponseEntity<>(updatedAuthorDto, HttpStatus.OK);

    }

}
