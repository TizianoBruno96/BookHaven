package com.example.bookHaven.controller;

import com.example.bookHaven.entity.dto.request.ReaderDTORequest;
import com.example.bookHaven.entity.dto.response.ReaderDTOResponse;
import com.example.bookHaven.service.IReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/reader")
public class ReaderController {

    @Autowired
    private IReaderService readerService;

    @PostMapping("/create")
    public ResponseEntity<ReaderDTOResponse> createReader(@RequestBody ReaderDTORequest request) {
        try {
            ReaderDTOResponse createdReader = readerService.create(request);
            return ResponseEntity.ok(createdReader);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ReaderDTOResponse> updateReader(@RequestBody ReaderDTORequest request) {
        try {
            ReaderDTOResponse updatedReader = readerService.update(request);
            return ResponseEntity.ok(updatedReader);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<ReaderDTOResponse> findById(@PathVariable String id) {
        ReaderDTOResponse reader = readerService.findById(id);
        if (reader != null) {
            return ResponseEntity.ok(reader);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/findByUsername/{username}")
    public ResponseEntity<ReaderDTOResponse> findByUsername(@PathVariable String username) {
        ReaderDTOResponse reader = readerService.findByUsername(username);
        if (reader != null) {
            return ResponseEntity.ok(reader);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/findByEmail/{email}")
    public ResponseEntity<ReaderDTOResponse> findByEmail(@PathVariable String email) {
        ReaderDTOResponse reader = readerService.findByEmail(email);
        if (reader != null) {
            return ResponseEntity.ok(reader);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/existsById/{id}")
    public ResponseEntity<Boolean> existById(@PathVariable String id) {
        boolean exists = readerService.existById(id);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/existsByUsername/{username}")
    public ResponseEntity<Boolean> existByUsername(@PathVariable String username) {
        boolean exists = readerService.existByUsername(username);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/existsByEmail/{email}")
    public ResponseEntity<Boolean> existByEmail(@PathVariable String email) {
        boolean exists = readerService.existByEmail(email);
        return ResponseEntity.ok(exists);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        boolean isDeleted = readerService.deleteById(id);
        return isDeleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deleteByUsername/{username}")
    public ResponseEntity<Void> deleteByUsername(@PathVariable String username) {
        boolean isDeleted = readerService.deleteByUsername(username);
        return isDeleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deleteByEmail/{email}")
    public ResponseEntity<Void> deleteByEmail(@PathVariable String email) {
        boolean isDeleted = readerService.deleteByEmail(email);
        return isDeleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countReaders() {
        long count = readerService.count();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<ReaderDTOResponse>> listAllReaders() {
        List<ReaderDTOResponse> readers = readerService.listAll();
        return ResponseEntity.ok(readers);
    }

    @PostMapping("/checkPassword")
    public ResponseEntity<Boolean> checkPassword(@RequestParam String username, @RequestParam String password) {
        boolean isValid = readerService.checkUserPassword(username, password);
        return ResponseEntity.ok(isValid);
    }

    @PostMapping("/addFriend")
    public ResponseEntity<Void> addFriend(@RequestParam String readerId, @RequestParam String friendId) {
        try {
            readerService.addFriend(readerId, friendId);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }
}
