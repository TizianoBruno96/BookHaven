package com.example.bookHaven.repository;

import com.example.bookHaven.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, String>, JpaSpecificationExecutor<Book> {
    boolean existsByTitle(String title);

    boolean existsByGenre(String title);

    boolean existsByAuthor(String title);

    void deleteByTitle(String title);

    void deleteByGenre(String genre);

    void deleteByAuthor(String author);
}
