package com.example.bookHaven.service.implementation;

import com.example.bookHaven.entity.Book;
import com.example.bookHaven.entity.Reader;
import com.example.bookHaven.entity.Review;
import com.example.bookHaven.entity.dto.request.BookDTORequest;
import com.example.bookHaven.entity.dto.request.ReaderDTORequest;
import com.example.bookHaven.entity.dto.request.ReviewDTORequest;
import com.example.bookHaven.entity.dto.response.ReviewDTOResponse;
import com.example.bookHaven.repository.BookRepository;
import com.example.bookHaven.repository.ReaderRepository;
import com.example.bookHaven.repository.ReviewRepository;
import com.example.bookHaven.repository.specification.BookSpecification;
import com.example.bookHaven.service.IReviewService;
import com.example.bookHaven.service.mappers.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ReviewService implements IReviewService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ReaderRepository readerRepository;
    @Autowired
    private ReviewRepository repository;
    @Autowired
    private ReviewMapper mapper;

    @Override
    public ReviewDTOResponse create(ReviewDTORequest request) {
        Review review = mapper.toEntity(request);
        Review savedReview = repository.save(review);
        return mapper.toResponse(savedReview);
    }

    @Override
    public ReviewDTOResponse update(ReviewDTORequest request) {
        Review review = mapper.toEntity(request);
        Review updatedReview = repository.save(review);
        return mapper.toResponse(updatedReview);
    }

    @Override
    public ReviewDTOResponse findById(String id) {
        Review review = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Review with ID " + id + " not found."));
        return mapper.toResponse(review);
    }

    @Override
    public List<ReviewDTOResponse> findByBook(String bookId) {
        return repository.findByBookId(bookId).stream().map(mapper::toResponse).toList();
    }

    @Override
    public List<ReviewDTOResponse> findByBook(BookDTORequest bookRequest) {
        List<Book> books = searchBooks(bookRequest);
        if (books.isEmpty()) {
            throw new NoSuchElementException("Book " + bookRequest.getTitle() + ", " + bookRequest.getAuthor() + " not found.");
        }
        List<Review> reviews = books.stream().flatMap(book -> repository.findByBook(book).stream()).toList();
        return reviews.stream().map(mapper::toResponse).toList();
    }

    @Override
    public List<ReviewDTOResponse> findByReader(String readerId) {
        return repository.findByReaderId(readerId).stream().map(mapper::toResponse).toList();
    }

    @Override
    public List<ReviewDTOResponse> findByReader(ReaderDTORequest readerRequest) {
        Reader reader = searchReaders(readerRequest);
        if (reader == null) throw new NoSuchElementException("Reader " + readerRequest.getUsername() + " not found.");
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
    public boolean existsByBook(BookDTORequest bookRequest) {
        List<Book> books = searchBooks(bookRequest);
        if (books.isEmpty()) {
            throw new NoSuchElementException("Book " + bookRequest.getTitle() + ", " + bookRequest.getAuthor() + " not found.");
        }
        return books.stream().anyMatch(book -> repository.existsByBook(book));
    }

    @Override
    public boolean existsByReader(String readerId) {
        return repository.existsByReaderId(readerId);
    }

    @Override
    public boolean existsByReader(ReaderDTORequest readerRequest) {
        Reader reader = searchReaders(readerRequest);
        if (reader == null) throw new NoSuchElementException("Reader " + readerRequest.getUsername() + " not found.");
        return repository.existsByReader(reader);
    }

    @Override
    public boolean deleteById(String id) {
        repository.deleteById(id);
        return !repository.existsById(id);
    }

    @Override
    public boolean deleteByBook(String bookId) {
        List<Review> reviews = repository.findByBookId(bookId);
        if (reviews.isEmpty()) {
            return true;
        }
        repository.deleteAll(reviews);
        return repository.existsByBookId(bookId);
    }

    @Override
    public boolean deleteByBook(BookDTORequest bookRequest) {
        List<Book> books = searchBooks(bookRequest);
        if (books.isEmpty()) {
            throw new NoSuchElementException("Book " + bookRequest.getTitle() + ", " + bookRequest.getAuthor() + " not found.");
        }
        books.forEach(
                book -> {
                    List<Review> reviews = repository.findByBook(book);
                    if (!reviews.isEmpty()) {
                        repository.deleteAll(reviews);
                    }
                }
        );
        return books.stream().noneMatch(book -> repository.existsByBook(book));
    }

    @Override
    public boolean deleteByReader(String readerId) {
        List<Review> reviews = repository.findByReaderId(readerId);
        if (reviews.isEmpty()) {
            return false;
        }
        repository.deleteAll(reviews);
        return !repository.existsByReaderId(readerId);
    }

    @Override
    public boolean deleteByReader(ReaderDTORequest readerRequest) {
        Reader reader = searchReaders(readerRequest);
        if (reader == null) throw new NoSuchElementException("Reader " + readerRequest.getUsername() + " not found.");
        List<Review> reviews = repository.findByReader(reader);
        if (reviews.isEmpty()) {
            return true;
        }
        repository.deleteAll(reviews);
        return repository.findByReader(reader).isEmpty();
    }

    @Override
    public List<ReviewDTOResponse> listAll() {
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
