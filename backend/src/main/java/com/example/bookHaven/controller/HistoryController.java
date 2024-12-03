package com.example.bookHaven.controller;

import com.example.bookHaven.controller.utils.ResponseFactory;
import com.example.bookHaven.entity.dto.request.BookDTORequest;
import com.example.bookHaven.entity.dto.request.HistoryDTORequest;
import com.example.bookHaven.entity.dto.request.ReaderDTORequest;
import com.example.bookHaven.entity.dto.response.HistoryDTOResponse;
import com.example.bookHaven.service.IHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

import static com.example.bookHaven.controller.utils.ResponseFactory.ResponseType.*;

@RestController
@RequestMapping("/history")
public class HistoryController {

    @Autowired
    private IHistoryService historyService;

    @PostMapping("/create")
    public ResponseEntity<?> createHistory(@RequestBody HistoryDTORequest historyDTORequest) {
        HistoryDTOResponse createdHistory = historyService.create(historyDTORequest);
        return ResponseFactory.getResponse(OK, createdHistory);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateHistory(@RequestBody HistoryDTORequest historyDTORequest) {
        HistoryDTOResponse updatedHistory = historyService.update(historyDTORequest);
        return ResponseFactory.getResponse(OK, updatedHistory);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        try {
            HistoryDTOResponse history = historyService.findById(id);
            return ResponseFactory.getResponse(OK, history);
        } catch (IllegalArgumentException e) {
            return ResponseFactory.getResponse(NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/findByBook/{bookId}")
    public ResponseEntity<?> findByBook(@PathVariable String bookId) {
        List<HistoryDTOResponse> histories = historyService.findByBook(bookId);
        return ResponseFactory.getResponse(OK, histories);
    }

    @GetMapping("/findByBook")
    public ResponseEntity<?> findByBook(@RequestBody BookDTORequest bookDTORequest) {
        List<HistoryDTOResponse> histories = historyService.findByBook(bookDTORequest);
        return ResponseFactory.getResponse(OK, histories);
    }

    @GetMapping("/findByReader/{readerId}")
    public ResponseEntity<?> findByReader(@PathVariable String readerId) {
        List<HistoryDTOResponse> histories = historyService.findByReader(readerId);
        return ResponseFactory.getResponse(OK, histories);
    }

    @GetMapping("/findByReader")
    public ResponseEntity<?> findByReader(@RequestBody ReaderDTORequest readerDTORequest) {
        List<HistoryDTOResponse> histories = historyService.findByReader(readerDTORequest);
        return ResponseFactory.getResponse(OK, histories);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        boolean isDeleted = historyService.deleteById(id);
        if (isDeleted) {
            return ResponseFactory.getResponse(OK, "History deleted successfully.");
        } else {
            return ResponseFactory.getResponse(NOT_FOUND, "History not found for ID: " + id);
        }
    }

    @DeleteMapping("/deleteByBook/{bookId}")
    public ResponseEntity<?> deleteByBook(@PathVariable String bookId) {
        boolean isDeleted = historyService.deleteByBook(bookId);
        if (isDeleted) {
            return ResponseFactory.getResponse(OK, "History deleted successfully.");
        } else {
            return ResponseFactory.getResponse(NOT_FOUND, "History not found for Book ID: " + bookId);
        }
    }

    @DeleteMapping("/deleteByBook")
    public ResponseEntity<?> deleteByBook(@RequestBody BookDTORequest bookDTORequest) {
        boolean isDeleted = historyService.deleteByBook(bookDTORequest);
        if (isDeleted) {
            return ResponseFactory.getResponse(OK, "History deleted successfully.");
        } else {
            return ResponseFactory.getResponse(NOT_FOUND, "History not found for Book: " + bookDTORequest.getTitle());
        }
    }

    @DeleteMapping("/deleteByReader/{readerId}")
    public ResponseEntity<?> deleteByReader(@PathVariable String readerId) {
        boolean isDeleted = historyService.deleteByReader(readerId);
        if (isDeleted) {
            return ResponseFactory.getResponse(OK, "History deleted successfully.");
        } else {
            return ResponseFactory.getResponse(NOT_FOUND, "History not found for Reader ID: " + readerId);
        }
    }

    @DeleteMapping("/deleteByReader")
    public ResponseEntity<?> deleteByReader(@RequestBody ReaderDTORequest readerDTORequest) {
        boolean isDeleted = historyService.deleteByReader(readerDTORequest);
        if (isDeleted) {
            return ResponseFactory.getResponse(OK, "History deleted successfully.");
        } else {
            return ResponseFactory.getResponse(NOT_FOUND, "History not found for Reader: " + readerDTORequest);
        }
    }

    @GetMapping("/existsByBook/{bookId}")
    public ResponseEntity<?> existsByBook(@PathVariable String bookId) {
        boolean exists = historyService.existsByBook(bookId);
        return ResponseFactory.getResponse(OK, exists);
    }

    @GetMapping("/existsByBook")
    public ResponseEntity<?> existsByBook(@RequestBody BookDTORequest bookDTORequest) {
        boolean exists = historyService.existsByBook(bookDTORequest);
        return ResponseFactory.getResponse(OK, exists);
    }

    @GetMapping("/existsByReader/{readerId}")
    public ResponseEntity<?> existsByReader(@PathVariable String readerId) {
        boolean exists = historyService.existsByReader(readerId);
        return ResponseFactory.getResponse(OK, exists);
    }

    @GetMapping("/existsByReader")
    public ResponseEntity<?> existsByReader(@RequestBody ReaderDTORequest readerDTORequest) {
        boolean exists = historyService.existsByReader(readerDTORequest);
        return ResponseFactory.getResponse(OK, exists);
    }

    @GetMapping("/listAll")
    public ResponseEntity<?> listAllHistories() {
        List<HistoryDTOResponse> histories = historyService.listAll();
        return ResponseFactory.getResponse(OK, histories);
    }

    @GetMapping("/count")
    public ResponseEntity<?> countHistories() {
        int count = historyService.count();
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
