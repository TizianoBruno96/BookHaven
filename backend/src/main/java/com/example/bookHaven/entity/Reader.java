package com.example.bookHaven.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "reader")
public class Reader {

    @Id
    @Column(name = "reader_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @NotNull
    @Column(unique = true, name = "email")
    private String email;
    @NotNull
    @Column(unique = true, name = "username")
    private String username;
    @NotNull
    @Column(name = "password")
    private String password;
    @Column(name = "age")
    private Integer age;
    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "gender")
    private Gender gender;
    @Column(name = "bio", length = 500)
    private String bio;
    @OneToMany(mappedBy = "reader", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notification> notifications = new ArrayList<>();
    @OneToMany(mappedBy = "reader", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<History> histories = new ArrayList<>();
    @OneToMany(mappedBy = "reader", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();
    @ManyToMany
    @JoinTable(
            name = "friendship",
            joinColumns = @JoinColumn(name = "reader_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    private List<Reader> friends = new ArrayList<>();

    public enum Gender {
        MALE,
        FEMALE,
        OTHER
    }
}
