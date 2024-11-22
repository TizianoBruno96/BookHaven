package com.example.bookHaven.service;

import com.example.bookHaven.entity.Book;

import java.util.List;

public interface IBookService {
    Book create(Book book);

    Book update(Book book);

    Book findById(String id);

    List<Book> searchBooks(String title, String genre, String author);

    boolean existsById(String id);

    boolean existsByTitle(String title);

    boolean existsByGenre(String genre);

    boolean existsByAuthor(String author);

    boolean deleteById(String id);

    boolean deleteByTitle(String title);

    boolean deleteByGenre(String genre);

    boolean deleteByAuthor(String author);

    long count();

    List<Book> listAll();
}
