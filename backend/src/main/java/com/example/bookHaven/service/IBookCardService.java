package com.example.bookHaven.service;

import com.example.bookHaven.entity.dto.request.BookCardDTORequest;
import com.example.bookHaven.entity.dto.request.BookDTORequest;
import com.example.bookHaven.entity.dto.response.BookCardDTOResponse;

import java.util.List;

public interface IBookCardService {

    BookCardDTOResponse create(BookCardDTORequest request);

    BookCardDTOResponse update(BookCardDTORequest request);

    BookCardDTOResponse findById(String id);

    List<BookCardDTOResponse> findByBook(String bookId);

    List<BookCardDTOResponse> findByBook(BookDTORequest bookRequest);

    boolean existsById(String id);

    boolean existsByBook(String bookId);

    boolean existsByBook(BookDTORequest bookRequest);

    boolean deleteById(String id);

    boolean deleteByBook(String bookId);

    boolean deleteByBook(BookDTORequest bookRequest);

    List<BookCardDTOResponse> listAll();

    int count();
}
