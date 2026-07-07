package com.bookstore.library.api.services.impl;

import com.bookstore.library.api.domain.dto.BookDto;
import com.bookstore.library.api.domain.entities.BooksEntity;
import com.bookstore.library.api.repositories.BookRepository;
import com.bookstore.library.api.services.BookService;
import org.springframework.stereotype.Service;

import javax.management.RuntimeErrorException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BooksEntity createUpdateBook(String isbn, BooksEntity booksEntity) {
        booksEntity.setIsbn(isbn);
        return bookRepository.save(booksEntity);
    }

    @Override
    public List<BooksEntity> findAll() {
        return StreamSupport.stream(bookRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Optional<BooksEntity> findOne(String isbn) {
        return bookRepository.findById(isbn);
    }

    @Override
    public boolean isExists(String isbn) {
        return bookRepository.existsById(isbn);
    }

    @Override
    public BooksEntity partialUpdate(String isbn, BooksEntity booksEntity) {
        booksEntity.setIsbn(isbn);

        return bookRepository.findById(isbn).map(existingBook -> {
            Optional.ofNullable(booksEntity.getTitle()).ifPresent(existingBook::setTitle);

            return bookRepository.save(booksEntity);
        }).orElseThrow(() -> new RuntimeException("Book Not Found"));
    }

    @Override
    public void deleteBook(String isbn) {
        bookRepository.deleteById(isbn);
    }
}
