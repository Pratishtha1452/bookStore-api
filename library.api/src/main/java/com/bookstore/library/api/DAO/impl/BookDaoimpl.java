package com.bookstore.library.api.DAO.impl;

import com.bookstore.library.api.DAO.BookDao;
import org.springframework.jdbc.core.JdbcTemplate;

public class BookDaoimpl implements BookDao {

    private final JdbcTemplate jdbcTemplate;

    public BookDaoimpl(final JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
}
