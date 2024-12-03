package com.example.bookHaven.service.implementation;

import com.example.bookHaven.entity.Book;
import com.example.bookHaven.entity.BookCard;
import com.example.bookHaven.entity.dto.request.BookCardDTORequest;
import com.example.bookHaven.entity.dto.request.BookDTORequest;
import com.example.bookHaven.entity.dto.response.BookCardDTOResponse;
import com.example.bookHaven.repository.BookCardRepository;
import com.example.bookHaven.repository.BookRepository;
import com.example.bookHaven.repository.specification.BookSpecification;
import com.example.bookHaven.service.IBookCardService;
import com.example.bookHaven.service.mappers.BookCardMapper;
import com.example.bookHaven.service.mappers.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookCardService implements IBookCardService {

    @Autowired
    private BookCardRepository repository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookCardMapper mapper;
    @Autowired
    private BookMapper bookMapper;

    @Override
    public BookCardDTOResponse create(BookCardDTORequest request) {
        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(NoSuchElementException::new);

        BookCard bookCard = mapper.toEntity(request);

        bookCard.setBook(book);
        BookCard savedBookCard = repository.save(bookCard);
        return mapper.toResponse(savedBookCard);
    }

    @Override
    public BookCardDTOResponse update(BookCardDTORequest request) {
        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(NoSuchElementException::new);

        BookCard bookCard = mapper.toEntity(request);

        bookCard.setBook(book);
        BookCard updatedBookCard = repository.save(bookCard);
        return mapper.toResponse(updatedBookCard);
    }

    @Override
    public BookCardDTOResponse findById(String id) {
        BookCard bookCard = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("BookCard with ID " + id + " not found."));
        return mapper.toResponse(bookCard);
    }

    @Override
    public List<BookCardDTOResponse> findByBook(String bookId) {
        List<BookCard> bookCards = repository.findByBookId(bookId);
        return bookCards.stream().map(mapper::toResponse).toList();
    }

    @Override
    public List<BookCardDTOResponse> findByBook(BookDTORequest bookRequest) {
        List<Book> books = searchBooks(bookRequest);
        List<BookCard> bookCards = books.stream().flatMap(
                book -> repository.findByBook(book)
                        .stream()).toList();
        return bookCards.stream().map(mapper::toResponse).toList();
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
    public boolean existsByBook(BookDTORequest bookRequest) {
        List<Book> books = searchBooks(bookRequest);
        return books.stream().anyMatch(book -> repository.existsByBook(book));
    }

    @Override
    public boolean deleteById(String id) {
        repository.deleteById(id);
        return !repository.existsById(id);
    }

    @Override
    public boolean deleteByBook(String bookId) {
        List<BookCard> bookCards = repository.findByBookId(bookId);
        if (bookCards.isEmpty()) {
            return true;
        }
        repository.deleteAll(bookCards);
        return repository.existsByBookId(bookId);
    }

    @Override
    public boolean deleteByBook(BookDTORequest bookRequest) {
        List<Book> books = searchBooks(bookRequest);
        if (books.isEmpty()) {
            throw new NoSuchElementException("History with book: " + bookRequest.getTitle() + ", " + bookRequest.getAuthor() + " not found.");
        }
        books.forEach(book -> {
            List<BookCard> bookCards = repository.findByBookId(book.getId());
            if (!bookCards.isEmpty()) {
                repository.deleteAll(bookCards);
            }
        });
        return books.stream().noneMatch(book -> repository.existsByBook(book));
    }

    @Override
    public List<BookCardDTOResponse> listAll() {
        List<BookCard> bookCards = repository.findAll();
        return bookCards.stream().map(mapper::toResponse).toList();
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
}
