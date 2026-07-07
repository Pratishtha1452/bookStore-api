package com.bookstore.library.api.services;

import com.bookstore.library.api.domain.entities.BooksEntity;

import java.util.List;
import java.util.Optional;

public interface BookService {

    public BooksEntity createUpdateBook(String isbn, BooksEntity booksEntity);

    List<BooksEntity> findAll();

    Optional<BooksEntity> findOne(String isbn);

    boolean isExists(String isbn);

    BooksEntity partialUpdate(String isbn, BooksEntity booksEntity);
}
