package com.bookstore.library.api;

import com.bookstore.library.api.domain.Authors;
import com.bookstore.library.api.domain.Books;

public final class TestDataUtil {

    private TestDataUtil(){

    }

    public static Authors createTestAuthor() {
        return Authors.builder()
                .id(1L)
                .name("Abegale rose")
                .age(20)
                .build();
    }

    public static Authors createTestAuthorB() {
        return Authors.builder()
                .id(2L)
                .name("Rosey")
                .age(24)
                .build();
    }

    public static Authors createTestAuthorC() {
        return Authors.builder()
                .id(3L)
                .name("Merlin")
                .age(120)
                .build();
    }



    public static Books createTestBooks() {
        return Books.builder()
                .isbn("A1B2")
                .title("Masque of Red Death")
                .author_id(1L)
                .build();
    }

    public static Books createTestBooksB() {
        return Books.builder()
                .isbn("C3D4")
                .title("HELLO!")
                .author_id(2L)
                .build();
    }

    public static Books createTestBooksC() {
        return Books.builder()
                .isbn("E5F6")
                .title("Merlin's Beard")
                .author_id(3L)
                .build();
    }
}
