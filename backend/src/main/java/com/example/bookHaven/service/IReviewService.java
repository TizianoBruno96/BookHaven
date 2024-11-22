package com.example.bookHaven.service;

import com.example.bookHaven.entity.Book;
import com.example.bookHaven.entity.Reader;
import com.example.bookHaven.entity.Review;

import java.util.List;

public interface IReviewService {

    Review create(Review review);

    Review update(Review review);

    Review findById(String id);

    List<Review> findByBook(String bookId);

    List<Review> findByBook(Book book);

    List<Review> findByReader(String readerId);

    List<Review> findByReader(Reader reader);

    boolean existsById(String id);

    boolean existsByBook(String bookId);

    boolean existsByBook(Book book);

    boolean existsByReader(String readerId);

    boolean existsByReader(Reader reader);

    boolean deleteById(String id);

    boolean deleteByBook(String bookId);

    boolean deleteByBook(Book book);

    boolean deleteByReader(String readerId);

    boolean deleteByReader(Reader reader);

    List<Review> listAll();

    int count();
}
