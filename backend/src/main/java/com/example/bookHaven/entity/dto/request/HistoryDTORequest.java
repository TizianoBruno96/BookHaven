package com.example.bookHaven.entity.dto.request;

import lombok.Data;

import java.time.Instant;

@Data
public class HistoryDTORequest {
    private Instant startedReadAt;
    private Instant last_read_at;
    private Boolean isRead;
    private String readerId;
    private String bookId;
}
