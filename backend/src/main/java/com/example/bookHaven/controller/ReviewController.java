package com.example.bookHaven.controller;

import com.example.bookHaven.entity.Review;
import com.example.bookHaven.service.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private IReviewService reviewService;

    @PostMapping("/create")
    public ResponseEntity<Review> createReview(@RequestBody Review review) {
        Review createdReview = reviewService.create(review);
        return ResponseEntity.ok(createdReview);
    }

    @PutMapping("/update")
    public ResponseEntity<Review> updateReview(@RequestBody Review review) {
        try {
            Review updatedReview = reviewService.update(review);
            return ResponseEntity.ok(updatedReview);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Review> findById(@PathVariable String id) {
        try {
            Review review = reviewService.findById(id);
            return ResponseEntity.ok(review);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/findByBook/{bookId}")
    public ResponseEntity<List<Review>> findByBook(@PathVariable String bookId) {
        List<Review> reviews = reviewService.findByBook(bookId);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/findByReader/{readerId}")
    public ResponseEntity<List<Review>> findByReader(@PathVariable String readerId) {
        List<Review> reviews = reviewService.findByReader(readerId);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/existsById/{id}")
    public ResponseEntity<Boolean> existsById(@PathVariable String id) {
        boolean exists = reviewService.existsById(id);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/existsByBook/{bookId}")
    public ResponseEntity<Boolean> existsByBook(@PathVariable String bookId) {
        boolean exists = reviewService.existsByBook(bookId);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/existsByReader/{readerId}")
    public ResponseEntity<Boolean> existsByReader(@PathVariable String readerId) {
        boolean exists = reviewService.existsByReader(readerId);
        return ResponseEntity.ok(exists);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        boolean isDeleted = reviewService.deleteById(id);
        return isDeleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deleteByBook/{bookId}")
    public ResponseEntity<Void> deleteByBook(@PathVariable String bookId) {
        boolean isDeleted = reviewService.deleteByBook(bookId);
        return isDeleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deleteByReader/{readerId}")
    public ResponseEntity<Void> deleteByReader(@PathVariable String readerId) {
        boolean isDeleted = reviewService.deleteByReader(readerId);
        return isDeleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> countReviews() {
        int count = reviewService.count();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<Review>> listAllReviews() {
        List<Review> reviews = reviewService.listAll();
        return ResponseEntity.ok(reviews);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
