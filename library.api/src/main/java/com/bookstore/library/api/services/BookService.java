package com.bookstore.library.api.services;

import com.bookstore.library.api.domain.dto.BookDto;
import com.bookstore.library.api.domain.entities.BooksEntity;

import java.util.List;

public interface BookService {

    public BooksEntity createBook(String isbn, BooksEntity booksEntity);

    List<BooksEntity> findAll();
}
