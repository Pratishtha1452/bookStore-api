package com.bookstore.library.api;

import com.bookstore.library.api.domain.Authors;
import com.bookstore.library.api.domain.Books;

public final class TestDataUtil {

    private TestDataUtil(){

    }

    public static Authors createTestAuthor() {
        return Authors.builder()
                .name("Abegale rose")
                .age(20)
                .build();
    }

    public static Authors createTestAuthorB() {
        return Authors.builder()
                .name("Rosey")
                .age(24)
                .build();
    }

    public static Authors createTestAuthorC() {
        return Authors.builder()
                .name("Merlin")
                .age(120)
                .build();
    }



    public static Books createTestBooks(final Authors author) {
        return Books.builder()
                .isbn("A1B2")
                .title("Masque of Red Death")
                .author(author)
                .build();
    }

    public static Books createTestBooksB(final Authors author) {
        return Books.builder()
                .isbn("C3D4")
                .title("HELLO!")
                .author(author)
                .build();
    }

    public static Books createTestBooksC(final Authors author) {
        return Books.builder()
                .isbn("E5F6")
                .title("Merlin's Beard")
                .author(author)
                .build();
    }
}
