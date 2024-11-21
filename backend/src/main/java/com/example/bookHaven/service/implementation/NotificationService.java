package com.example.bookHaven.service.implementation;

import com.example.bookHaven.entity.Notification;
import com.example.bookHaven.entity.Reader;
import com.example.bookHaven.repository.NotificationRepository;
import com.example.bookHaven.service.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService implements INotificationService {

    @Autowired
    private NotificationRepository repository;

    @Override
    public Notification create(Notification notification) {
        return repository.save(notification);
    }

    @Override
    public Notification update(Notification notification) {
        if (!repository.existsById(notification.getId())) {
            throw new IllegalArgumentException("Notification with ID " + notification.getId() + " does not exist.");
        }
        return repository.save(notification);
    }

    @Override
    public Notification findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Notification with ID " + id + " not found."));
    }

    @Override
    public List<Notification> findByReader(String readerId) {
        return repository.findByReaderId(readerId);
    }

    @Override
    public List<Notification> findByReader(Reader reader) {
        return repository.findByReader(reader);
    }

    @Override
    public boolean existsById(String id) {
        return repository.existsById(id);
    }

    @Override
    public boolean existsByReader(String readerId) {
        return repository.existsByReaderId(readerId);
    }

    @Override
    public boolean existsByReader(Reader reader) {
        return repository.existsByReader(reader);
    }

    @Override
    public boolean deleteById(String id) {
        if (!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }

    @Override
    public boolean deleteByReader(String readerId) {
        List<Notification> notifications = repository.findByReaderId(readerId);
        if (notifications.isEmpty()) {
            return false;
        }
        repository.deleteAll(notifications);
        return true;
    }

    @Override
    public boolean deleteByReader(Reader reader) {
        List<Notification> notifications = repository.findByReader(reader);
        if (notifications.isEmpty()) {
            return false;
        }
        repository.deleteAll(notifications);
        return true;
    }

    @Override
    public List<Notification> listAll() {
        return repository.findAll();
    }

    @Override
    public int count() {
        return (int) repository.count();
    }
}
