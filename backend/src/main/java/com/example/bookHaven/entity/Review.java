package com.example.bookHaven.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;

@Entity
@Data
@Table(name = "review")
public class Review {

    @Id
    @Column(name = "review_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "rating")
    private Rating rating;

    @NotNull
    @Column(name = "comment", length = 2000)
    private String comment;

    @Column(name = "created_at")
    private Instant createdAt;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "reader_id")
    private Reader reader;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "book_id")
    private Book book;

    public enum Rating {
        VERY_BAD,
        BAD,
        DECENT,
        GOOD,
        EXCELLENT
    }
}
