package com.example.bookHaven.service;

import com.example.bookHaven.entity.Book;
import com.example.bookHaven.entity.History;
import com.example.bookHaven.entity.Reader;

import java.util.List;

public interface IHistoryService {

    History create(History history);
    History update(History history);
    History findById(String id);
    List<History> findByBook(String bookId);
    List<History> findByBook(Book book);
    List<History> findByReader(String readerId);
    List<History> findByReader(Reader reader);
    boolean existsById(String id);
    boolean existsByBook(String bookId);
    boolean existsByBook(Book book);
    boolean existsByReader(String readerId);
    boolean existsByReader(Reader reader);
    boolean deleteById(String id);
    boolean deleteByBook(String bookId);
    boolean deleteByBook(Book book);
    boolean deleteByReader(String readerId);
    boolean deleteByReader(Reader reader);
    List<History> listAll();
    int count();
}
