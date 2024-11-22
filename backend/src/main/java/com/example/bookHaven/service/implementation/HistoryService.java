package com.example.bookHaven.service.implementation;

import com.example.bookHaven.entity.Book;
import com.example.bookHaven.entity.History;
import com.example.bookHaven.entity.Reader;
import com.example.bookHaven.entity.dto.request.BookDTORequest;
import com.example.bookHaven.entity.dto.request.HistoryDTORequest;
import com.example.bookHaven.entity.dto.request.ReaderDTORequest;
import com.example.bookHaven.entity.dto.response.HistoryDTOResponse;
import com.example.bookHaven.repository.BookRepository;
import com.example.bookHaven.repository.HistoryRepository;
import com.example.bookHaven.repository.ReaderRepository;
import com.example.bookHaven.repository.specification.BookSpecification;
import com.example.bookHaven.service.IHistoryService;
import com.example.bookHaven.service.mappers.HistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class HistoryService implements IHistoryService {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    ReaderRepository readerRepository;
    @Autowired
    private HistoryRepository repository;
    @Autowired
    private HistoryMapper mapper;

    @Override
    public HistoryDTOResponse create(HistoryDTORequest request) {
        History history = mapper.toEntity(request);
        History savedHistory = repository.save(history);
        return mapper.toResponse(savedHistory);
    }

    @Override
    public HistoryDTOResponse update(HistoryDTORequest request) {
        History history = mapper.toEntity(request);
        History updatedHistory = repository.save(history);
        return mapper.toResponse(updatedHistory);
    }

    @Override
    public HistoryDTOResponse findById(String id) {
        History history = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("History with ID " + id + " not found."));
        return mapper.toResponse(history);
    }

    @Override
    public List<HistoryDTOResponse> findByBook(String bookId) {
        return repository.findByBookId(bookId).stream().map(mapper::toResponse).toList();
    }

    @Override
    public List<HistoryDTOResponse> findByBook(BookDTORequest bookDTORequest) {
        List<Book> books = searchBooks(bookDTORequest);
        List<History> histories = books.stream().flatMap(book -> repository.findByBook(book).stream()).toList();
        return histories.stream().map(mapper::toResponse).toList();
    }

    @Override
    public List<HistoryDTOResponse> findByReader(String readerId) {
        return repository.findByReaderId(readerId).stream().map(mapper::toResponse).toList();
    }

    @Override
    public List<HistoryDTOResponse> findByReader(ReaderDTORequest readerRequest) {
        Reader reader = searchReaders(readerRequest);
        if (reader == null) throw new NoSuchElementException("Reader not found.");
        return repository.findByReader(reader).stream().map(mapper::toResponse).toList();
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
    public boolean existsByBook(BookDTORequest bookDTORequest) {
        List<Book> books = searchBooks(bookDTORequest);
        return books.stream().anyMatch(book -> repository.existsByBook(book));
    }

    @Override
    public boolean existsByReader(String readerId) {
        return repository.existsByReaderId(readerId);
    }

    @Override
    public boolean existsByReader(ReaderDTORequest readerRequest) {
        Reader reader = searchReaders(readerRequest);
        if (reader == null) throw new NoSuchElementException("Reader not found.");
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
    public boolean deleteByBook(BookDTORequest bookDTORequest) {
        List<Book> books = searchBooks(bookDTORequest);
        if (books.isEmpty()) {
            return false;
        }
        books.forEach(
                book -> {
                    List<History> histories = repository.findByBook(book);
                    if (!histories.isEmpty()) {
                        repository.deleteAll(histories);
                    }
                }
        );
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
    public boolean deleteByReader(ReaderDTORequest readerRequest) {
        Reader reader = searchReaders(readerRequest);
        if (reader == null) throw new NoSuchElementException("Reader not found.");
        List<History> histories = repository.findByReader(reader);
        if (histories.isEmpty()) {
            return false;
        }
        repository.deleteAll(histories);
        return true;

    }

    @Override
    public List<HistoryDTOResponse> listAll() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    @Override
    public int count() {
        return (int) repository.count();
    }

    private List<Book> searchBooks(BookDTORequest request) {
        Specification<Book> spec = Specification.where(BookSpecification.hasTitle(request.getTitle()))
                .and(BookSpecification.hasGenre(request.getGenre()))
                .and(BookSpecification.hasAuthor(request.getAuthor()));

        return bookRepository.findAll(spec);
    }

    private Reader searchReaders(ReaderDTORequest request) {
        return readerRepository.findByUsername(request.getUsername()).orElse(null);
    }
}
