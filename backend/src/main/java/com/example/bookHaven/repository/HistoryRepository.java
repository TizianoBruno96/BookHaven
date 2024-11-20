package com.example.bookHaven.repository;

import com.example.bookHaven.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends JpaRepository<History, String> { }
