package com.example.bookHaven.service;

import com.example.bookHaven.entity.dto.request.NotificationDTORequest;
import com.example.bookHaven.entity.dto.request.ReaderDTORequest;
import com.example.bookHaven.entity.dto.response.NotificationDTOResponse;

import java.util.List;

public interface INotificationService {
    NotificationDTOResponse create(NotificationDTORequest request);

    NotificationDTOResponse update(NotificationDTORequest request);

    NotificationDTOResponse findById(String id);

    List<NotificationDTOResponse> findByReader(String readerId);

    List<NotificationDTOResponse> findByReader(ReaderDTORequest readerRequest);

    boolean existsById(String id);

    boolean existsByReader(String readerId);

    boolean existsByReader(ReaderDTORequest readerRequest);

    boolean deleteById(String id);

    boolean deleteByReader(String readerId);

    boolean deleteByReader(ReaderDTORequest readerRequest);

    List<NotificationDTOResponse> listAll();

    int count();
}
