package com.bookstore.library.api.repositories;

import com.bookstore.library.api.domain.Authors;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Authors, Long> {
}
