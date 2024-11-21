package com.example.bookHaven.entity.dto.response;

import com.example.bookHaven.entity.Review;
import lombok.Data;

import java.time.Instant;

@Data
public class ReviewDTOResponse {
    private String id;
    private Review.Rating rating;
    private String comment;
    private Instant createdAt;
}
