package com.example.bookHaven.repository;

import com.example.bookHaven.entity.Book;
import com.example.bookHaven.entity.Reader;
import com.example.bookHaven.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {
    List<Review> findByBookId(String bookId);
    List<Review> findByBook(Book book);
    List<Review> findByReaderId(String readerId);
    List<Review> findByReader(Reader reader);
    boolean existsByBookId(String bookId);
    boolean existsByBook(Book book);
    boolean existsByReaderId(String readerId);
    boolean existsByReader(Reader reader);
}
