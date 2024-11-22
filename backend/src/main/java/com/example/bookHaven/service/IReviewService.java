package com.example.bookHaven.service;

import com.example.bookHaven.entity.dto.request.BookDTORequest;
import com.example.bookHaven.entity.dto.request.ReaderDTORequest;
import com.example.bookHaven.entity.dto.request.ReviewDTORequest;
import com.example.bookHaven.entity.dto.response.ReviewDTOResponse;

import java.util.List;

public interface IReviewService {

    ReviewDTOResponse create(ReviewDTORequest request);

    ReviewDTOResponse update(ReviewDTORequest request);

    ReviewDTOResponse findById(String id);

    List<ReviewDTOResponse> findByBook(String bookId);

    List<ReviewDTOResponse> findByBook(BookDTORequest bookRequest);

    List<ReviewDTOResponse> findByReader(String readerId);

    List<ReviewDTOResponse> findByReader(ReaderDTORequest readerRequest);

    boolean existsById(String id);

    boolean existsByBook(String bookId);

    boolean existsByBook(BookDTORequest bookRequest);

    boolean existsByReader(String readerId);

    boolean existsByReader(ReaderDTORequest readerRequest);

    boolean deleteById(String id);

    boolean deleteByBook(String bookId);

    boolean deleteByBook(BookDTORequest bookrequest);

    boolean deleteByReader(String readerId);

    boolean deleteByReader(ReaderDTORequest readerRequest);

    List<ReviewDTOResponse> listAll();

    int count();
}
