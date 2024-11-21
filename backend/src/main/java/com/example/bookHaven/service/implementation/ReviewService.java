package com.example.bookHaven.service.implementation;

import com.example.bookHaven.entity.Book;
import com.example.bookHaven.entity.Reader;
import com.example.bookHaven.entity.Review;
import com.example.bookHaven.repository.ReviewRepository;
import com.example.bookHaven.service.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService implements IReviewService {

    @Autowired
    ReviewRepository repository;

    @Override
    public boolean create(Review review) {
        return false;
    }

    @Override
    public boolean update(Review review) {
        return false;
    }

    @Override
    public Review findById(String id) {
        return null;
    }

    @Override
    public List<Review> findByBook(String bookId) {
        return List.of();
    }

    @Override
    public List<Review> findByBook(Book book) {
        return List.of();
    }

    @Override
    public List<Review> findByReader(String readerId) {
        return List.of();
    }

    @Override
    public List<Review> findByReader(Reader reader) {
        return List.of();
    }

    @Override
    public boolean existsById(String id) {
        return false;
    }

    @Override
    public boolean existsByBook(String bookId) {
        return false;
    }

    @Override
    public boolean existsByBook(Book book) {
        return false;
    }

    @Override
    public boolean existsByReader(String readerId) {
        return false;
    }

    @Override
    public boolean existsByReader(Reader reader) {
        return false;
    }

    @Override
    public boolean deleteById(String id) {
        return false;
    }

    @Override
    public boolean deleteByBook(String bookId) {
        return false;
    }

    @Override
    public boolean deleteByBook(Book book) {
        return false;
    }

    @Override
    public boolean deleteByReader(String readerId) {
        return false;
    }

    @Override
    public boolean deleteByReader(Reader reader) {
        return false;
    }

    @Override
    public List<Review> listAll() {
        return List.of();
    }

    @Override
    public int could() {
        return 0;
    }
}
