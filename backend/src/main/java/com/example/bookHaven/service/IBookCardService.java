package com.example.bookHaven.service;

import com.example.bookHaven.entity.Book;
import com.example.bookHaven.entity.BookCard;

import java.util.List;

public interface IBookCardService {

    boolean create(BookCard bookCard);
    boolean update(BookCard bookCard);
    BookCard findById(String id);
    List<BookCard> findByBook(String bookId);
    List<BookCard> findByBook(Book book);
    boolean existsById(String id);
    boolean existsByBook(String bookId);
    boolean existsByBook(Book book);
    boolean deleteById(String id);
    boolean deleteByBook(String bookId);
    boolean deleteByBook(Book book);
    List<BookCard> listAll();
    int count();
}
