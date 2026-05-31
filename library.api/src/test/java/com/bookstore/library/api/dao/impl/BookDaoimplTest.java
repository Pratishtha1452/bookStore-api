package com.bookstore.library.api.dao.impl;

import com.bookstore.library.api.DAO.impl.BookDaoimpl;
import com.bookstore.library.api.TestDataUtil;
import com.bookstore.library.api.domain.Authors;
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
        Books book = TestDataUtil.createTestBooks();
        booktest.create(book);

        verify(jdbcTemplate).update(
                "INSERT into books(isbn, title, author_id) VALUES(?, ?, ?)",
                "A1B2", "Masque of Red Death", 1L
        );
    }

    @Test
    public void testThatFindOneGeneratesCorrectSql(){
        booktest.findOne("A1B2");

        verify(jdbcTemplate).query(
                eq("SELECT isbn, title, author_id FROM Books WHERE isbn = ? LIMIT 1"),
                ArgumentMatchers.<BookDaoimpl.BookRowMapper>any(),
                eq("A1B2")
        );
    }

    @Test
    public void findManyThatGeneratesTheCorrectSQL(){
        booktest.find();

        verify(jdbcTemplate).query(
                eq("SELECT isbn, title, author_id FROM Books"),
                ArgumentMatchers.<BookDaoimpl.BookRowMapper>any()
        );
    }

    @Test
    public void TestThatBooksCanBeUpdated(){
        Books book = TestDataUtil.createTestBooks();
        booktest.update(1L, book);

        verify(jdbcTemplate).update(
                "UPDATE Books set isbn = ?, title = ?, author_id = ? WHERE author_id = ?",
                "A1B2", "Masque of Red Death", 1L, 1L

        );
    }
}
