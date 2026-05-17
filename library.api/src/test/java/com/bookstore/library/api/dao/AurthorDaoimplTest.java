package com.bookstore.library.api.dao;

import com.bookstore.library.api.DAO.impl.AuthorDaoimpl;
import com.bookstore.library.api.domain.Authors;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AurthorDaoimplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private AuthorDaoimpl underTest;

    @Test
    public void testThatCreateAuthorGeneratesCorrectSql(){
        Authors authors = Authors.builder()
        .id(1L)
        .name("Abegale rose")
        .age(20)
        .build();
        underTest.create(authors);

        verify(jdbcTemplate).update(
                "INSERT into authors(id, name, age) VALUES(?, ?, ?)",
                1L,"Abegale rose", 20
        );
    }
}
