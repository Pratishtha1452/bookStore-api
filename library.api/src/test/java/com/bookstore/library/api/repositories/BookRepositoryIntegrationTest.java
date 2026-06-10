package com.bookstore.library.api.repositories;


import com.bookstore.library.api.TestDataUtil;
import com.bookstore.library.api.domain.entities.AuthorsEntity;
import com.bookstore.library.api.domain.entities.BooksEntity;
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

        AuthorsEntity author = TestDataUtil.createTestAuthor();
        authorUnderTest.save(author);

        BooksEntity book = TestDataUtil.createTestBooks(author);
        bookUnderTest.save(book);

        Optional<BooksEntity> result= bookUnderTest.findById(book.getIsbn());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }

    @Test
    public void testThatMultipleBooksCanBeCreeatedAdRecalled(){
        AuthorsEntity author = TestDataUtil.createTestAuthor();
        authorUnderTest.save(author);
        AuthorsEntity authorB = TestDataUtil.createTestAuthorB();
        authorUnderTest.save(authorB);
        AuthorsEntity authorC = TestDataUtil.createTestAuthorC();
        authorUnderTest.save(authorC);

        BooksEntity book = TestDataUtil.createTestBooks(author);
        bookUnderTest.save(book);

        BooksEntity bookB = TestDataUtil.createTestBooksB(authorB);
        bookUnderTest.save(bookB);

        BooksEntity bookC = TestDataUtil.createTestBooksC(authorC);
        bookUnderTest.save(bookC);

        Iterable<BooksEntity> result = bookUnderTest.findAll();
        assertThat(result).containsExactly(book, bookB, bookC)
                .hasSize(3);
    }

    @Test
    public void testThatBooksCanBeUpdated(){
        AuthorsEntity authorsEntity = TestDataUtil.createTestAuthor();
        authorUnderTest.save(authorsEntity);
        BooksEntity booksEntity = TestDataUtil.createTestBooks(authorsEntity);
        bookUnderTest.save(booksEntity);

        booksEntity.setTitle("UPDATED");
        bookUnderTest.save(booksEntity);
        Optional<BooksEntity> result = bookUnderTest.findById(booksEntity.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(booksEntity);
    }

    @Test
    public void testThatBooksCanBeDeleted(){
        AuthorsEntity authorsEntity = TestDataUtil.createTestAuthor();
        authorUnderTest.save(authorsEntity);
        BooksEntity booksEntity = TestDataUtil.createTestBooks(authorsEntity);
        bookUnderTest.save(booksEntity);

        bookUnderTest.deleteById(booksEntity.getIsbn());
        Optional<BooksEntity> result = bookUnderTest.findById(booksEntity.getIsbn());
        assertThat(result).isEmpty();
    }


}