package com.example.bookHaven.service.utils.todto;

import com.example.bookHaven.config.GeneralMapperConfig;
import com.example.bookHaven.entity.Book;
import com.example.bookHaven.entity.dto.response.BookDTOResponse;
import org.mapstruct.Mapper;

@Mapper(config = GeneralMapperConfig.class)
public interface BookToDtoMapper {

    BookDTOResponse toDto(Book entity);
}
