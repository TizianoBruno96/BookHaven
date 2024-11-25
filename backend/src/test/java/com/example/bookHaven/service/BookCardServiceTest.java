package com.example.bookHaven.service;

import com.example.bookHaven.entity.BookCard;
import com.example.bookHaven.entity.dto.request.BookCardDTORequest;
import com.example.bookHaven.entity.dto.response.BookCardDTOResponse;
import com.example.bookHaven.repository.BookCardRepository;
import com.example.bookHaven.service.implementation.BookCardService;
import com.example.bookHaven.service.mappers.BookCardMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookCardServiceTest {

    @InjectMocks
    private BookCardService service;
    @Mock
    private BookCardRepository bookCardRepository;
    @Mock
    private BookCardMapper bookCardMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        BookCardDTORequest request = new BookCardDTORequest();
        BookCard bookCard = new BookCard();
        BookCard savedBookCard = new BookCard();
        BookCardDTOResponse response = new BookCardDTOResponse();

        when(bookCardMapper.toEntity(request)).thenReturn(bookCard);
        when(bookCardRepository.save(bookCard)).thenReturn(savedBookCard);
        when(bookCardMapper.toResponse(savedBookCard)).thenReturn(response);

        BookCardDTOResponse result = service.create(request);

        assertNotNull(result);
        verify(bookCardMapper).toEntity(request);
        verify(bookCardRepository).save(bookCard);
        verify(bookCardMapper).toResponse(savedBookCard);
    }

    @Test
    void testUpdate() {
        BookCardDTORequest request = new BookCardDTORequest();
        BookCard bookCard = new BookCard();
        BookCard updatedBookCard = new BookCard();
        BookCardDTOResponse response = new BookCardDTOResponse();

        when(bookCardMapper.toEntity(request)).thenReturn(bookCard);
        when(bookCardRepository.save(bookCard)).thenReturn(updatedBookCard);
        when(bookCardMapper.toResponse(updatedBookCard)).thenReturn(response);

        BookCardDTOResponse result = service.update(request);

        assertNotNull(result);
        verify(bookCardMapper).toEntity(request);
        verify(bookCardRepository).save(bookCard);
        verify(bookCardMapper).toResponse(updatedBookCard);
    }

    @Test
    void testFindById() {
        String id = "test-id";
        BookCard bookCard = new BookCard();
        BookCardDTOResponse response = new BookCardDTOResponse();

        when(bookCardRepository.findById(id)).thenReturn(Optional.of(bookCard));
        when(bookCardMapper.toResponse(bookCard)).thenReturn(response);

        BookCardDTOResponse result = service.findById(id);

        assertNotNull(result);
        verify(bookCardRepository).findById(id);
        verify(bookCardMapper).toResponse(bookCard);
    }

    @Test
    void testFindById_NotFound() {
        String id = "test-id";

        when(bookCardRepository.findById(id)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> service.findById(id));
        assertEquals("BookCard with ID " + id + " not found.", exception.getMessage());
    }

    @Test
    void testFindByBookId() {
        String bookId = "book-id";
        BookCard bookCard = new BookCard();
        BookCardDTOResponse response = new BookCardDTOResponse();

        when(bookCardRepository.findByBookId(bookId)).thenReturn(List.of(bookCard));
        when(bookCardMapper.toResponse(bookCard)).thenReturn(response);

        List<BookCardDTOResponse> result = service.findByBook(bookId);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(bookCardRepository).findByBookId(bookId);
        verify(bookCardMapper).toResponse(bookCard);
    }

    @Test
    void testDeleteById() {
        String id = "test-id";

        when(bookCardRepository.existsById(id)).thenReturn(true);

        boolean result = service.deleteById(id);

        assertTrue(result);
        verify(bookCardRepository).existsById(id);
        verify(bookCardRepository).deleteById(id);
    }

    @Test
    void testDeleteById_NotFound() {
        String id = "test-id";

        when(bookCardRepository.existsById(id)).thenReturn(false);

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> service.deleteById(id));
        assertEquals("BookCard with ID " + id + " not found.", exception.getMessage());
        verify(bookCardRepository).existsById(id);
    }

    @Test
    void testListAll() {
        BookCard bookCard = new BookCard();
        BookCardDTOResponse response = new BookCardDTOResponse();

        when(bookCardRepository.findAll()).thenReturn(List.of(bookCard));
        when(bookCardMapper.toResponse(bookCard)).thenReturn(response);

        List<BookCardDTOResponse> result = service.listAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(bookCardRepository).findAll();
        verify(bookCardMapper).toResponse(bookCard);
    }

    @Test
    void testCount() {
        when(bookCardRepository.count()).thenReturn(5L);

        int result = service.count();

        assertEquals(5, result);
        verify(bookCardRepository).count();
    }
}
