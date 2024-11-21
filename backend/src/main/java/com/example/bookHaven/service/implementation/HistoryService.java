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
    private HistoryRepository repository;

    @Override
    public History create(History history) {
        return repository.save(history);
    }

    @Override
    public History update(History history) {
        if (!repository.existsById(history.getId())) {
            throw new IllegalArgumentException("History with ID " + history.getId() + " does not exist.");
        }
        return repository.save(history);
    }

    @Override
    public History findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("History with ID " + id + " not found."));
    }

    @Override
    public List<History> findByBook(String bookId) {
        return repository.findByBookId(bookId);
    }

    @Override
    public List<History> findByBook(Book book) {
        return repository.findByBook(book);
    }

    @Override
    public List<History> findByReader(String readerId) {
        return repository.findByReaderId(readerId);
    }

    @Override
    public List<History> findByReader(Reader reader) {
        return repository.findByReader(reader);
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
    public boolean existsByReader(String readerId) {
        return repository.existsByReaderId(readerId);
    }

    @Override
    public boolean existsByReader(Reader reader) {
        return repository.existsByReader(reader);
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
        List<History> histories = repository.findByBookId(bookId);
        if (histories.isEmpty()) {
            return false;
        }
        repository.deleteAll(histories);
        return true;
    }

    @Override
    public boolean deleteByBook(Book book) {
        List<History> histories = repository.findByBook(book);
        if (histories.isEmpty()) {
            return false;
        }
        repository.deleteAll(histories);
        return true;
    }

    @Override
    public boolean deleteByReader(String readerId) {
        List<History> histories = repository.findByReaderId(readerId);
        if (histories.isEmpty()) {
            return false;
        }
        repository.deleteAll(histories);
        return true;
    }

    @Override
    public boolean deleteByReader(Reader reader) {
        List<History> histories = repository.findByReader(reader);
        if (histories.isEmpty()) {
            return false;
        }
        repository.deleteAll(histories);
        return true;
    }

    @Override
    public List<History> listAll() {
        return repository.findAll();
    }

    @Override
    public int count() {
        return (int) repository.count();
    }
}
