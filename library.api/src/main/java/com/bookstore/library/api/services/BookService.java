package com.bookstore.library.api.services;

import com.bookstore.library.api.domain.entities.BooksEntity;

public interface BookService {

    public BooksEntity createBook(String isbn, BooksEntity booksEntity);
}
