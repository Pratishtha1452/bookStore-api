package com.bookstore.library.api.services;

import com.bookstore.library.api.domain.entities.AuthorsEntity;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    AuthorsEntity createAuthor(AuthorsEntity authorsEntity);

    List<AuthorsEntity> findAll();

    Optional<AuthorsEntity> findOne(Long id);
}
