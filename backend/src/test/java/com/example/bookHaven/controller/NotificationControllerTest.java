package com.example.bookHaven.controller;

import com.example.bookHaven.entity.dto.request.NotificationDTORequest;
import com.example.bookHaven.entity.dto.response.NotificationDTOResponse;
import com.example.bookHaven.service.INotificationService;
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
import static org.mockito.Mockito.times;

class NotificationControllerTest {

    @Mock
    private INotificationService notificationService;

    @InjectMocks
    private NotificationController notificationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateNotification() {
        NotificationDTORequest request = new NotificationDTORequest();
        NotificationDTOResponse response = new NotificationDTOResponse();

        when(notificationService.create(request)).thenReturn(response);

        ResponseEntity<?> result = notificationController.createNotification(request);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
        verify(notificationService, times(1)).create(request);
    }

    @Test
    void testUpdateNotification() {
        NotificationDTORequest request = new NotificationDTORequest();
        NotificationDTOResponse response = new NotificationDTOResponse();

        when(notificationService.update(request)).thenReturn(response);

        ResponseEntity<?> result = notificationController.updateNotification(request);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
        verify(notificationService, times(1)).update(request);
    }

    @Test
    void testGetNotificationById() {
        String id = "notif-id";
        NotificationDTOResponse response = new NotificationDTOResponse();

        when(notificationService.findById(id)).thenReturn(response);

        ResponseEntity<?> result = notificationController.getNotificationById(id);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
        verify(notificationService, times(1)).findById(id);
    }

    @Test
    void testGetNotificationsByReaderId() {
        String readerId = "reader-id";
        List<NotificationDTOResponse> response = Collections.singletonList(new NotificationDTOResponse());

        when(notificationService.findByReader(readerId)).thenReturn(response);

        ResponseEntity<?> result = notificationController.getNotificationsByReaderId(readerId);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
        verify(notificationService, times(1)).findByReader(readerId);
    }

    @Test
    void testCheckNotificationExistsById() {
        String id = "notif-id";

        when(notificationService.existsById(id)).thenReturn(true);

        ResponseEntity<?> result = notificationController.checkNotificationExistsById(id);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(true, result.getBody());
        verify(notificationService, times(1)).existsById(id);
    }

    @Test
    void testDeleteNotificationById() {
        String id = "notif-id";

        when(notificationService.deleteById(id)).thenReturn(true);

        ResponseEntity<?> result = notificationController.deleteNotificationById(id);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(true, result.getBody());
        verify(notificationService, times(1)).deleteById(id);
    }

    @Test
    void testDeleteNotificationByIdNotFound() {
        String id = "invalid-id";

        when(notificationService.deleteById(id)).thenReturn(false);

        ResponseEntity<?> result = notificationController.deleteNotificationById(id);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(false, result.getBody());
        verify(notificationService, times(1)).deleteById(id);
    }

    @Test
    void testCountNotifications() {
        int count = 5;

        when(notificationService.count()).thenReturn(count);

        ResponseEntity<?> result = notificationController.countNotifications();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(count, result.getBody());
        verify(notificationService, times(1)).count();
    }

    @Test
    void testListAllNotifications() {
        List<NotificationDTOResponse> response = Collections.singletonList(new NotificationDTOResponse());

        when(notificationService.listAll()).thenReturn(response);

        ResponseEntity<?> result = notificationController.listAllNotifications();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
        verify(notificationService, times(1)).listAll();
    }
}