package com.bookstore.library.api.DAO.impl;

import com.bookstore.library.api.DAO.AuthorDao;
import com.bookstore.library.api.domain.Authors;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Optional;

public class AuthorDaoimpl implements AuthorDao {

    private final JdbcTemplate jdbcTemplate;

    public AuthorDaoimpl(final JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Authors authors) {
        jdbcTemplate.update("INSERT into authors(id, name, age) VALUES(?, ?, ?)",
        authors.getId(), authors.getName(), authors.getAge()
        );
    }

    @Override
    public Optional<Authors> findOne(long l) {

        return Optional.empty();
    }
}
