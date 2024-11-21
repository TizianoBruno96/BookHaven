package com.example.bookHaven.service.implementation;

import com.example.bookHaven.entity.Book;
import com.example.bookHaven.entity.History;
import com.example.bookHaven.entity.Reader;
import com.example.bookHaven.repository.HistoryRepository;
import com.example.bookHaven.service.IHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryService implements IHistoryService {

    @Autowired
    HistoryRepository repository;

    @Override
    public History create(History history) {
        return new History();
    }

    @Override
    public History update(History history) {
        return new History();
    }

    @Override
    public History findById(String id) {
        return null;
    }

    @Override
    public List<History> findByBook(String bookId) {
        return List.of();
    }

    @Override
    public List<History> findByBook(Book book) {
        return List.of();
    }

    @Override
    public List<History> findByReader(String readerId) {
        return List.of();
    }

    @Override
    public List<History> findByReader(Reader reader) {
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
    public boolean existsByReader(String readerId) {
        return false;
    }

    @Override
    public boolean existsByReader(Reader reader) {
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
    public boolean deleteByReader(String readerId) {
        return false;
    }

    @Override
    public boolean deleteByReader(Reader reader) {
        return false;
    }

    @Override
    public List<History> listAll() {
        return List.of();
    }

    @Override
    public int could() {
        return 0;
    }
}
