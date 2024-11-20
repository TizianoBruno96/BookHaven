package com.example.bookHaven.repository;

import com.example.bookHaven.entity.BookCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookCardRepository extends JpaRepository<BookCard, String> { }
