package com.example.bookHaven.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "book")
public class Book {

    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotNull
    @Column(name = "title")
    private String title;

    @NotNull
    @Column(name = "num_pages")
    private Integer pages;

    @NotNull
    @Column(name = "description", length = 2000)
    private String description;

    @NotNull
    @Column(name = "genre")
    private String genre;

    @NotNull
    @Column(name = "author")
    private String author;

    @NotNull
    @Column(name = "url")
    private String url;

    @NotNull
    @Column(name = "published_year")
    private Date publishedYear;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<History> histories = new ArrayList<>();

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookCard> bookCards = new ArrayList<>();
}
