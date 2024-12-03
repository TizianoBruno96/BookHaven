package com.example.bookHaven.controller;

import com.example.bookHaven.controller.utils.ResponseFactory;
import com.example.bookHaven.entity.dto.request.NotificationDTORequest;
import com.example.bookHaven.entity.dto.request.ReaderDTORequest;
import com.example.bookHaven.entity.dto.response.NotificationDTOResponse;
import com.example.bookHaven.service.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

import static com.example.bookHaven.controller.utils.ResponseFactory.ResponseType.*;

@RestController
@RequestMapping("/notification")
@CrossOrigin(origins = "http://localhost:3000")
public class NotificationController {

    @Autowired
    private INotificationService notificationService;

    @PostMapping
    public ResponseEntity<?> createNotification(@RequestBody NotificationDTORequest request) {
        NotificationDTOResponse response = notificationService.create(request);
        return ResponseFactory.getResponse(OK, response);
    }

    @PutMapping
    public ResponseEntity<?> updateNotification(@RequestBody NotificationDTORequest request) {
        NotificationDTOResponse response = notificationService.update(request);
        return ResponseFactory.getResponse(OK, response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getNotificationById(@PathVariable String id) {
        NotificationDTOResponse response = notificationService.findById(id);
        return ResponseFactory.getResponse(OK, response);
    }

    @GetMapping("/reader/{readerId}")
    public ResponseEntity<?> getNotificationsByReaderId(@PathVariable String readerId) {
        List<NotificationDTOResponse> response = notificationService.findByReader(readerId);
        return ResponseFactory.getResponse(OK, response);
    }

    @PostMapping("/reader")
    public ResponseEntity<?> getNotificationsByReader(@RequestBody ReaderDTORequest readerRequest) {
        List<NotificationDTOResponse> response = notificationService.findByReader(readerRequest);
        return ResponseFactory.getResponse(OK, response);
    }

    @GetMapping("/exists/{id}")
    public ResponseEntity<?> checkNotificationExistsById(@PathVariable String id) {
        boolean exists = notificationService.existsById(id);
        return ResponseFactory.getResponse(OK, exists);
    }

    @GetMapping("/exists/reader/{readerId}")
    public ResponseEntity<?> checkNotificationsExistsByReaderId(@PathVariable String readerId) {
        boolean exists = notificationService.existsByReader(readerId);
        return ResponseFactory.getResponse(OK, exists);
    }

    @PostMapping("/exists/reader")
    public ResponseEntity<?> checkNotificationsExistsByReader(@RequestBody ReaderDTORequest readerRequest) {
        boolean exists = notificationService.existsByReader(readerRequest);
        return ResponseFactory.getResponse(OK, exists);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNotificationById(@PathVariable String id) {
        boolean deleted = notificationService.deleteById(id);
        return ResponseFactory.getResponse(OK, deleted);
    }

    @DeleteMapping("/reader/{readerId}")
    public ResponseEntity<?> deleteNotificationsByReaderId(@PathVariable String readerId) {
        boolean deleted = notificationService.deleteByReader(readerId);
        return ResponseFactory.getResponse(OK, deleted);
    }

    @PostMapping("/delete/reader")
    public ResponseEntity<?> deleteNotificationsByReader(@RequestBody ReaderDTORequest readerRequest) {
        boolean deleted = notificationService.deleteByReader(readerRequest);
        return ResponseFactory.getResponse(OK, deleted);
    }

    @GetMapping
    public ResponseEntity<?> listAllNotifications() {
        List<NotificationDTOResponse> response = notificationService.listAll();
        return ResponseFactory.getResponse(OK, response);
    }

    @GetMapping("/count")
    public ResponseEntity<?> countNotifications() {
        int count = notificationService.count();
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
