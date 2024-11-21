package com.example.bookHaven.entity.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class BookDTOResponse {
    private String id;
    private String title;
    private Integer pages;
    private String description;
    private String genre;
    private String author;
    private String url;
    private Date publishedYear;
}
