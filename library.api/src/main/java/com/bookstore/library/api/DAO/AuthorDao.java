package com.bookstore.library.api.DAO;

import com.bookstore.library.api.domain.Authors;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {
    void create(Authors authors);

    Optional<Authors> findOne(long l);

    List<Authors> find();

    void update(long id, Authors author);

    void delete(long id);
}
