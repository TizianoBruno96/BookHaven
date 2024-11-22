package com.example.bookHaven.controller;

import com.example.bookHaven.controller.utils.ResponseFactory;
import com.example.bookHaven.entity.dto.request.BookDTORequest;
import com.example.bookHaven.entity.dto.response.BookDTOResponse;
import com.example.bookHaven.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

import static com.example.bookHaven.controller.utils.ResponseFactory.ResponseType.*;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private IBookService bookService;

    @PostMapping("/create")
    public ResponseEntity<?> createBook(@RequestBody BookDTORequest bookDTORequest) {
        try {
            BookDTOResponse createdBook = bookService.create(bookDTORequest);
            return ResponseFactory.getResponse(OK, createdBook);
        } catch (IllegalArgumentException e) {
            return ResponseFactory.getResponse(BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateBook(@RequestBody BookDTORequest bookDTORequest) {
        try {
            BookDTOResponse updatedBook = bookService.update(bookDTORequest);
            return ResponseFactory.getResponse(OK, updatedBook);
        } catch (NoSuchElementException e) {
            return ResponseFactory.getResponse(NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        BookDTOResponse book = bookService.findById(id);
        if (book != null) {
            return ResponseFactory.getResponse(OK, book);
        } else {
            return ResponseFactory.getResponse(NOT_FOUND, "Book not found for ID: " + id);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchBooks(@RequestParam String title, @RequestParam String genre, @RequestParam String author) {
        List<BookDTOResponse> books = bookService.searchBooks(title, genre, author);
        return ResponseFactory.getResponse(OK, books);
    }

    @GetMapping("/existsById/{id}")
    public ResponseEntity<?> existsById(@PathVariable String id) {
        boolean exists = bookService.existsById(id);
        return ResponseFactory.getResponse(OK, exists);
    }

    @GetMapping("/existsByTitle/{title}")
    public ResponseEntity<?> existsByTitle(@PathVariable String title) {
        boolean exists = bookService.existsByTitle(title);
        return ResponseFactory.getResponse(OK, exists);
    }

    @GetMapping("/existsByAuthor/{author}")
    public ResponseEntity<?> existsByAuthor(@PathVariable String author) {
        boolean exists = bookService.existsByAuthor(author);
        return ResponseFactory.getResponse(OK, exists);
    }

    @GetMapping("/existsByGenre/{genre}")
    public ResponseEntity<?> existsByGenre(@PathVariable String genre) {
        boolean exists = bookService.existsByGenre(genre);
        return ResponseFactory.getResponse(OK, exists);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        boolean isDeleted = bookService.deleteById(id);
        if (isDeleted) {
            return ResponseFactory.getResponse(OK, "Book deleted successfully.");
        } else {
            return ResponseFactory.getResponse(NOT_FOUND, "Book not found for ID: " + id);
        }
    }

    @DeleteMapping("/deleteByTitle/{title}")
    public ResponseEntity<?> deleteByTitle(@PathVariable String title) {
        boolean isDeleted = bookService.deleteByTitle(title);
        if (isDeleted) {
            return ResponseFactory.getResponse(OK, "Book deleted successfully.");
        } else {
            return ResponseFactory.getResponse(NOT_FOUND, "Book not found for Title: " + title);
        }
    }

    @DeleteMapping("/deleteByGenre/{genre}")
    public ResponseEntity<?> deleteByGenre(@PathVariable String genre) {
        boolean isDeleted = bookService.deleteByGenre(genre);
        if (isDeleted) {
            return ResponseFactory.getResponse(OK, "Books deleted successfully.");
        } else {
            return ResponseFactory.getResponse(NOT_FOUND, "No books found for Genre: " + genre);
        }
    }

    @DeleteMapping("/deleteByAuthor/{author}")
    public ResponseEntity<?> deleteByAuthor(@PathVariable String author) {
        boolean isDeleted = bookService.deleteByAuthor(author);
        if (isDeleted) {
            return ResponseFactory.getResponse(OK, "Books deleted successfully.");
        } else {
            return ResponseFactory.getResponse(NOT_FOUND, "No books found for Author: " + author);
        }
    }

    @GetMapping("/count")
    public ResponseEntity<?> countBooks() {
        long count = bookService.count();
        return ResponseFactory.getResponse(OK, count);
    }

    @GetMapping("/listAll")
    public ResponseEntity<?> listAllBooks() {
        List<BookDTOResponse> books = bookService.listAll();
        return ResponseFactory.getResponse(OK, books);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseFactory.getResponse(BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> handleNoSuchElementException(NoSuchElementException ex) {
        return ResponseFactory.getResponse(NOT_FOUND, ex.getMessage());
    }
}
