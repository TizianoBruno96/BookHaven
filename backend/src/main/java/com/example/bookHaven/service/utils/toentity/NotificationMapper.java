package com.example.bookHaven.service.utils.toentity;

import com.example.bookHaven.config.GeneralMapperConfig;
import com.example.bookHaven.entity.Notification;
import com.example.bookHaven.entity.dto.request.NotificationDTORequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = GeneralMapperConfig.class)
public interface NotificationMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "reader", ignore = true)
    Notification toEntity(NotificationDTORequest request);
}
