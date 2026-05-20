package com.bookstore.library.api.DAO;

import com.bookstore.library.api.domain.Books;

import java.util.Optional;

public interface BookDao {
    void create(Books book);

    Optional<Books> findOne(String isbn);
}
