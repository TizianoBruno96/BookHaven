package com.example.bookHaven.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;

@Entity
@Data
@Table(name = "notification")
public class Notification {

    @Id
    @Column(name = "notification_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotNull
    @Column(name = "message")
    private String message;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "is_read")
    private Boolean isRead;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "reader_id")
    private Reader reader;
}
