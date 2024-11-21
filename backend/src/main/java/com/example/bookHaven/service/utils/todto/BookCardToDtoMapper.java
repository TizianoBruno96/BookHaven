package com.example.bookHaven.service.utils.todto;

import com.example.bookHaven.config.GeneralMapperConfig;
import com.example.bookHaven.entity.BookCard;
import com.example.bookHaven.entity.dto.response.BookCardDTOResponse;
import org.mapstruct.Mapper;

@Mapper(config = GeneralMapperConfig.class)
public interface BookCardToDtoMapper {

    BookCardDTOResponse toDto(BookCard entity);
}
