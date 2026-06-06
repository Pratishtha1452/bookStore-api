package com.bookstore.library.api.repositories;

import com.bookstore.library.api.domain.Authors;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Authors, Long> {
    Iterable<Authors> ageLessThan(int age);

    @Query("SELECT a from Authors a where a.age > ?1")//HQL QUERY
    Iterable<Authors> findAuthorsWithAgeGreaterThan(int age);
}
