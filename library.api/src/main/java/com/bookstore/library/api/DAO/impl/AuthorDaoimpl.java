package com.bookstore.library.api.DAO.impl;

import com.bookstore.library.api.DAO.AuthorDao;
import com.bookstore.library.api.domain.Authors;
import org.springframework.jdbc.core.JdbcTemplate;

public class AuthorDaoimpl implements AuthorDao {

    private final JdbcTemplate jdbcTemplate;

    public AuthorDaoimpl(final JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Authors authors) {

    }
}
