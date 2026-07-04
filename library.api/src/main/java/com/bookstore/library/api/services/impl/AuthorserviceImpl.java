package com.bookstore.library.api.services.impl;

import com.bookstore.library.api.domain.entities.AuthorsEntity;
import com.bookstore.library.api.repositories.AuthorRepository;
import com.bookstore.library.api.services.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AuthorserviceImpl implements AuthorService{

    private AuthorRepository authorRepository;

    public AuthorserviceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorsEntity createAuthor(AuthorsEntity authorsEntity) {
        return authorRepository.save(authorsEntity);
    }

    @Override
    public List<AuthorsEntity> findAll() {
        return StreamSupport.stream(authorRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Optional<AuthorsEntity> findOne(Long id) {
        return authorRepository.findById(id);
    }


}



