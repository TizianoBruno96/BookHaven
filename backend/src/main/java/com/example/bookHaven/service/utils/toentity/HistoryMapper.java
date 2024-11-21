package com.example.bookHaven.service.utils.toentity;

import com.example.bookHaven.config.GeneralMapperConfig;
import com.example.bookHaven.entity.History;
import com.example.bookHaven.entity.dto.request.HistoryDTORequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = GeneralMapperConfig.class)
public interface HistoryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "reader", ignore = true)
    @Mapping(target = "book", ignore = true)
    History toEntity(HistoryDTORequest request);
}
