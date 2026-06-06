//package com.bookstore.library.api.repositories;
//
//import com.bookstore.library.api.DAO.impl.AuthorDaoimpl;
//import com.bookstore.library.api.TestDataUtil;
//import com.bookstore.library.api.domain.Authors;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//@ExtendWith(SpringExtension.class)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//public class AuthorDaoImplIntegrationTest {
//
//    private AuthorDaoimpl underTest;
//
//    @Autowired
//    public AuthorDaoImplIntegrationTest(AuthorDaoimpl underTest){
//        this.underTest = underTest;
//    }
//
//
//    @Test
//    public void testThatAuthorCanBeImplementedAndRecalled(){
//        Authors authors = TestDataUtil.createTestAuthor();
//
//        underTest.create(authors);
//        Optional<Authors> result = underTest.findOne(authors.getId());
//        assertThat(result).isPresent();
//        assertThat(result.get()).isEqualTo(authors);
//    }
//
//    @Test
//    public void testThatMultipleAuthorsCanBeCreeatedAdRecalled(){
//        Authors author = TestDataUtil.createTestAuthor();
//        underTest.create(author);
//        Authors authorB = TestDataUtil.createTestAuthorB();
//        underTest.create(authorB);
//        Authors authorC = TestDataUtil.createTestAuthorC();
//        underTest.create(authorC);
//
//        List<Authors> result = underTest.find();
//        assertThat(result).hasSize(3)
//        .containsExactly(author, authorB, authorC);
//    }
//
//    @Test
//    public void testThatAuthorCanbeUpdated(){
//        Authors author = TestDataUtil.createTestAuthor();
//        underTest.create(author);
//        author.setName("UPDATED");
//        underTest.update(author.getId(), author);
//        Optional<Authors> result = underTest.findOne(author.getId());
//        assertThat(result).isPresent();
//        assertThat(result.get()).isEqualTo(author);
//    }
//
//    @Test
//    public void testThatAuthorCanBeDeleted(){
//        Authors authors = TestDataUtil.createTestAuthor();
//        underTest.create(authors);
//
//        underTest.delete(authors.getId());
//        Optional<Authors> result = underTest.findOne(authors.getId());
//        assertThat(result).isEmpty();
//    }
//}
