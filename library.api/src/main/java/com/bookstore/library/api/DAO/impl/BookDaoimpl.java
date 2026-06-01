package com.bookstore.library.api.DAO.impl;

import com.bookstore.library.api.DAO.BookDao;
import com.bookstore.library.api.domain.Books;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class BookDaoimpl implements BookDao {

    private final JdbcTemplate jdbcTemplate;

    public BookDaoimpl(final JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Books book) {
        jdbcTemplate.update("INSERT into books(isbn, title, author_id) VALUES(?, ?, ?)",
                book.getIsbn(), book.getTitle(), book.getAuthor_id()
        );
    }

    @Override
    public Optional<Books> findOne(String isbn) {
        List<Books> bookresult = jdbcTemplate.query("SELECT isbn, title, author_id FROM Books WHERE isbn = ? LIMIT 1",
                new BookRowMapper(), isbn);
        return bookresult.stream().findFirst();
    }

    @Override
    public List<Books> find() {
        return jdbcTemplate.query(
                "SELECT isbn, title, author_id FROM Books",
                new BookRowMapper()
        );
    }

    @Override
    public void update(long author_id, Books book) {
        jdbcTemplate.update(
                "UPDATE Books set isbn = ?, title = ?, author_id = ? WHERE author_id = ?",
                book.getIsbn(), book.getTitle(), book.getAuthor_id(), book.getAuthor_id()
        );

    }

    @Override
    public void delete(long author_id) {
        jdbcTemplate.update(
                "DELETE FROM Books WHERE author_id = ?",
                author_id
        );
    }

    public static class BookRowMapper implements RowMapper<Books>{

        @Override
        public Books mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Books.builder()
                    .isbn(rs.getString("isbn"))
                    .title(rs.getString("title"))
                    .author_id(rs.getLong("author_id"))
                    .build();
        }
    }
}
