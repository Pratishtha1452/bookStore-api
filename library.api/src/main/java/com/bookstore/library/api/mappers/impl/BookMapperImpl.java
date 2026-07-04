package com.bookstore.library.api.mappers.impl;

import com.bookstore.library.api.domain.dto.BookDto;
import com.bookstore.library.api.domain.entities.BooksEntity;
import com.bookstore.library.api.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BookMapperImpl implements Mapper<BooksEntity, BookDto> {

    private ModelMapper modelMapper;

    public BookMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Override
    public BookDto mapTo(BooksEntity booksEntity) {
        return modelMapper.map(booksEntity, BookDto.class);
    }

    @Override
    public BooksEntity mapFrom(BookDto bookDto) {
        return modelMapper.map(bookDto, BooksEntity.class);
    }
}
