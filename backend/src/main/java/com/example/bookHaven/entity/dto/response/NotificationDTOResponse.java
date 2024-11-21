package com.example.bookHaven.entity.dto.response;

import lombok.Data;

import java.time.Instant;

@Data
public class NotificationDTOResponse {
    private String id;
    private String message;
    private Instant createdAt;
    private Boolean isRead;
}
