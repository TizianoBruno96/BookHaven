package com.example.bookHaven.controller;

import com.example.bookHaven.controller.utils.ResponseFactory;
import com.example.bookHaven.entity.dto.request.BookDTORequest;
import com.example.bookHaven.entity.dto.response.BookDTOResponse;
import com.example.bookHaven.service.IBookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.NoSuchElementException;

import static com.example.bookHaven.controller.utils.ResponseFactory.ResponseType.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {

    @InjectMocks
    private BookController controller;

    @Mock
    private IBookService bookService;

    private BookDTORequest bookRequest;
    private BookDTOResponse bookResponse;

    @BeforeEach
    void setUp() {
        bookRequest = new BookDTORequest();
        bookResponse = new BookDTOResponse();
    }

    @Test
    void testCreateBook() {
        when(bookService.create(bookRequest)).thenReturn(bookResponse);

        ResponseEntity<?> response = controller.createBook(bookRequest);

        assertNotNull(response);
        assertEquals(ResponseFactory.getResponse(OK, bookResponse).getStatusCode(), response.getStatusCode());
        verify(bookService).create(bookRequest);
    }

    @Test
    void testUpdateBook() {
        when(bookService.update(bookRequest)).thenReturn(bookResponse);

        ResponseEntity<?> response = controller.updateBook(bookRequest);

        assertNotNull(response);
        assertEquals(ResponseFactory.getResponse(OK, bookResponse).getStatusCode(), response.getStatusCode());
        verify(bookService).update(bookRequest);
    }

    @Test
    void testFindById_Success() {
        String id = "test-id";
        when(bookService.findById(id)).thenReturn(bookResponse);

        ResponseEntity<?> response = controller.findById(id);

        assertNotNull(response);
        assertEquals(ResponseFactory.getResponse(OK, bookResponse).getStatusCode(), response.getStatusCode());
        verify(bookService).findById(id);
    }

    @Test
    void testFindById_NotFound() {
        String id = "non-existent-id";
        when(bookService.findById(id)).thenReturn(null);

        ResponseEntity<?> response = controller.findById(id);

        assertNotNull(response);
        assertEquals(ResponseFactory.getResponse(NOT_FOUND, "Book not found for ID: " + id).getStatusCode(),
                response.getStatusCode());
        verify(bookService).findById(id);
    }

    @Test
    void testSearchBooks() {
        String title = "title";
        String genre = "genre";
        String author = "author";
        List<BookDTOResponse> bookList = List.of(bookResponse);

        when(bookService.searchBooks(title, genre, author)).thenReturn(bookList);

        ResponseEntity<?> response = controller.searchBooks(title, genre, author);

        assertNotNull(response);
        assertEquals(ResponseFactory.getResponse(OK, bookList).getStatusCode(), response.getStatusCode());
        verify(bookService).searchBooks(title, genre, author);
    }

    @Test
    void testDeleteById_Success() {
        String id = "test-id";
        when(bookService.deleteById(id)).thenReturn(true);

        ResponseEntity<?> response = controller.deleteById(id);

        assertNotNull(response);
        assertEquals(ResponseFactory.getResponse(OK, "Book deleted successfully.").getStatusCode(),
                response.getStatusCode());
        verify(bookService).deleteById(id);
    }

    @Test
    void testDeleteById_NotFound() {
        String id = "test-id";
        when(bookService.deleteById(id)).thenReturn(false);

        ResponseEntity<?> response = controller.deleteById(id);

        assertNotNull(response);
        assertEquals(ResponseFactory.getResponse(NOT_FOUND, "Book not found for ID: " + id).getStatusCode(),
                response.getStatusCode());
        verify(bookService).deleteById(id);
    }

    @Test
    void testListAllBooks() {
        List<BookDTOResponse> books = List.of(bookResponse);
        when(bookService.listAll()).thenReturn(books);

        ResponseEntity<?> response = controller.listAllBooks();

        assertNotNull(response);
        assertEquals(ResponseFactory.getResponse(OK, books).getStatusCode(), response.getStatusCode());
        verify(bookService).listAll();
    }

    @Test
    void testCountBooks() {
        long count = 10;
        when(bookService.count()).thenReturn(count);

        ResponseEntity<?> response = controller.countBooks();

        assertNotNull(response);
        assertEquals(ResponseFactory.getResponse(OK, count).getStatusCode(), response.getStatusCode());
        verify(bookService).count();
    }

    @Test
    void testHandleIllegalArgumentException() {
        String errorMessage = "Invalid argument";
        ResponseEntity<?> response = controller.handleIllegalArgumentException(new IllegalArgumentException(errorMessage));

        assertNotNull(response);
        assertEquals(ResponseFactory.getResponse(BAD_REQUEST, errorMessage).getStatusCode(), response.getStatusCode());
    }

    @Test
    void testHandleNoSuchElementException() {
        String errorMessage = "Element not found";
        ResponseEntity<?> response = controller.handleNoSuchElementException(new NoSuchElementException(errorMessage));

        assertNotNull(response);
        assertEquals(ResponseFactory.getResponse(NOT_FOUND, errorMessage).getStatusCode(), response.getStatusCode());
    }
}