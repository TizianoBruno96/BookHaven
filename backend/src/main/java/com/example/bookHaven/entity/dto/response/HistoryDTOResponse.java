package com.example.bookHaven.entity.dto.response;

import lombok.Data;

import java.time.Instant;

@Data
public class HistoryDTOResponse {
    private String id;
    private Instant startedReadAt;
    private Instant last_read_at;
    private Boolean isRead;
}
