package com.example.bookHaven.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "book_card")
public class BookCard {

    @Id
    @Column(name = "book_card_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotNull
    @Column(name = "title")
    private String Title;

    @NotNull
    @Column(name = "body", length = 2000)
    private String body;

    @Lob
    @Column(name = "image")
    private byte[] image;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "book_id")
    private Book book;
}
