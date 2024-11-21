package com.example.bookHaven.entity.dto.request;

import com.example.bookHaven.entity.Review;
import lombok.Data;

import java.time.Instant;

@Data
public class ReviewDTORequest {
    private Review.Rating rating;
    private String comment;
    private Instant createdAt;
    private String readerId;
    private String bookId;
}
