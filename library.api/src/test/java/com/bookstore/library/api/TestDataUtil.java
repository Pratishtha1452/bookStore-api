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


    public static Books createTestBooks() {
        return Books.builder()
                .isbn("A1B2")
                .title("Masque of Red Death")
                .author_id(1L)
                .build();
    }
}
