package com.example.bookHaven.controller;

import com.example.bookHaven.entity.History;
import com.example.bookHaven.service.IHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/history")
public class HistoryController {

    @Autowired
    private IHistoryService historyService;

    @PostMapping("/create")
    public ResponseEntity<History> createHistory(@RequestBody History history) {
        History createdHistory = historyService.create(history);
        return ResponseEntity.ok(createdHistory);
    }

    @PutMapping("/update")
    public ResponseEntity<History> updateHistory(@RequestBody History history) {
        try {
            History updatedHistory = historyService.update(history);
            return ResponseEntity.ok(updatedHistory);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<History> findById(@PathVariable String id) {
        try {
            History history = historyService.findById(id);
            return ResponseEntity.ok(history);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/findByBook/{bookId}")
    public ResponseEntity<List<History>> findByBook(@PathVariable String bookId) {
        List<History> histories = historyService.findByBook(bookId);
        return ResponseEntity.ok(histories);
    }

    @GetMapping("/findByReader/{readerId}")
    public ResponseEntity<List<History>> findByReader(@PathVariable String readerId) {
        List<History> histories = historyService.findByReader(readerId);
        return ResponseEntity.ok(histories);
    }

    @GetMapping("/existsById/{id}")
    public ResponseEntity<Boolean> existsById(@PathVariable String id) {
        boolean exists = historyService.existsById(id);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/existsByBook/{bookId}")
    public ResponseEntity<Boolean> existsByBook(@PathVariable String bookId) {
        boolean exists = historyService.existsByBook(bookId);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/existsByReader/{readerId}")
    public ResponseEntity<Boolean> existsByReader(@PathVariable String readerId) {
        boolean exists = historyService.existsByReader(readerId);
        return ResponseEntity.ok(exists);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        boolean isDeleted = historyService.deleteById(id);
        return isDeleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deleteByBook/{bookId}")
    public ResponseEntity<Void> deleteByBook(@PathVariable String bookId) {
        boolean isDeleted = historyService.deleteByBook(bookId);
        return isDeleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deleteByReader/{readerId}")
    public ResponseEntity<Void> deleteByReader(@PathVariable String readerId) {
        boolean isDeleted = historyService.deleteByReader(readerId);
        return isDeleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> countHistories() {
        int count = historyService.count();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<History>> listAllHistories() {
        List<History> histories = historyService.listAll();
        return ResponseEntity.ok(histories);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
