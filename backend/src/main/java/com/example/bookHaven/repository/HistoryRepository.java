package com.example.bookHaven.repository;

import com.example.bookHaven.entity.Book;
import com.example.bookHaven.entity.History;
import com.example.bookHaven.entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, String> {
    List<History> findByBookId(String bookId);

    List<History> findByBook(Book book);

    List<History> findByReaderId(String readerId);

    List<History> findByReader(Reader reader);

    boolean existsByBookId(String bookId);

    boolean existsByBook(Book book);

    boolean existsByReaderId(String readerId);

    boolean existsByReader(Reader reader);
}
