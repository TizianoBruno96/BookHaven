package com.example.bookHaven.service.implementation;

import com.example.bookHaven.repository.NotificationRepository;
import com.example.bookHaven.service.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService implements INotificationService {

    @Autowired
    NotificationRepository repository;
}
