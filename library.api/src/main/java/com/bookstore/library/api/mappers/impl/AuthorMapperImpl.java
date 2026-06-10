package com.bookstore.library.api.mappers.impl;

import com.bookstore.library.api.domain.dto.AuthorDto;
import com.bookstore.library.api.domain.entities.AuthorsEntity;
import com.bookstore.library.api.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapperImpl implements Mapper<AuthorsEntity, AuthorDto> {

    private ModelMapper modelMapper;

    public AuthorMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public AuthorDto mapTo(AuthorsEntity authorsEntity) {
        return modelMapper.map(authorsEntity, AuthorDto.class);
    }

    @Override
    public AuthorsEntity mapFrom(AuthorDto authorDto) {
        return modelMapper.map(authorDto, AuthorsEntity.class);
    }
}
