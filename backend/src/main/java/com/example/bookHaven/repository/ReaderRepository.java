package com.example.bookHaven.repository;

import com.example.bookHaven.entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, String> {
    Optional<Reader> findByUsername(String username);
    Optional<Reader> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByUsernameAndPassword(String username, String password);
    void deleteByUsername(String username);
    void deleteByEmail(String email);
}
