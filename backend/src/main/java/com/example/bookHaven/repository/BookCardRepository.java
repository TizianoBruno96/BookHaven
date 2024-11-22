package com.example.bookHaven.repository;

import com.example.bookHaven.entity.Book;
import com.example.bookHaven.entity.BookCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookCardRepository extends JpaRepository<BookCard, String> {
    List<BookCard> findByBookId(String bookId);

    List<BookCard> findByBook(Book book);

    boolean existsByBookId(String bookId);

    boolean existsByBook(Book book);
}
