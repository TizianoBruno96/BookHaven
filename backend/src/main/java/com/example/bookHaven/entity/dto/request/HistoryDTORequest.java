package com.example.bookHaven.entity.dto.request;

import com.example.bookHaven.entity.Book;
import com.example.bookHaven.entity.Reader;
import lombok.Data;

import java.time.Instant;

@Data
public class HistoryDTORequest {
    private Instant StartedReadAt;
    private Instant last_read_at;
    private Boolean isRead;
    private String readerId;
    private String bookId;
}
