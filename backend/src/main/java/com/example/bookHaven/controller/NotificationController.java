package com.example.bookHaven.controller;

import com.example.bookHaven.entity.dto.request.NotificationDTORequest;
import com.example.bookHaven.entity.dto.request.ReaderDTORequest;
import com.example.bookHaven.entity.dto.response.NotificationDTOResponse;
import com.example.bookHaven.service.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private INotificationService notificationService;

    @PostMapping
    public ResponseEntity<NotificationDTOResponse> createNotification(@RequestBody NotificationDTORequest request) {
        NotificationDTOResponse response = notificationService.create(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<NotificationDTOResponse> updateNotification(@RequestBody NotificationDTORequest request) {
        NotificationDTOResponse response = notificationService.update(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationDTOResponse> getNotificationById(@PathVariable String id) {
        NotificationDTOResponse response = notificationService.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/reader/{readerId}")
    public ResponseEntity<List<NotificationDTOResponse>> getNotificationsByReaderId(@PathVariable String readerId) {
        List<NotificationDTOResponse> response = notificationService.findByReader(readerId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/reader")
    public ResponseEntity<List<NotificationDTOResponse>> getNotificationsByReader(@RequestBody ReaderDTORequest readerRequest) {
        List<NotificationDTOResponse> response = notificationService.findByReader(readerRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> checkNotificationExistsById(@PathVariable String id) {
        boolean exists = notificationService.existsById(id);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/exists/reader/{readerId}")
    public ResponseEntity<Boolean> checkNotificationsExistsByReaderId(@PathVariable String readerId) {
        boolean exists = notificationService.existsByReader(readerId);
        return ResponseEntity.ok(exists);
    }

    @PostMapping("/exists/reader")
    public ResponseEntity<Boolean> checkNotificationsExistsByReader(@RequestBody ReaderDTORequest readerRequest) {
        boolean exists = notificationService.existsByReader(readerRequest);
        return ResponseEntity.ok(exists);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotificationById(@PathVariable String id) {
        boolean deleted = notificationService.deleteById(id);
        return deleted ? new ResponseEntity<>(NO_CONTENT) : new ResponseEntity<>(NOT_FOUND);
    }

    @DeleteMapping("/reader/{readerId}")
    public ResponseEntity<Void> deleteNotificationsByReaderId(@PathVariable String readerId) {
        boolean deleted = notificationService.deleteByReader(readerId);
        return deleted ? new ResponseEntity<>(NO_CONTENT) : new ResponseEntity<>(NOT_FOUND);
    }

    @PostMapping("/delete/reader")
    public ResponseEntity<Void> deleteNotificationsByReader(@RequestBody ReaderDTORequest readerRequest) {
        boolean deleted = notificationService.deleteByReader(readerRequest);
        return deleted ? new ResponseEntity<>(NO_CONTENT) : new ResponseEntity<>(NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<NotificationDTOResponse>> listAllNotifications() {
        List<NotificationDTOResponse> response = notificationService.listAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> countNotifications() {
        int count = notificationService.count();
        return new ResponseEntity<>(count, OK);
    }
}
