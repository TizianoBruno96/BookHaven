package com.example.bookHaven.service.mappers;

import com.example.bookHaven.entity.History;
import com.example.bookHaven.entity.dto.request.HistoryDTORequest;
import com.example.bookHaven.entity.dto.response.HistoryDTOResponse;
import org.mapstruct.Mapping;

public interface HistoryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "reader", ignore = true)
    @Mapping(target = "book", ignore = true)
    History toEntity(HistoryDTORequest request);

    HistoryDTOResponse toDto(History entity);
}
