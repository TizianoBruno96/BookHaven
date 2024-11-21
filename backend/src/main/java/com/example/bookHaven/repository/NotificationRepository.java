package com.example.bookHaven.repository;

import com.example.bookHaven.entity.Notification;
import com.example.bookHaven.entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, String> {
    List<Notification> findByReaderId(String readerId);
    List<Notification> findByReader(Reader reader);
    boolean existsByReaderId(String readerId);
    boolean existsByReader(Reader reader);
}
