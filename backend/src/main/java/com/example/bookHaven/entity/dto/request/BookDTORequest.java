package com.example.bookHaven.entity.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class BookDTORequest {
    private String title;
    private Integer pages;
    private String description;
    private String genre;
    private String author;
    private String url;
    private Date publishedYear;
}
