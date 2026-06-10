package com.bookstore.library.api.repositories;

import com.bookstore.library.api.domain.entities.AuthorsEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<AuthorsEntity, Long> {
    Iterable<AuthorsEntity> ageLessThan(int age);

    @Query("SELECT a from Authors a where a.age > ?1")//HQL QUERY
    Iterable<AuthorsEntity> findAuthorsWithAgeGreaterThan(int age);
}
