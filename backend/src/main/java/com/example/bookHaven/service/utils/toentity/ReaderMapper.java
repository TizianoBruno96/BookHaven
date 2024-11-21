package com.example.bookHaven.service.utils.toentity;

import com.example.bookHaven.config.GeneralMapperConfig;
import com.example.bookHaven.entity.Reader;
import com.example.bookHaven.entity.dto.request.ReaderDTORequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = GeneralMapperConfig.class)
public interface ReaderMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "notifications", ignore = true)
    @Mapping(target = "histories", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "friends", ignore = true)
    Reader toEntity(ReaderDTORequest request);
}
