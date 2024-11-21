package com.example.bookHaven.service.mappers;

import com.example.bookHaven.config.GeneralMapperConfig;
import com.example.bookHaven.entity.Book;
import com.example.bookHaven.entity.dto.request.BookDTORequest;
import com.example.bookHaven.entity.dto.response.BookDTOResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = GeneralMapperConfig.class)
public interface BookMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "histories", ignore = true)
    @Mapping(target = "bookCards", ignore = true)
    Book toEntity(BookDTORequest request);

    BookDTOResponse toDto(Book entity);
}
