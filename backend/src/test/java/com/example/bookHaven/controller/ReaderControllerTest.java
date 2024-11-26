package com.example.bookHaven.controller;

import com.example.bookHaven.entity.dto.request.ReaderDTORequest;
import com.example.bookHaven.entity.dto.response.ReaderDTOResponse;
import com.example.bookHaven.service.IReaderService;
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

class ReaderControllerTest {

    @Mock
    private IReaderService readerService;

    @InjectMocks
    private ReaderController readerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateReader() {
        ReaderDTORequest request = new ReaderDTORequest();
        ReaderDTOResponse response = new ReaderDTOResponse();

        when(readerService.create(request)).thenReturn(response);

        ResponseEntity<?> result = readerController.createReader(request);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
        verify(readerService, times(1)).create(request);
    }

    @Test
    void testUpdateReader() {
        ReaderDTORequest request = new ReaderDTORequest();
        ReaderDTOResponse response = new ReaderDTOResponse();

        when(readerService.update(request)).thenReturn(response);

        ResponseEntity<?> result = readerController.updateReader(request);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
        verify(readerService, times(1)).update(request);
    }

    @Test
    void testFindById() {
        String id = "test-id";
        ReaderDTOResponse response = new ReaderDTOResponse();

        when(readerService.findById(id)).thenReturn(response);

        ResponseEntity<?> result = readerController.findById(id);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
        verify(readerService, times(1)).findById(id);
    }

    @Test
    void testFindByUsername() {
        String username = "test-username";
        ReaderDTOResponse response = new ReaderDTOResponse();

        when(readerService.findByUsername(username)).thenReturn(response);

        ResponseEntity<?> result = readerController.findByUsername(username);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
        verify(readerService, times(1)).findByUsername(username);
    }

    @Test
    void testExistsByEmail() {
        String email = "test@example.com";

        when(readerService.existsByEmail(email)).thenReturn(true);

        ResponseEntity<?> result = readerController.existByEmail(email);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(true, result.getBody());
        verify(readerService, times(1)).existsByEmail(email);
    }

    @Test
    void testDeleteById() {
        String id = "test-id";

        when(readerService.deleteById(id)).thenReturn(true);

        ResponseEntity<?> result = readerController.deleteById(id);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(true, result.getBody());
        verify(readerService, times(1)).deleteById(id);
    }

    @Test
    void testDeleteByUsername() {
        String username = "test-username";

        when(readerService.deleteByUsername(username)).thenReturn(true);

        ResponseEntity<?> result = readerController.deleteByUsername(username);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(true, result.getBody());
        verify(readerService, times(1)).deleteByUsername(username);
    }

    @Test
    void testCountReaders() {
        long count = 10;

        when(readerService.count()).thenReturn(count);

        ResponseEntity<?> result = readerController.countReaders();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(count, result.getBody());
        verify(readerService, times(1)).count();
    }

    @Test
    void testListAllReaders() {
        List<ReaderDTOResponse> response = Collections.singletonList(new ReaderDTOResponse());

        when(readerService.listAll()).thenReturn(response);

        ResponseEntity<?> result = readerController.listAllReaders();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
        verify(readerService, times(1)).listAll();
    }

    @Test
    void testCheckPassword() {
        String username = "test-username";
        String password = "password";

        when(readerService.checkUserPassword(username, password)).thenReturn(true);

        ResponseEntity<?> result = readerController.checkPassword(username, password);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(true, result.getBody());
        verify(readerService, times(1)).checkUserPassword(username, password);
    }

    @Test
    void testAddFriend() {
        String readerId = "reader-id";
        String friendId = "friend-id";

        doNothing().when(readerService).addFriend(readerId, friendId);

        ResponseEntity<?> result = readerController.addFriend(readerId, friendId);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(readerService, times(1)).addFriend(readerId, friendId);
    }

    @Test
    void testAddFriendNotFound() {
        String readerId = "reader-id";
        String friendId = "friend-id";

        doThrow(new NoSuchElementException("Invalid friend ID")).when(readerService).addFriend(readerId, friendId);

        ResponseEntity<?> result = readerController.addFriend(readerId, friendId);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        verify(readerService, times(1)).addFriend(readerId, friendId);
    }
}
