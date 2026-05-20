package com.bookstore.library.api.DAO.impl;

import com.bookstore.library.api.DAO.BookDao;
import com.bookstore.library.api.domain.Books;
import org.jspecify.annotations.Nullable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Books> findOne(long authorid) {
        List<Books> bookresult = jdbcTemplate.query("SELECT isbn, title, authorid FROM Books WHERE authorid = ? LIMIT = 1",
                new BookRowMapper(), authorid);
        return bookresult.stream().findFirst();
    }

    public static class BookRowMapper implements RowMapper<Books>{

        @Override
        public Books mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Books.builder()
                    .isbn(rs.getString("isbn"))
                    .title(rs.getString("title"))
                    .authorid(rs.getLong("authorid"))
                    .build();
        }
    }
}
