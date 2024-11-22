package com.example.bookHaven.controller;

import com.example.bookHaven.controller.utils.ResponseFactory;
import com.example.bookHaven.entity.dto.request.BookCardDTORequest;
import com.example.bookHaven.entity.dto.request.BookDTORequest;
import com.example.bookHaven.entity.dto.response.BookCardDTOResponse;
import com.example.bookHaven.service.IBookCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.bookHaven.controller.utils.ResponseFactory.ResponseType.*;

@RestController
@RequestMapping("/bookCard")
public class BookCardController {

    @Autowired
    private IBookCardService bookCardService;

    @PostMapping("/create")
    public ResponseEntity<?> createBookCard(@RequestBody BookCardDTORequest bookCardRequest) {
        BookCardDTOResponse createdBookCard = bookCardService.create(bookCardRequest);
        return ResponseFactory.getResponse(OK, createdBookCard);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateBookCard(@RequestBody BookCardDTORequest bookCardRequest) {
        try {
            BookCardDTOResponse updatedBookCard = bookCardService.update(bookCardRequest);
            return ResponseFactory.getResponse(OK, updatedBookCard);
        } catch (IllegalArgumentException e) {
            return ResponseFactory.getResponse(BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        try {
            BookCardDTOResponse bookCard = bookCardService.findById(id);
            return ResponseFactory.getResponse(OK, bookCard);
        } catch (IllegalArgumentException e) {
            return ResponseFactory.getResponse(NOT_FOUND, "BookCard not found for ID: " + id);
        }
    }

    @GetMapping("/findByBook/{bookId}")
    public ResponseEntity<?> findByBook(@PathVariable String bookId) {
        List<BookCardDTOResponse> bookCards = bookCardService.findByBook(bookId);
        return ResponseFactory.getResponse(OK, bookCards);
    }

    @GetMapping("/findByBookDTO")
    public ResponseEntity<?> findByBook(@RequestBody BookDTORequest bookRequest) {
        List<BookCardDTOResponse> bookCards = bookCardService.findByBook(bookRequest);
        return ResponseFactory.getResponse(OK, bookCards);
    }

    @GetMapping("/existsById/{id}")
    public ResponseEntity<?> existsById(@PathVariable String id) {
        boolean exists = bookCardService.existsById(id);
        return ResponseFactory.getResponse(OK, exists);
    }

    @GetMapping("/existsByBook/{bookId}")
    public ResponseEntity<?> existsByBook(@PathVariable String bookId) {
        boolean exists = bookCardService.existsByBook(bookId);
        return ResponseFactory.getResponse(OK, exists);
    }

    @GetMapping("/existsByBookDTO")
    public ResponseEntity<?> existsByBook(@RequestBody BookDTORequest bookRequest) {
        boolean exists = bookCardService.existsByBook(bookRequest);
        return ResponseFactory.getResponse(OK, exists);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        boolean isDeleted = bookCardService.deleteById(id);
        if (isDeleted) {
            return ResponseFactory.getResponse(OK, "BookCard deleted successfully.");
        } else {
            return ResponseFactory.getResponse(NOT_FOUND, "BookCard not found for ID: " + id);
        }
    }

    @DeleteMapping("/deleteByBook/{bookId}")
    public ResponseEntity<?> deleteByBook(@PathVariable String bookId) {
        boolean isDeleted = bookCardService.deleteByBook(bookId);
        if (isDeleted) {
            return ResponseFactory.getResponse(OK, "BookCard deleted successfully.");
        } else {
            return ResponseFactory.getResponse(NOT_FOUND, "BookCard not found for Book ID: " + bookId);
        }
    }

    @DeleteMapping("/deleteByBookDTO")
    public ResponseEntity<?> deleteByBook(@RequestBody BookDTORequest bookRequest) {
        boolean isDeleted = bookCardService.deleteByBook(bookRequest);
        if (isDeleted) {
            return ResponseFactory.getResponse(OK, "BookCard deleted successfully.");
        } else {
            return ResponseFactory.getResponse(NOT_FOUND, "BookCard not found for Book: " + bookRequest);
        }
    }

    @GetMapping("/listAll")
    public ResponseEntity<?> listAllBookCards() {
        List<BookCardDTOResponse> bookCards = bookCardService.listAll();
        return ResponseFactory.getResponse(OK, bookCards);
    }

    @GetMapping("/count")
    public ResponseEntity<?> countBookCards() {
        int count = bookCardService.count();
        return ResponseFactory.getResponse(OK, count);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseFactory.getResponse(BAD_REQUEST, ex.getMessage());
    }
}
