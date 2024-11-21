package com.example.bookHaven.controller;

import com.example.bookHaven.entity.Notification;
import com.example.bookHaven.service.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private INotificationService notificationService;

    @PostMapping("/create")
    public ResponseEntity<Notification> createNotification(@RequestBody Notification notification) {
        Notification createdNotification = notificationService.create(notification);
        return ResponseEntity.ok(createdNotification);
    }

    @PutMapping("/update")
    public ResponseEntity<Notification> updateNotification(@RequestBody Notification notification) {
        try {
            Notification updatedNotification = notificationService.update(notification);
            return ResponseEntity.ok(updatedNotification);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Notification> findById(@PathVariable String id) {
        try {
            Notification notification = notificationService.findById(id);
            return ResponseEntity.ok(notification);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/findByReader/{readerId}")
    public ResponseEntity<List<Notification>> findByReader(@PathVariable String readerId) {
        List<Notification> notifications = notificationService.findByReader(readerId);
        return ResponseEntity.ok(notifications);
    }

    @GetMapping("/existsById/{id}")
    public ResponseEntity<Boolean> existsById(@PathVariable String id) {
        boolean exists = notificationService.existsById(id);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/existsByReader/{readerId}")
    public ResponseEntity<Boolean> existsByReader(@PathVariable String readerId) {
        boolean exists = notificationService.existsByReader(readerId);
        return ResponseEntity.ok(exists);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        boolean isDeleted = notificationService.deleteById(id);
        return isDeleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deleteByReader/{readerId}")
    public ResponseEntity<Void> deleteByReader(@PathVariable String readerId) {
        boolean isDeleted = notificationService.deleteByReader(readerId);
        return isDeleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> countNotifications() {
        int count = notificationService.count();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<Notification>> listAllNotifications() {
        List<Notification> notifications = notificationService.listAll();
        return ResponseEntity.ok(notifications);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
