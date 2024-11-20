package com.example.bookHaven.service.implementation;

import com.example.bookHaven.entity.Book;
import com.example.bookHaven.repository.BookRepository;
import com.example.bookHaven.repository.specification.BookSpecification;
import com.example.bookHaven.service.IBookService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService {

    @Autowired
    private BookRepository repository;

    @Override
    @Transactional
    public boolean create(Book book) {
        if (existsByTitle(book.getTitle()) && existsByAuthor(book.getAuthor()) && existsByGenre(book.getGenre())) {
            return false;
        }

        repository.save(book);
        return existsByTitle(book.getTitle()) && existsByAuthor(book.getAuthor()) && existsByGenre(book.getGenre());
    }

    @Override
    @Transactional
    public boolean update(Book book) {
        if (!(existsByTitle(book.getTitle()) && existsByAuthor(book.getAuthor()) && existsByGenre(book.getGenre()))) {
            return false;
        }

        repository.save(book);
        return existsByTitle(book.getTitle()) && existsByAuthor(book.getAuthor()) && existsByGenre(book.getGenre());
    }

    @Override
    public Book findById(String id) {
        Optional<Book> book = repository.findById(id);
        return book.orElse(null);
    }

    @Override
    public List<Book> searchBooks(String title, String genre, String author) {
        Specification<Book> spec = Specification.where(BookSpecification.hasTitle(title))
                .and(BookSpecification.hasGenre(genre))
                .and(BookSpecification.hasAuthor(author));
        return repository.findAll(spec);
    }

    @Override
    public boolean existsById(String id) {
        return repository.existsById(id);
    }

    @Override
    public boolean existsByTitle(String title) {
        return repository.existsByTitle(title);
    }

    @Override
    public boolean existsByGenre(String genre) {
        return repository.existsByGenre(genre);
    }

    @Override
    public boolean existsByAuthor(String author) {
        return repository.existsByAuthor(author);
    }

    @Override
    @Transactional
    public boolean deleteById(String id) {
        repository.deleteById(id);
        return existsById(id);
    }

    @Override
    @Transactional
    public boolean deleteByTitle(String title) {
        repository.deleteByTitle(title);
        return existsByTitle(title);
    }

    @Override
    @Transactional
    public boolean deleteByGenre(String genre) {
        repository.deleteByGenre(genre);
        return existsByGenre(genre);
    }

    @Override
    @Transactional
    public boolean deleteByAuthor(String author) {
        repository.deleteByAuthor(author);
        return existsByAuthor(author);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public List<Book> listAll() {
        return repository.findAll();
    }
}
