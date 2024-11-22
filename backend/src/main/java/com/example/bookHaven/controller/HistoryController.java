package com.example.bookHaven.controller;

import com.example.bookHaven.entity.dto.request.BookDTORequest;
import com.example.bookHaven.entity.dto.request.HistoryDTORequest;
import com.example.bookHaven.entity.dto.request.ReaderDTORequest;
import com.example.bookHaven.entity.dto.response.HistoryDTOResponse;
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
    public ResponseEntity<HistoryDTOResponse> createHistory(@RequestBody HistoryDTORequest historyDTORequest) {
        HistoryDTOResponse createdHistory = historyService.create(historyDTORequest);
        return ResponseEntity.ok(createdHistory);
    }

    @PutMapping("/update")
    public ResponseEntity<HistoryDTOResponse> updateHistory(@RequestBody HistoryDTORequest historyDTORequest) {
        try {
            HistoryDTOResponse updatedHistory = historyService.update(historyDTORequest);
            return ResponseEntity.ok(updatedHistory);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<HistoryDTOResponse> findById(@PathVariable String id) {
        try {
            HistoryDTOResponse history = historyService.findById(id);
            return ResponseEntity.ok(history);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/findByBook/{bookId}")
    public ResponseEntity<List<HistoryDTOResponse>> findByBook(@PathVariable String bookId) {
        List<HistoryDTOResponse> histories = historyService.findByBook(bookId);
        return ResponseEntity.ok(histories);
    }

    @GetMapping("/findByBook")
    public ResponseEntity<List<HistoryDTOResponse>> findByBook(@RequestBody BookDTORequest bookDTORequest) {
        List<HistoryDTOResponse> histories = historyService.findByBook(bookDTORequest);
        return ResponseEntity.ok(histories);
    }

    @GetMapping("/findByReader/{readerId}")
    public ResponseEntity<List<HistoryDTOResponse>> findByReader(@PathVariable String readerId) {
        List<HistoryDTOResponse> histories = historyService.findByReader(readerId);
        return ResponseEntity.ok(histories);
    }

    @GetMapping("/findByReader")
    public ResponseEntity<List<HistoryDTOResponse>> findByReader(@RequestBody ReaderDTORequest readerDTORequest) {
        List<HistoryDTOResponse> histories = historyService.findByReader(readerDTORequest);
        return ResponseEntity.ok(histories);
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

    @DeleteMapping("/deleteByBook")
    public ResponseEntity<Void> deleteByBook(@RequestBody BookDTORequest bookDTORequest) {
        boolean isDeleted = historyService.deleteByBook(bookDTORequest);
        return isDeleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deleteByReader/{readerId}")
    public ResponseEntity<Void> deleteByReader(@PathVariable String readerId) {
        boolean isDeleted = historyService.deleteByReader(readerId);
        return isDeleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deleteByReader")
    public ResponseEntity<Void> deleteByReader(@RequestBody ReaderDTORequest readerDTORequest) {
        boolean isDeleted = historyService.deleteByReader(readerDTORequest);
        return isDeleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/existsByBook/{bookId}")
    public ResponseEntity<Boolean> existsByBook(@PathVariable String bookId) {
        boolean exists = historyService.existsByBook(bookId);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/existsByBook")
    public ResponseEntity<Boolean> existsByBook(@RequestBody BookDTORequest bookDTORequest) {
        boolean exists = historyService.existsByBook(bookDTORequest);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/existsByReader/{readerId}")
    public ResponseEntity<Boolean> existsByReader(@PathVariable String readerId) {
        boolean exists = historyService.existsByReader(readerId);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/existsByReader")
    public ResponseEntity<Boolean> existsByReader(@RequestBody ReaderDTORequest readerDTORequest) {
        boolean exists = historyService.existsByReader(readerDTORequest);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<HistoryDTOResponse>> listAllHistories() {
        List<HistoryDTOResponse> histories = historyService.listAll();
        return ResponseEntity.ok(histories);
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> countHistories() {
        int count = historyService.count();
        return ResponseEntity.ok(count);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
