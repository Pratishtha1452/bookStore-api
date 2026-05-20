package com.bookstore.library.api;

import com.bookstore.library.api.domain.Authors;

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
}
