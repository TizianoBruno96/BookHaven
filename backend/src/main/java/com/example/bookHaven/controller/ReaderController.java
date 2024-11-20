package com.example.bookHaven.controller;

import com.example.bookHaven.service.IReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reader")
public class ReaderController {

    @Autowired
    private IReaderService readerService;

    @PostMapping("/addFriend")
    public void addFriend(@RequestParam String readerId, @RequestParam String friendId) {
        readerService.addFriend(readerId, friendId);
    }
}
