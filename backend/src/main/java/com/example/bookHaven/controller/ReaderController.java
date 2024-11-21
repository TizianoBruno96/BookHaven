package com.example.bookHaven.controller;

import com.example.bookHaven.service.IReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reader")
public class ReaderController {

    @Autowired
    private IReaderService readerService;

    @PostMapping("/addFriend")
    public ResponseEntity<Void> addFriend(@RequestParam String readerId, @RequestParam String friendId) {
        readerService.addFriend(readerId, friendId);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
