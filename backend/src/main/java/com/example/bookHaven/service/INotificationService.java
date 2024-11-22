package com.example.bookHaven.service;

import com.example.bookHaven.entity.Notification;
import com.example.bookHaven.entity.Reader;

import java.util.List;

public interface INotificationService {
    Notification create(Notification Notification);

    Notification update(Notification Notification);

    Notification findById(String id);

    List<Notification> findByReader(String ReaderId);

    List<Notification> findByReader(Reader Reader);

    boolean existsById(String id);

    boolean existsByReader(String ReaderId);

    boolean existsByReader(Reader Reader);

    boolean deleteById(String id);

    boolean deleteByReader(String ReaderId);

    boolean deleteByReader(Reader Reader);

    List<Notification> listAll();

    int count();
}
