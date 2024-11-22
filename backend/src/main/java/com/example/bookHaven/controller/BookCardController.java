package com.example.bookHaven.controller;

import com.example.bookHaven.entity.dto.request.BookCardDTORequest;
import com.example.bookHaven.entity.dto.request.BookDTORequest;
import com.example.bookHaven.entity.dto.response.BookCardDTOResponse;
import com.example.bookHaven.service.IBookCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookCard")
public class BookCardController {

    @Autowired
    private IBookCardService bookCardService;

    @PostMapping("/create")
    public ResponseEntity<BookCardDTOResponse> createBookCard(@RequestBody BookCardDTORequest bookCardRequest) {
        BookCardDTOResponse createdBookCard = bookCardService.create(bookCardRequest);
        return ResponseEntity.ok(createdBookCard);
    }

    @PutMapping("/update")
    public ResponseEntity<BookCardDTOResponse> updateBookCard(@RequestBody BookCardDTORequest bookCardRequest) {
        try {
            BookCardDTOResponse updatedBookCard = bookCardService.update(bookCardRequest);
            return ResponseEntity.ok(updatedBookCard);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<BookCardDTOResponse> findById(@PathVariable String id) {
        try {
            BookCardDTOResponse bookCard = bookCardService.findById(id);
            return ResponseEntity.ok(bookCard);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/findByBook/{bookId}")
    public ResponseEntity<List<BookCardDTOResponse>> findByBook(@PathVariable String bookId) {
        List<BookCardDTOResponse> bookCards = bookCardService.findByBook(bookId);
        return ResponseEntity.ok(bookCards);
    }

    @GetMapping("/findByBookDTO")
    public ResponseEntity<List<BookCardDTOResponse>> findByBook(@RequestBody BookDTORequest bookRequest) {
        List<BookCardDTOResponse> bookCards = bookCardService.findByBook(bookRequest);
        return ResponseEntity.ok(bookCards);
    }

    @GetMapping("/existsById/{id}")
    public ResponseEntity<Boolean> existsById(@PathVariable String id) {
        boolean exists = bookCardService.existsById(id);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/existsByBook/{bookId}")
    public ResponseEntity<Boolean> existsByBook(@PathVariable String bookId) {
        boolean exists = bookCardService.existsByBook(bookId);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/existsByBookDTO")
    public ResponseEntity<Boolean> existsByBook(@RequestBody BookDTORequest bookRequest) {
        boolean exists = bookCardService.existsByBook(bookRequest);
        return ResponseEntity.ok(exists);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        boolean isDeleted = bookCardService.deleteById(id);
        return isDeleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deleteByBook/{bookId}")
    public ResponseEntity<Void> deleteByBook(@PathVariable String bookId) {
        boolean isDeleted = bookCardService.deleteByBook(bookId);
        return isDeleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deleteByBookDTO")
    public ResponseEntity<Void> deleteByBook(@RequestBody BookDTORequest bookRequest) {
        boolean isDeleted = bookCardService.deleteByBook(bookRequest);
        return isDeleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<BookCardDTOResponse>> listAllBookCards() {
        List<BookCardDTOResponse> bookCards = bookCardService.listAll();
        return ResponseEntity.ok(bookCards);
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> countBookCards() {
        int count = bookCardService.count();
        return ResponseEntity.ok(count);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
