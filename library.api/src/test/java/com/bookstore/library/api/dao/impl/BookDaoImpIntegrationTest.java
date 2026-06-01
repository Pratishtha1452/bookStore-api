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

import java.util.List;
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

    @Test
    public void testThatMultipleBooksCanBeCreeatedAdRecalled(){
        Authors author = TestDataUtil.createTestAuthor();
        authorUnderTest.create(author);
        Authors authorB = TestDataUtil.createTestAuthorB();
        authorUnderTest.create(authorB);
        Authors authorC = TestDataUtil.createTestAuthorC();
        authorUnderTest.create(authorC);

        Books book = TestDataUtil.createTestBooks();
        bookUnderTest.create(book);

        Books bookB = TestDataUtil.createTestBooksB();
        bookUnderTest.create(bookB);

        Books bookC = TestDataUtil.createTestBooksC();
        bookUnderTest.create(bookC);

        List<Books> result = bookUnderTest.find();
        assertThat(result).containsExactly(book, bookB, bookC)
                .hasSize(3);
    }

    @Test
    public void testThatBooksCanBeUpdated(){
        Authors authors = TestDataUtil.createTestAuthor();
        authorUnderTest.create(authors);
        Books books = TestDataUtil.createTestBooks();
        bookUnderTest.create(books);

        books.setTitle("UPDATED");
        bookUnderTest.update(books.getAuthor_id(), books);
        Optional<Books> result = bookUnderTest.findOne(books.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(books);
    }


}
