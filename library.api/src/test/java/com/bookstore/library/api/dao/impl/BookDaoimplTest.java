package com.bookstore.library.api.dao.impl;

import com.bookstore.library.api.DAO.impl.BookDaoimpl;
import com.bookstore.library.api.domain.Books;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookDaoimplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private BookDaoimpl booktest;

    @Test
    public void testThatCreateAuthorGeneratesCorrectSql(){
        Books book = Books.builder()
                .isbn("A1B2")
                .title("Masque of Red Death")
                .authorid(1L)
                .build();
        booktest.create(book);

        verify(jdbcTemplate).update(
                "INSERT into books(isbn, title, authorid) VALUES(?, ?, ?)",
                "A1B2", "Masque of Red Death", 1L
        );
    }

    @Test
    public void testThatFindOneGeneratesCorrectSql(){
        booktest.findOne(1L);

        verify(jdbcTemplate).query(
                eq("SELECT isbn, title, authorid FROM Books WHERE authorid = ? LIMIT = 1"),
                ArgumentMatchers.<BookDaoimpl.BookRowMapper>any(),
                eq(1L)
        );
    }
}
