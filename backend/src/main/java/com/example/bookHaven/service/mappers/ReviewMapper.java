package com.example.bookHaven.service.mappers;

import com.example.bookHaven.config.GeneralMapperConfig;
import com.example.bookHaven.entity.Review;
import com.example.bookHaven.entity.dto.request.ReviewDTORequest;
import com.example.bookHaven.entity.dto.response.ReviewDTOResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = GeneralMapperConfig.class)
public interface ReviewMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "reader", ignore = true)
    @Mapping(target = "book", ignore = true)
    Review toEntity(ReviewDTORequest request);

    ReviewDTOResponse toResponse(Review entity);
}
