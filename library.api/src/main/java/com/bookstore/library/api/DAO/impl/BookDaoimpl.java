package com.bookstore.library.api.DAO.impl;

import com.bookstore.library.api.DAO.BookDao;
import com.bookstore.library.api.domain.Books;
import org.springframework.jdbc.core.JdbcTemplate;

public class BookDaoimpl implements BookDao {

    private final JdbcTemplate jdbcTemplate;

    public BookDaoimpl(final JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Books book) {
        jdbcTemplate.update("INSERT into books(isbn, title, authorid) VALUES(?, ?, ?)",
                book.getIsbn(), book.getTitle(), book.getAuthorid()
        );
    }
}
