package com.example.bookHaven.entity.dto.request;

import lombok.Data;

import java.time.Instant;

@Data
public class NotificationDTORequest {
    private String id;
    private String message;
    private Instant createdAt;
    private Boolean isRead;
    private String readerId;
}
