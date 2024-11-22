package com.example.bookHaven.service;

import com.example.bookHaven.entity.dto.request.BookDTORequest;
import com.example.bookHaven.entity.dto.response.BookDTOResponse;

import java.util.List;

public interface IBookService {
    BookDTOResponse create(BookDTORequest request);

    BookDTOResponse update(BookDTORequest request);

    BookDTOResponse findById(String id);

    List<BookDTOResponse> searchBooks(String title, String genre, String author);

    boolean existsById(String id);

    boolean existsByTitle(String title);

    boolean existsByGenre(String genre);

    boolean existsByAuthor(String author);

    boolean deleteById(String id);

    boolean deleteByTitle(String title);

    boolean deleteByGenre(String genre);

    boolean deleteByAuthor(String author);

    long count();

    List<BookDTOResponse> listAll();
}
