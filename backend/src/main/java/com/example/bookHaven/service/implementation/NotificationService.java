package com.example.bookHaven.service.implementation;

import com.example.bookHaven.entity.Notification;
import com.example.bookHaven.entity.Reader;
import com.example.bookHaven.entity.dto.request.NotificationDTORequest;
import com.example.bookHaven.entity.dto.request.ReaderDTORequest;
import com.example.bookHaven.entity.dto.response.NotificationDTOResponse;
import com.example.bookHaven.repository.NotificationRepository;
import com.example.bookHaven.repository.ReaderRepository;
import com.example.bookHaven.service.INotificationService;
import com.example.bookHaven.service.mappers.NotificationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class NotificationService implements INotificationService {

    @Autowired
    private NotificationRepository repository;
    @Autowired
    private NotificationMapper mapper;
    @Autowired
    private ReaderRepository readerRepository;

    @Override
    public NotificationDTOResponse create(NotificationDTORequest request) {
        Notification notification = mapper.toEntity(request);
        Notification savedNotification = repository.save(notification);
        return mapper.toResponse(savedNotification);
    }

    @Override
    public NotificationDTOResponse update(NotificationDTORequest request) {
        Notification notification = mapper.toEntity(request);
        Notification updatedNotification = repository.save(notification);
        return mapper.toResponse(updatedNotification);
    }

    @Override
    public NotificationDTOResponse findById(String id) {
        Notification notification = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Notification with ID " + id + " not found."));
        return mapper.toResponse(notification);
    }

    @Override
    public List<NotificationDTOResponse> findByReader(String readerId) {
        return repository.findByReaderId(readerId).stream().map(mapper::toResponse).toList();
    }

    @Override
    public List<NotificationDTOResponse> findByReader(ReaderDTORequest readerRequest) {
        Reader reader = searchReaders(readerRequest);
        if (reader == null) throw new NoSuchElementException("Reader " + readerRequest.getUsername() + " not found");
        return repository.findByReader(reader).stream().map(mapper::toResponse).toList();
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
    public boolean existsByReader(ReaderDTORequest readerRequest) {
        Reader reader = searchReaders(readerRequest);
        if (reader == null) throw new NoSuchElementException("Reader " + readerRequest.getUsername() + " not found");
        return repository.existsByReader(reader);
    }

    @Override
    public boolean deleteById(String id) {
        repository.deleteById(id);
        return !repository.existsById(id);
    }

    @Override
    public boolean deleteByReader(String readerId) {
        List<Notification> notifications = repository.findByReaderId(readerId);
        repository.deleteAll(notifications);
        return repository.findByReaderId(readerId).isEmpty();
    }

    @Override
    public boolean deleteByReader(ReaderDTORequest readerRequest) {
        Reader reader = searchReaders(readerRequest);
        if (reader == null) throw new NoSuchElementException("Reader " + readerRequest.getUsername() + " not found");
        List<Notification> notifications = repository.findByReader(reader);
        if (notifications.isEmpty()) {
            return false;
        }
        repository.deleteAll(notifications);
        return true;
    }

    @Override
    public List<NotificationDTOResponse> listAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public int count() {
        return (int) repository.count();
    }

    private Reader searchReaders(ReaderDTORequest request) {
        return readerRepository.findByUsername(request.getUsername()).orElse(null);
    }
}
