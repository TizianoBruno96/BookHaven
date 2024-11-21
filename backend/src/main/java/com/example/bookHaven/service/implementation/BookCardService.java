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
        return repository.save(bookCard);
    }

    @Override
    public BookCard update(BookCard bookCard) {
        if (!repository.existsById(bookCard.getId())) {
            throw new IllegalArgumentException("BookCard with ID " + bookCard.getId() + " does not exist.");
        }
        return repository.save(bookCard);
    }

    @Override
    public BookCard findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("BookCard with ID " + id + " not found."));
    }

    @Override
    public List<BookCard> findByBook(String bookId) {
        return repository.findByBookId(bookId);
    }

    @Override
    public List<BookCard> findByBook(Book book) {
        return repository.findByBook(book);
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
    public boolean deleteById(String id) {
        if (!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }

    @Override
    public boolean deleteByBook(String bookId) {
        List<BookCard> bookCards = repository.findByBookId(bookId);
        if (bookCards.isEmpty()) {
            return false;
        }
        repository.deleteAll(bookCards);
        return true;
    }

    @Override
    public boolean deleteByBook(Book book) {
        List<BookCard> bookCards = repository.findByBook(book);
        if (bookCards.isEmpty()) {
            return false;
        }
        repository.deleteAll(bookCards);
        return true;
    }

    @Override
    public List<BookCard> listAll() {
        return repository.findAll();
    }

    @Override
    public int count() {
        return (int) repository.count();
    }
}
