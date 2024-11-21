package com.example.bookHaven.service.utils.todto;

import com.example.bookHaven.config.GeneralMapperConfig;
import com.example.bookHaven.entity.Review;
import com.example.bookHaven.entity.dto.response.ReviewDTOResponse;
import org.mapstruct.Mapper;

@Mapper(config = GeneralMapperConfig.class)
public interface ReviewToDtoMapper {

    ReviewDTOResponse toDto(Review entity);
}
