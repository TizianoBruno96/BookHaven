package com.example.bookHaven.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;

@Entity
@Data
@Table(name = "history")
public class History {

    @Id
    @Column(name = "history_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "started_read_at")
    private Instant startedReadAt;

    @Column(name = "last_read_at")
    private Instant last_read_at;

    @Column(name = "is_read")
    private Boolean isRead;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "reader_id")
    private Reader reader;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "book_id")
    private Book book;
}
