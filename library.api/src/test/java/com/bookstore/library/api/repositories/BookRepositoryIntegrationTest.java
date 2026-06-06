package com.bookstore.library.api.repositories;


import com.bookstore.library.api.TestDataUtil;
import com.bookstore.library.api.domain.Authors;
import com.bookstore.library.api.domain.Books;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Iterator;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookRepositoryIntegrationTest {

    private BookRepository bookUnderTest;
    private AuthorRepository authorUnderTest;

    @Autowired
    public BookRepositoryIntegrationTest(BookRepository bookUnderTest, AuthorRepository authorUnderTest) {
        this.bookUnderTest = bookUnderTest;
        this.authorUnderTest = authorUnderTest;
    }



    @Test
    public void testThatBookCanBeImplementedAndRecalled(){

        Authors author = TestDataUtil.createTestAuthor();
        authorUnderTest.save(author);

        Books book = TestDataUtil.createTestBooks(author);
        bookUnderTest.save(book);

        Optional<Books> result= bookUnderTest.findById(book.getIsbn());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }

    @Test
    public void testThatMultipleBooksCanBeCreeatedAdRecalled(){
        Authors author = TestDataUtil.createTestAuthor();
        authorUnderTest.save(author);
        Authors authorB = TestDataUtil.createTestAuthorB();
        authorUnderTest.save(authorB);
        Authors authorC = TestDataUtil.createTestAuthorC();
        authorUnderTest.save(authorC);

        Books book = TestDataUtil.createTestBooks(author);
        bookUnderTest.save(book);

        Books bookB = TestDataUtil.createTestBooksB(authorB);
        bookUnderTest.save(bookB);

        Books bookC = TestDataUtil.createTestBooksC(authorC);
        bookUnderTest.save(bookC);

        Iterable<Books> result = bookUnderTest.findAll();
        assertThat(result).containsExactly(book, bookB, bookC)
                .hasSize(3);
    }

    @Test
    public void testThatBooksCanBeUpdated(){
        Authors authors = TestDataUtil.createTestAuthor();
        authorUnderTest.save(authors);
        Books books = TestDataUtil.createTestBooks(authors);
        bookUnderTest.save(books);

        books.setTitle("UPDATED");
        bookUnderTest.save(books);
        Optional<Books> result = bookUnderTest.findById(books.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(books);
    }

    @Test
    public void testThatBooksCanBeDeleted(){
        Authors authors = TestDataUtil.createTestAuthor();
        authorUnderTest.save(authors);
        Books books = TestDataUtil.createTestBooks(authors);
        bookUnderTest.save(books);

        bookUnderTest.deleteById(books.getIsbn());
        Optional<Books> result = bookUnderTest.findById(books.getIsbn());
        assertThat(result).isEmpty();
    }


}