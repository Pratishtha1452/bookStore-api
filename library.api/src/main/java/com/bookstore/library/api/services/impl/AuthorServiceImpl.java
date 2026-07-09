package com.bookstore.library.api.services.impl;

import com.bookstore.library.api.domain.entities.AuthorsEntity;
import com.bookstore.library.api.repositories.AuthorRepository;
import com.bookstore.library.api.services.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AuthorServiceImpl implements AuthorService{

    private AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorsEntity saveAuthor(AuthorsEntity authorsEntity) {
        return authorRepository.save(authorsEntity);
    }

    @Override
    public List<AuthorsEntity> findAll() {
        return StreamSupport.stream(authorRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Optional<AuthorsEntity> findOne(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return authorRepository.existsById(id);
    }

    @Override
    public AuthorsEntity partialUpdate(Long id, AuthorsEntity authorsEntity) {
        authorsEntity.setId(id);

        //find if author exists -> if yes (using map)grab the object and name it existingAuthor and run the code inside curly braces
        return authorRepository.findById(id).map(existingAuthor ->{
            //findById return an Optional since it either returns the object or null -> get the attribute
            //if it is present set the value from the object
            Optional.ofNullable(authorsEntity.getName()).ifPresent(existingAuthor::setName);
            Optional.ofNullable(authorsEntity.getAge()).ifPresent(existingAuthor::setAge);
            //after updation save it
            return authorRepository.save(existingAuthor);

            //just in case -> though not likely = return a runtime exception for when author does not exist
        }).orElseThrow(() -> new RuntimeException("Author does not exists"));
    }

    @Override
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }


}



