package com.bookstore.library.api.services;

import com.bookstore.library.api.domain.entities.AuthorsEntity;

import java.util.List;

public interface AuthorService {

    AuthorsEntity createAuthor(AuthorsEntity authorsEntity);

    List<AuthorsEntity> findAll();
}
