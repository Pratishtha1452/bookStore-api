package com.bookstore.library.api.repositories;

import com.bookstore.library.api.domain.entities.BooksEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<BooksEntity, String> {

}
