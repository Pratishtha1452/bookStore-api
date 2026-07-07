package com.bookstore.library.api.repositories;

import com.bookstore.library.api.TestDataUtil;
import com.bookstore.library.api.domain.entities.AuthorsEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorRepositoryIntegrationTest {

    private AuthorRepository underTest;

    @Autowired
    public AuthorRepositoryIntegrationTest(AuthorRepository underTest){
        this.underTest = underTest;
    }


    @Test
    public void testThatAuthorCanBeImplementedAndRecalled(){
        AuthorsEntity authorsEntity = TestDataUtil.createTestAuthor();

        underTest.save(authorsEntity);
        Optional<AuthorsEntity> result = underTest.findById(authorsEntity.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(authorsEntity);
    }

    @Test
    public void testThatMultipleAuthorsCanBeCreatedAdRecalled(){
        AuthorsEntity author = TestDataUtil.createTestAuthor();
        underTest.save(author);
        AuthorsEntity authorB = TestDataUtil.createTestAuthorB();
        underTest.save(authorB);
        AuthorsEntity authorC = TestDataUtil.createTestAuthorC();
        underTest.save(authorC);

        Iterable<AuthorsEntity> result = underTest.findAll();
        assertThat(result).hasSize(3)
        .containsExactly(author, authorB, authorC);
    }

    @Test
    public void testThatAuthorCanBeUpdated(){
        AuthorsEntity author = TestDataUtil.createTestAuthor();
        underTest.save(author);
        author.setName("UPDATED");
        underTest.save(author);
        Optional<AuthorsEntity> result = underTest.findById(author.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }

    @Test
    public void testThatAuthorCanBeDeleted(){
        AuthorsEntity authorsEntity = TestDataUtil.createTestAuthor();
        underTest.save(authorsEntity);

        underTest.deleteById(authorsEntity.getId());
        Optional<AuthorsEntity> result = underTest.findById(authorsEntity.getId());
        assertThat(result).isEmpty();
    }

    @Test
    public void getAuthorsWithAgeLessThan(){
        AuthorsEntity authorsEntity = TestDataUtil.createTestAuthor();
        underTest.save(authorsEntity);
        AuthorsEntity authorsEntity1 = TestDataUtil.createTestAuthorB();
        underTest.save(authorsEntity1);
        AuthorsEntity authorsEntity2 = TestDataUtil.createTestAuthorC();
        underTest.save(authorsEntity2);

        Iterable<AuthorsEntity> result = underTest.ageLessThan(50);
        assertThat(result).containsExactly(authorsEntity, authorsEntity1);

    }

    @Test
    public void testThatGetAuthorsWithAgeGreaterThan(){
        AuthorsEntity authorsEntity = TestDataUtil.createTestAuthor();
        underTest.save(authorsEntity);
        AuthorsEntity authorsEntity1 = TestDataUtil.createTestAuthorB();
        underTest.save(authorsEntity1);
        AuthorsEntity authorsEntity2 = TestDataUtil.createTestAuthorC();
        underTest.save(authorsEntity2);

        Iterable<AuthorsEntity> result = underTest.findAuthorsWithAgeGreaterThan(100);
        assertThat(result).containsExactly(authorsEntity2);

    }
}
