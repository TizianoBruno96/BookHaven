package com.example.bookHaven.controller;

import com.example.bookHaven.entity.dto.request.BookDTORequest;
import com.example.bookHaven.entity.dto.request.ReaderDTORequest;
import com.example.bookHaven.entity.dto.request.ReviewDTORequest;
import com.example.bookHaven.entity.dto.response.ReviewDTOResponse;
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

    @PostMapping
    public ResponseEntity<ReviewDTOResponse> createReview(@RequestBody ReviewDTORequest request) {
        return ResponseEntity.ok(reviewService.create(request));
    }

    @PutMapping
    public ResponseEntity<ReviewDTOResponse> updateReview(@RequestBody ReviewDTORequest request) {
        return ResponseEntity.ok(reviewService.update(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDTOResponse> getReviewById(@PathVariable String id) {
        return ResponseEntity.ok(reviewService.findById(id));
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<ReviewDTOResponse>> getReviewsByBookId(@PathVariable String bookId) {
        return ResponseEntity.ok(reviewService.findByBook(bookId));
    }

    @PostMapping("/book")
    public ResponseEntity<List<ReviewDTOResponse>> getReviewsByBook(@RequestBody BookDTORequest bookRequest) {
        return ResponseEntity.ok(reviewService.findByBook(bookRequest));
    }

    @GetMapping("/reader/{readerId}")
    public ResponseEntity<List<ReviewDTOResponse>> getReviewsByReaderId(@PathVariable String readerId) {
        return ResponseEntity.ok(reviewService.findByReader(readerId));
    }

    @PostMapping("/reader")
    public ResponseEntity<List<ReviewDTOResponse>> getReviewsByReader(@RequestBody ReaderDTORequest readerRequest) {
        return ResponseEntity.ok(reviewService.findByReader(readerRequest));
    }

    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> checkReviewExistsById(@PathVariable String id) {
        return ResponseEntity.ok(reviewService.existsById(id));
    }

    @GetMapping("/exists/book/{bookId}")
    public ResponseEntity<Boolean> checkReviewExistsByBookId(@PathVariable String bookId) {
        return ResponseEntity.ok(reviewService.existsByBook(bookId));
    }

    @PostMapping("/exists/book")
    public ResponseEntity<Boolean> checkReviewExistsByBook(@RequestBody BookDTORequest bookRequest) {
        return ResponseEntity.ok(reviewService.existsByBook(bookRequest));
    }

    @GetMapping("/exists/reader/{readerId}")
    public ResponseEntity<Boolean> checkReviewExistsByReaderId(@PathVariable String readerId) {
        return ResponseEntity.ok(reviewService.existsByReader(readerId));
    }

    @PostMapping("/exists/reader")
    public ResponseEntity<Boolean> checkReviewExistsByReader(@RequestBody ReaderDTORequest readerRequest) {
        return ResponseEntity.ok(reviewService.existsByReader(readerRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReviewById(@PathVariable String id) {
        reviewService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/book/{bookId}")
    public ResponseEntity<Void> deleteReviewByBookId(@PathVariable String bookId) {
        reviewService.deleteByBook(bookId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/delete/book")
    public ResponseEntity<Void> deleteReviewByBook(@RequestBody BookDTORequest bookRequest) {
        reviewService.deleteByBook(bookRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/reader/{readerId}")
    public ResponseEntity<Void> deleteReviewByReaderId(@PathVariable String readerId) {
        reviewService.deleteByReader(readerId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/delete/reader")
    public ResponseEntity<Void> deleteReviewByReader(@RequestBody ReaderDTORequest readerRequest) {
        reviewService.deleteByReader(readerRequest);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ReviewDTOResponse>> listAllReviews() {
        return ResponseEntity.ok(reviewService.listAll());
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> countReviews() {
        return ResponseEntity.ok(reviewService.count());
    }
}
