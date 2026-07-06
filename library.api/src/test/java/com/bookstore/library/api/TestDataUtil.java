package com.bookstore.library.api;

import com.bookstore.library.api.domain.entities.AuthorsEntity;
import com.bookstore.library.api.domain.entities.BooksEntity;

public final class TestDataUtil {

    private TestDataUtil(){

    }

    public static AuthorsEntity createTestAuthor() {
        return AuthorsEntity.builder()
                .name("Abegale rose")
                .age(20)
                .build();
    }

    public static AuthorsEntity createTestAuthorB() {
        return AuthorsEntity.builder()
                .name("Fyodor")
                .age(24)
                .build();
    }

    public static AuthorsEntity createTestAuthorC() {
        return AuthorsEntity.builder()
                .name("Merlin")
                .age(120)
                .build();
    }



    public static BooksEntity createTestBooks(final AuthorsEntity author) {
        return BooksEntity.builder()
                .isbn("A1B2")
                .title("Masque of Red Death")
                .author(author)
                .build();
    }

    public static BooksEntity createTestBooksB(final AuthorsEntity author) {
        return BooksEntity.builder()
                .isbn("C3D4")
                .title("Notes from the underground")
                .author(author)
                .build();
    }

    public static BooksEntity createTestBooksC(final AuthorsEntity author) {
        return BooksEntity.builder()
                .isbn("E5F6")
                .title("Merlin's Beard")
                .author(author)
                .build();
    }
}
