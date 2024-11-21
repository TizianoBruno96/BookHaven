package com.example.bookHaven.service.utils.todto;

import com.example.bookHaven.config.GeneralMapperConfig;
import com.example.bookHaven.entity.Notification;
import com.example.bookHaven.entity.dto.response.NotificationDTOResponse;
import org.mapstruct.Mapper;

@Mapper(config = GeneralMapperConfig.class)
public interface NotificationToDtoMapper {

    NotificationDTOResponse toDto(Notification entity);
}
