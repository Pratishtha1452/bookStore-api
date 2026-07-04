package com.bookstore.library.api.services.impl;

import com.bookstore.library.api.domain.entities.BooksEntity;
import com.bookstore.library.api.repositories.BookRepository;
import com.bookstore.library.api.services.BookService;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BooksEntity createBook(String isbn, BooksEntity booksEntity) {
        booksEntity.setIsbn(isbn);
        return bookRepository.save(booksEntity);
    }
}
