package com.example.bookHaven.service.mappers;

import com.example.bookHaven.config.GeneralMapperConfig;
import com.example.bookHaven.entity.BookCard;
import com.example.bookHaven.entity.dto.request.BookCardDTORequest;
import com.example.bookHaven.entity.dto.response.BookCardDTOResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = GeneralMapperConfig.class)
public interface BookCardMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "book", ignore = true)
    BookCard toEntity(BookCardDTORequest request);

    BookCardDTOResponse toResponse(BookCard entity);
}
