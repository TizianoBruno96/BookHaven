package com.example.bookHaven.controller;

import com.example.bookHaven.entity.dto.request.ReviewDTORequest;
import com.example.bookHaven.entity.dto.response.ReviewDTOResponse;
import com.example.bookHaven.service.IReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ReviewControllerTest {

    @Mock
    private IReviewService reviewService;

    @InjectMocks
    private ReviewController reviewController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateReview() {
        ReviewDTORequest request = new ReviewDTORequest();
        ReviewDTOResponse response = new ReviewDTOResponse();

        when(reviewService.create(request)).thenReturn(response);

        ResponseEntity<?> result = reviewController.createReview(request);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
        verify(reviewService, times(1)).create(request);
    }

    @Test
    void testUpdateReview() {
        ReviewDTORequest request = new ReviewDTORequest();
        ReviewDTOResponse response = new ReviewDTOResponse();

        when(reviewService.update(request)).thenReturn(response);

        ResponseEntity<?> result = reviewController.updateReview(request);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
        verify(reviewService, times(1)).update(request);
    }

    @Test
    void testGetReviewById() {
        String id = "test-id";
        ReviewDTOResponse response = new ReviewDTOResponse();

        when(reviewService.findById(id)).thenReturn(response);

        ResponseEntity<?> result = reviewController.getReviewById(id);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
        verify(reviewService, times(1)).findById(id);
    }

    @Test
    void testGetReviewsByBookId() {
        String bookId = "book-id";
        List<ReviewDTOResponse> response = Collections.singletonList(new ReviewDTOResponse());

        when(reviewService.findByBook(bookId)).thenReturn(response);

        ResponseEntity<?> result = reviewController.getReviewsByBookId(bookId);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
        verify(reviewService, times(1)).findByBook(bookId);
    }

    @Test
    void testGetReviewsByReaderId() {
        String readerId = "reader-id";
        List<ReviewDTOResponse> response = Collections.singletonList(new ReviewDTOResponse());

        when(reviewService.findByReader(readerId)).thenReturn(response);

        ResponseEntity<?> result = reviewController.getReviewsByReaderId(readerId);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
        verify(reviewService, times(1)).findByReader(readerId);
    }

    @Test
    void testCheckReviewExistsById() {
        String id = "review-id";

        when(reviewService.existsById(id)).thenReturn(true);

        ResponseEntity<?> result = reviewController.checkReviewExistsById(id);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(true, result.getBody());
        verify(reviewService, times(1)).existsById(id);
    }

    @Test
    void testDeleteReviewById() {
        String id = "review-id";

        when(reviewService.deleteById(id)).thenReturn(true);

        ResponseEntity<?> result = reviewController.deleteReviewById(id);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(true, result.getBody());
        verify(reviewService, times(1)).deleteById(id);
    }

    @Test
    void testListAllReviews() {
        List<ReviewDTOResponse> response = Collections.singletonList(new ReviewDTOResponse());

        when(reviewService.listAll()).thenReturn(response);

        ResponseEntity<?> result = reviewController.listAllReviews();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
        verify(reviewService, times(1)).listAll();
    }

    @Test
    void testCountReviews() {
        int count = 10;

        when(reviewService.count()).thenReturn(count);

        ResponseEntity<?> result = reviewController.countReviews();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(count, result.getBody());
        verify(reviewService, times(1)).count();
    }

    @Test
    void testHandleIllegalArgumentException() {
        String errorMessage = "Invalid argument";

        ResponseEntity<?> result = reviewController.handleIllegalArgumentException(new IllegalArgumentException(errorMessage));

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals(errorMessage, result.getBody());
    }

    @Test
    void testHandleNoSuchElementException() {
        String errorMessage = "Element not found";

        ResponseEntity<?> result = reviewController.handleNoSuchElementException(new NoSuchElementException(errorMessage));

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertEquals(errorMessage, result.getBody());
    }
}
