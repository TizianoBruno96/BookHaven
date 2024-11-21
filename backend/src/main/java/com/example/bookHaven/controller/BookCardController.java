package com.example.bookHaven.controller;

import com.example.bookHaven.entity.BookCard;
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
    public ResponseEntity<BookCard> createBookCard(@RequestBody BookCard bookCard) {
        BookCard createdBookCard = bookCardService.create(bookCard);
        return ResponseEntity.ok(createdBookCard);
    }

    @PutMapping("/update")
    public ResponseEntity<BookCard> updateBookCard(@RequestBody BookCard bookCard) {
        try {
            BookCard updatedBookCard = bookCardService.update(bookCard);
            return ResponseEntity.ok(updatedBookCard);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<BookCard> findById(@PathVariable String id) {
        try {
            BookCard bookCard = bookCardService.findById(id);
            return ResponseEntity.ok(bookCard);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/findByBook/{bookId}")
    public ResponseEntity<List<BookCard>> findByBook(@PathVariable String bookId) {
        List<BookCard> bookCards = bookCardService.findByBook(bookId);
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

    @GetMapping("/count")
    public ResponseEntity<Integer> countBookCards() {
        int count = bookCardService.count();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<BookCard>> listAllBookCards() {
        List<BookCard> bookCards = bookCardService.listAll();
        return ResponseEntity.ok(bookCards);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
