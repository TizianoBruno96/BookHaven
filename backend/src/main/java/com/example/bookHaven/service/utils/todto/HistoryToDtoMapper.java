package com.example.bookHaven.service.utils.todto;

import com.example.bookHaven.config.GeneralMapperConfig;
import com.example.bookHaven.entity.History;
import com.example.bookHaven.entity.dto.response.HistoryDTOResponse;
import org.mapstruct.Mapper;

@Mapper(config = GeneralMapperConfig.class)
public interface HistoryToDtoMapper {

    HistoryDTOResponse toDto(History entity);
}
