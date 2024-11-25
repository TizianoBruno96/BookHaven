package com.example.bookHaven.controller;

import com.example.bookHaven.controller.utils.ResponseFactory;
import com.example.bookHaven.entity.dto.request.BookDTORequest;
import com.example.bookHaven.entity.dto.request.ReaderDTORequest;
import com.example.bookHaven.entity.dto.request.ReviewDTORequest;
import com.example.bookHaven.entity.dto.response.ReviewDTOResponse;
import com.example.bookHaven.service.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

import static com.example.bookHaven.controller.utils.ResponseFactory.ResponseType.*;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private IReviewService reviewService;

    @PostMapping
    public ResponseEntity<?> createReview(@RequestBody ReviewDTORequest request) {
        ReviewDTOResponse createdReview = reviewService.create(request);
        return ResponseFactory.getResponse(OK, createdReview);
    }

    @PutMapping
    public ResponseEntity<?> updateReview(@RequestBody ReviewDTORequest request) {
        ReviewDTOResponse updatedReview = reviewService.update(request);
        return ResponseFactory.getResponse(OK, updatedReview);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getReviewById(@PathVariable String id) {
        ReviewDTOResponse review = reviewService.findById(id);
        return review != null ? ResponseFactory.getResponse(OK, review) :
                ResponseFactory.getResponse(NOT_FOUND);
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<?> getReviewsByBookId(@PathVariable String bookId) {
        List<ReviewDTOResponse> reviews = reviewService.findByBook(bookId);
        return ResponseFactory.getResponse(OK, reviews);
    }

    @PostMapping("/book")
    public ResponseEntity<?> getReviewsByBook(@RequestBody BookDTORequest bookRequest) {
        List<ReviewDTOResponse> reviews = reviewService.findByBook(bookRequest);
        return ResponseFactory.getResponse(OK, reviews);
    }

    @GetMapping("/reader/{readerId}")
    public ResponseEntity<?> getReviewsByReaderId(@PathVariable String readerId) {
        List<ReviewDTOResponse> reviews = reviewService.findByReader(readerId);
        return ResponseFactory.getResponse(OK, reviews);
    }

    @PostMapping("/reader")
    public ResponseEntity<?> getReviewsByReader(@RequestBody ReaderDTORequest readerRequest) {
        List<ReviewDTOResponse> reviews = reviewService.findByReader(readerRequest);
        return ResponseFactory.getResponse(OK, reviews);
    }

    @GetMapping("/exists/{id}")
    public ResponseEntity<?> checkReviewExistsById(@PathVariable String id) {
        boolean exists = reviewService.existsById(id);
        return ResponseFactory.getResponse(OK, exists);
    }

    @GetMapping("/exists/book/{bookId}")
    public ResponseEntity<?> checkReviewExistsByBookId(@PathVariable String bookId) {
        boolean exists = reviewService.existsByBook(bookId);
        return ResponseFactory.getResponse(OK, exists);
    }

    @PostMapping("/exists/book")
    public ResponseEntity<?> checkReviewExistsByBook(@RequestBody BookDTORequest bookRequest) {
        boolean exists = reviewService.existsByBook(bookRequest);
        return ResponseFactory.getResponse(OK, exists);
    }

    @GetMapping("/exists/reader/{readerId}")
    public ResponseEntity<?> checkReviewExistsByReaderId(@PathVariable String readerId) {
        boolean exists = reviewService.existsByReader(readerId);
        return ResponseFactory.getResponse(OK, exists);
    }

    @PostMapping("/exists/reader")
    public ResponseEntity<?> checkReviewExistsByReader(@RequestBody ReaderDTORequest readerRequest) {
        boolean exists = reviewService.existsByReader(readerRequest);
        return ResponseFactory.getResponse(OK, exists);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReviewById(@PathVariable String id) {
        return ResponseFactory.getResponse(OK, reviewService.deleteById(id));
    }

    @DeleteMapping("/book/{bookId}")
    public ResponseEntity<?> deleteReviewByBookId(@PathVariable String bookId) {
        return ResponseFactory.getResponse(OK, reviewService.deleteByBook(bookId));
    }

    @PostMapping("/delete/book")
    public ResponseEntity<?> deleteReviewByBook(@RequestBody BookDTORequest bookRequest) {
        return ResponseFactory.getResponse(OK, reviewService.deleteByBook(bookRequest));
    }

    @DeleteMapping("/reader/{readerId}")
    public ResponseEntity<?> deleteReviewByReaderId(@PathVariable String readerId) {
        return ResponseFactory.getResponse(OK, reviewService.deleteByReader(readerId));
    }

    @PostMapping("/delete/reader")
    public ResponseEntity<?> deleteReviewByReader(@RequestBody ReaderDTORequest readerRequest) {
        return ResponseFactory.getResponse(OK, reviewService.deleteByReader(readerRequest));
    }

    @GetMapping
    public ResponseEntity<?> listAllReviews() {
        List<ReviewDTOResponse> reviews = reviewService.listAll();
        return ResponseFactory.getResponse(OK, reviews);
    }

    @GetMapping("/count")
    public ResponseEntity<?> countReviews() {
        int count = reviewService.count();
        return ResponseFactory.getResponse(OK, count);
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
