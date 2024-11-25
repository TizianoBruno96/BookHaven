package com.example.bookHaven.service.implementation;

import com.example.bookHaven.entity.Book;
import com.example.bookHaven.entity.dto.request.BookDTORequest;
import com.example.bookHaven.entity.dto.response.BookDTOResponse;
import com.example.bookHaven.repository.BookRepository;
import com.example.bookHaven.repository.specification.BookSpecification;
import com.example.bookHaven.service.IBookService;
import com.example.bookHaven.service.mappers.BookMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BookService implements IBookService {

    @Autowired
    private BookRepository repository;
    @Autowired
    private BookMapper mapper;

    @Override
    @Transactional
    public BookDTOResponse create(BookDTORequest request) {
        if (checkBook(request.getTitle(), request.getAuthor(), request.getGenre())) {
            throw new IllegalArgumentException("Book already exists. Please use update instead.");
        }

        Book book = mapper.toEntity(request);
        return mapper.toResponse(repository.save(book));
    }

    @Override
    @Transactional
    public BookDTOResponse update(BookDTORequest request) {
        if (!checkBook(request.getTitle(), request.getAuthor(), request.getGenre())) {
            throw new NoSuchElementException("Book not found. Please use save instead.");
        }

        Book book = mapper.toEntity(request);
        Book updatedBook = repository.save(book);
        return mapper.toResponse(updatedBook);
    }

    @Override
    public BookDTOResponse findById(String id) {
        Optional<Book> book = repository.findById(id);
        return book.map(mapper::toResponse).orElse(null);
    }

    @Override
    public List<BookDTOResponse> searchBooks(String title, String genre, String author) {
        Specification<Book> spec = Specification.where(BookSpecification.hasTitle(title))
                .and(BookSpecification.hasGenre(genre))
                .and(BookSpecification.hasAuthor(author));

        List<Book> books = repository.findAll(spec);
        return books.stream().map(mapper::toResponse).toList();
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
        return !repository.existsById(id);
    }

    @Override
    @Transactional
    public boolean deleteByTitle(String title) {
        repository.deleteByTitle(title);
        return !repository.existsByTitle(title);
    }

    @Override
    @Transactional
    public boolean deleteByGenre(String genre) {
        repository.deleteByGenre(genre);
        return !repository.existsByGenre(genre);
    }

    @Override
    @Transactional
    public boolean deleteByAuthor(String author) {
        repository.deleteByAuthor(author);
        return !repository.existsByAuthor(author);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public List<BookDTOResponse> listAll() {
        List<Book> books = repository.findAll();
        return books.stream().map(mapper::toResponse).toList();
    }

    private boolean checkBook(String title, String author, String genre) {
        return repository.existsByTitle(title) && repository.existsByAuthor(author) && repository.existsByGenre(genre);
    }
}
