package com.example.bookHaven.service.utils.todto;

import com.example.bookHaven.config.GeneralMapperConfig;
import com.example.bookHaven.entity.Reader;
import com.example.bookHaven.entity.dto.response.ReaderDTOResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(config = GeneralMapperConfig.class)
public interface ReaderToDtoMapper {

    @Mapping(target = "friendIds", source = "friends")
    ReaderDTOResponse toResponse(Reader entity);

    default List<String> mapFriendsToIds(List<Reader> friends) {
        return friends.stream()
                .map(Reader::getId)
                .collect(Collectors.toList());
    }
}
