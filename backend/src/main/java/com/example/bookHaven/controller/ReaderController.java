package com.example.bookHaven.controller;

import com.example.bookHaven.controller.utils.ResponseFactory;
import com.example.bookHaven.entity.dto.request.ReaderDTORequest;
import com.example.bookHaven.entity.dto.response.ReaderDTOResponse;
import com.example.bookHaven.service.IReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

import static com.example.bookHaven.controller.utils.ResponseFactory.ResponseType.*;

@RestController
@RequestMapping("/reader")
@CrossOrigin(origins = "http://localhost:3000")
public class ReaderController {

    @Autowired
    private IReaderService readerService;

    @PostMapping("/create")
    public ResponseEntity<?> createReader(@RequestBody ReaderDTORequest request) {
        ReaderDTOResponse createdReader = readerService.create(request);
        return ResponseFactory.getResponse(OK, createdReader);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateReader(@RequestBody ReaderDTORequest request) {
        ReaderDTOResponse updatedReader = readerService.update(request);
        return ResponseFactory.getResponse(OK, updatedReader);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        ReaderDTOResponse reader = readerService.findById(id);
        return reader != null ? ResponseFactory.getResponse(OK, reader) :
                ResponseFactory.getResponse(NOT_FOUND);
    }

    @GetMapping("/findByUsername/{username}")
    public ResponseEntity<?> findByUsername(@PathVariable String username) {
        ReaderDTOResponse reader = readerService.findByUsername(username);
        return reader != null ? ResponseFactory.getResponse(OK, reader) :
                ResponseFactory.getResponse(NOT_FOUND);
    }

    @GetMapping("/findByEmail/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable String email) {
        ReaderDTOResponse reader = readerService.findByEmail(email);
        return reader != null ? ResponseFactory.getResponse(OK, reader) :
                ResponseFactory.getResponse(NOT_FOUND);
    }

    @GetMapping("/existsById/{id}")
    public ResponseEntity<?> existById(@PathVariable String id) {
        boolean exists = readerService.existsById(id);
        return ResponseFactory.getResponse(OK, exists);
    }

    @GetMapping("/existsByUsername/{username}")
    public ResponseEntity<?> existByUsername(@PathVariable String username) {
        boolean exists = readerService.existsByUsername(username);
        return ResponseFactory.getResponse(OK, exists);
    }

    @GetMapping("/existsByEmail/{email}")
    public ResponseEntity<?> existByEmail(@PathVariable String email) {
        boolean exists = readerService.existsByEmail(email);
        return ResponseFactory.getResponse(OK, exists);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        boolean isDeleted = readerService.deleteById(id);
        return ResponseFactory.getResponse(OK, isDeleted);
    }

    @DeleteMapping("/deleteByUsername/{username}")
    public ResponseEntity<?> deleteByUsername(@PathVariable String username) {
        boolean isDeleted = readerService.deleteByUsername(username);
        return ResponseFactory.getResponse(OK, isDeleted);
    }

    @DeleteMapping("/deleteByEmail/{email}")
    public ResponseEntity<?> deleteByEmail(@PathVariable String email) {
        boolean isDeleted = readerService.deleteByEmail(email);
        return ResponseFactory.getResponse(OK, isDeleted);
    }

    @GetMapping("/count")
    public ResponseEntity<?> countReaders() {
        long count = readerService.count();
        return ResponseFactory.getResponse(OK, count);
    }

    @GetMapping("/listAll")
    public ResponseEntity<?> listAllReaders() {
        List<ReaderDTOResponse> readers = readerService.listAll();
        return ResponseFactory.getResponse(OK, readers);
    }

    @PostMapping("/checkPassword")
    public ResponseEntity<?> checkPassword(@RequestParam String username, @RequestParam String password) {
        boolean isValid = readerService.checkUserPassword(username, password);
        return ResponseFactory.getResponse(OK, isValid);
    }

    @PostMapping("/addFriend")
    public ResponseEntity<?> addFriend(@RequestParam String readerId, @RequestParam String friendId) {
        try {
            readerService.addFriend(readerId, friendId);
        } catch (NoSuchElementException e) {
            return ResponseFactory.getResponse(NOT_FOUND, e.getMessage());
        }
        return ResponseFactory.getResponse(OK);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseFactory.getResponse(BAD_REQUEST, ex.getMessage());
    }
}
