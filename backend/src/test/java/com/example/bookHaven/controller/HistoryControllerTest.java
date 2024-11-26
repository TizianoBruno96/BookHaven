package com.example.bookHaven.controller;

import com.example.bookHaven.entity.dto.request.HistoryDTORequest;
import com.example.bookHaven.entity.dto.response.HistoryDTOResponse;
import com.example.bookHaven.service.IHistoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class HistoryControllerTest {

    @Mock
    private IHistoryService historyService;

    @InjectMocks
    private HistoryController historyController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateHistory() {
        HistoryDTORequest request = new HistoryDTORequest();
        HistoryDTOResponse response = new HistoryDTOResponse();

        when(historyService.create(request)).thenReturn(response);

        ResponseEntity<?> result = historyController.createHistory(request);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
        verify(historyService, times(1)).create(request);
    }

    @Test
    void testUpdateHistory() {
        HistoryDTORequest request = new HistoryDTORequest();
        HistoryDTOResponse response = new HistoryDTOResponse();

        when(historyService.update(request)).thenReturn(response);

        ResponseEntity<?> result = historyController.updateHistory(request);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
        verify(historyService, times(1)).update(request);
    }

    @Test
    void testFindById() {
        String id = "test-id";
        HistoryDTOResponse response = new HistoryDTOResponse();

        when(historyService.findById(id)).thenReturn(response);

        ResponseEntity<?> result = historyController.findById(id);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
        verify(historyService, times(1)).findById(id);
    }

    @Test
    void testFindByIdNotFound() {
        String id = "invalid-id";

        when(historyService.findById(id)).thenThrow(new IllegalArgumentException("History not found"));

        ResponseEntity<?> result = historyController.findById(id);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertEquals("History not found", result.getBody());
        verify(historyService, times(1)).findById(id);
    }

    @Test
    void testFindByReader() {
        String readerId = "reader-id";
        List<HistoryDTOResponse> response = Collections.singletonList(new HistoryDTOResponse());

        when(historyService.findByReader(readerId)).thenReturn(response);

        ResponseEntity<?> result = historyController.findByReader(readerId);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
        verify(historyService, times(1)).findByReader(readerId);
    }

    @Test
    void testDeleteById() {
        String id = "test-id";

        when(historyService.deleteById(id)).thenReturn(true);

        ResponseEntity<?> result = historyController.deleteById(id);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("History deleted successfully.", result.getBody());
        verify(historyService, times(1)).deleteById(id);
    }

    @Test
    void testDeleteByIdNotFound() {
        String id = "invalid-id";

        when(historyService.deleteById(id)).thenReturn(false);

        ResponseEntity<?> result = historyController.deleteById(id);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertEquals("History not found for ID: " + id, result.getBody());
        verify(historyService, times(1)).deleteById(id);
    }

    @Test
    void testCountHistories() {
        int count = 10;

        when(historyService.count()).thenReturn(count);

        ResponseEntity<?> result = historyController.countHistories();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(count, result.getBody());
        verify(historyService, times(1)).count();
    }

    @Test
    void testListAllHistories() {
        List<HistoryDTOResponse> response = Collections.singletonList(new HistoryDTOResponse());

        when(historyService.listAll()).thenReturn(response);

        ResponseEntity<?> result = historyController.listAllHistories();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
        verify(historyService, times(1)).listAll();
    }
}