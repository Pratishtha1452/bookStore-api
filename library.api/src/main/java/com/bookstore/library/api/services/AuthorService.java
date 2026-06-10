package com.bookstore.library.api.services;

import com.bookstore.library.api.domain.entities.AuthorsEntity;

public interface AuthorService {

    AuthorsEntity createAuthor(AuthorsEntity authorsEntity);
}
