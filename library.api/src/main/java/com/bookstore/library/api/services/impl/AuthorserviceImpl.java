package com.bookstore.library.api.services.impl;

import com.bookstore.library.api.domain.entities.AuthorsEntity;
import com.bookstore.library.api.repositories.AuthorRepository;
import com.bookstore.library.api.services.AuthorService;
import org.springframework.stereotype.Service;

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


}



