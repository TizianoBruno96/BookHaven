package com.example.bookHaven.entity.dto.request;

import lombok.Data;

@Data
public class BookCardDTORequest {
    private String Title;
    private String body;
    private byte[] image;
    private String bookId;
}
