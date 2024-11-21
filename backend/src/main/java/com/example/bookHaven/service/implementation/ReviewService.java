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
    private ReviewRepository repository;

    @Override
    public Review create(Review review) {
        return repository.save(review);
    }

    @Override
    public Review update(Review review) {
        if (!repository.existsById(review.getId())) {
            throw new IllegalArgumentException("Review with ID " + review.getId() + " does not exist.");
        }
        return repository.save(review);
    }

    @Override
    public Review findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Review with ID " + id + " not found."));
    }

    @Override
    public List<Review> findByBook(String bookId) {
        return repository.findByBookId(bookId);
    }

    @Override
    public List<Review> findByBook(Book book) {
        return repository.findByBook(book);
    }

    @Override
    public List<Review> findByReader(String readerId) {
        return repository.findByReaderId(readerId);
    }

    @Override
    public List<Review> findByReader(Reader reader) {
        return repository.findByReader(reader);
    }

    @Override
    public boolean existsById(String id) {
        return repository.existsById(id);
    }

    @Override
    public boolean existsByBook(String bookId) {
        return repository.existsByBookId(bookId);
    }

    @Override
    public boolean existsByBook(Book book) {
        return repository.existsByBook(book);
    }

    @Override
    public boolean existsByReader(String readerId) {
        return repository.existsByReaderId(readerId);
    }

    @Override
    public boolean existsByReader(Reader reader) {
        return repository.existsByReader(reader);
    }

    @Override
    public boolean deleteById(String id) {
        if (!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }

    @Override
    public boolean deleteByBook(String bookId) {
        List<Review> reviews = repository.findByBookId(bookId);
        if (reviews.isEmpty()) {
            return false;
        }
        repository.deleteAll(reviews);
        return true;
    }

    @Override
    public boolean deleteByBook(Book book) {
        List<Review> reviews = repository.findByBook(book);
        if (reviews.isEmpty()) {
            return false;
        }
        repository.deleteAll(reviews);
        return true;
    }

    @Override
    public boolean deleteByReader(String readerId) {
        List<Review> reviews = repository.findByReaderId(readerId);
        if (reviews.isEmpty()) {
            return false;
        }
        repository.deleteAll(reviews);
        return true;
    }

    @Override
    public boolean deleteByReader(Reader reader) {
        List<Review> reviews = repository.findByReader(reader);
        if (reviews.isEmpty()) {
            return false;
        }
        repository.deleteAll(reviews);
        return true;
    }

    @Override
    public List<Review> listAll() {
        return repository.findAll();
    }

    @Override
    public int count() {
        return (int) repository.count();
    }
}
