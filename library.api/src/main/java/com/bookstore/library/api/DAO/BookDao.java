package com.bookstore.library.api.DAO;

import com.bookstore.library.api.domain.Books;

import java.util.Optional;
import java.util.List;

public interface BookDao {
    void create(Books book);

    Optional<Books> findOne(String isbn);

    List<Books> find();

    void update(long author_id, Books book);

    void delete(long author_id);
}
