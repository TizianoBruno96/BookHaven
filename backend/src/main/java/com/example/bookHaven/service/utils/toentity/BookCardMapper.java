package com.example.bookHaven.service.utils.toentity;

import com.example.bookHaven.config.GeneralMapperConfig;
import com.example.bookHaven.entity.BookCard;
import com.example.bookHaven.entity.dto.request.BookCardDTORequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = GeneralMapperConfig.class)
public interface BookCardMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "book", ignore = true)
    BookCard toEntity(BookCardDTORequest request);
}
