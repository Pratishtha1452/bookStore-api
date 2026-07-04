package com.bookstore.library.api.services.impl;

import com.bookstore.library.api.domain.dto.BookDto;
import com.bookstore.library.api.domain.entities.BooksEntity;
import com.bookstore.library.api.repositories.BookRepository;
import com.bookstore.library.api.services.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

    @Override
    public List<BooksEntity> findAll() {
        return StreamSupport.stream(bookRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }
}
