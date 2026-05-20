package com.bookstore.library.api.dao.impl;

import com.bookstore.library.api.DAO.impl.AuthorDaoimpl;
import com.bookstore.library.api.DAO.impl.BookDaoimpl;
import com.bookstore.library.api.TestDataUtil;
import com.bookstore.library.api.domain.Authors;
import com.bookstore.library.api.domain.Books;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookDaoImpIntegrationTest {

    private BookDaoimpl bookUnderTest;
    private AuthorDaoimpl authorUnderTest;

    @Autowired
    public BookDaoImpIntegrationTest(BookDaoimpl bookUnderTest, AuthorDaoimpl authorUnderTest) {
        this.bookUnderTest = bookUnderTest;
        this.authorUnderTest = authorUnderTest;
    }



    @Test
    public void testThatBookCanBeImplementedAndRecalled(){

        Authors authors = TestDataUtil.createTestAuthor();
        authorUnderTest.create(authors);

        Books book = TestDataUtil.createTestBooks();
        bookUnderTest.create(book);

        Optional<Books> result= bookUnderTest.findOne(book.getIsbn());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }
}
