package com.bookstore.library.api.repositories;

import com.bookstore.library.api.domain.Books;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Books, String> {

}
