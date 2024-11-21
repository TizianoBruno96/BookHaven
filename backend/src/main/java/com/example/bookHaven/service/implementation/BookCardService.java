package com.example.bookHaven.service.implementation;

import com.example.bookHaven.entity.Book;
import com.example.bookHaven.entity.BookCard;
import com.example.bookHaven.repository.BookCardRepository;
import com.example.bookHaven.service.IBookCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookCardService implements IBookCardService {

    @Autowired
    BookCardRepository repository;

    @Override
    public BookCard create(BookCard bookCard) {
        return new BookCard();
    }

    @Override
    public BookCard update(BookCard bookCard) {
        return new BookCard();
    }

    @Override
    public BookCard findById(String id) {
        return null;
    }

    @Override
    public List<BookCard> findByBook(String bookId) {
        return List.of();
    }

    @Override
    public List<BookCard> findByBook(Book book) {
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
    public List<BookCard> listAll() {
        return List.of();
    }

    @Override
    public int count() {
        return 0;
    }
}
