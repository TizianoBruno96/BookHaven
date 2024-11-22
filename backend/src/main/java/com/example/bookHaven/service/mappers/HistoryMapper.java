package com.example.bookHaven.service.mappers;

import com.example.bookHaven.config.GeneralMapperConfig;
import com.example.bookHaven.entity.History;
import com.example.bookHaven.entity.dto.request.HistoryDTORequest;
import com.example.bookHaven.entity.dto.response.HistoryDTOResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = GeneralMapperConfig.class)
public interface HistoryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "reader", ignore = true)
    @Mapping(target = "book", ignore = true)
    History toEntity(HistoryDTORequest request);

    HistoryDTOResponse toResponse(History entity);
}
