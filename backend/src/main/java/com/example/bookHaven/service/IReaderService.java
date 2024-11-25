package com.example.bookHaven.service;

import com.example.bookHaven.entity.dto.request.ReaderDTORequest;
import com.example.bookHaven.entity.dto.response.ReaderDTOResponse;

import java.util.List;

public interface IReaderService {

    ReaderDTOResponse create(ReaderDTORequest request);

    ReaderDTOResponse update(ReaderDTORequest request);

    ReaderDTOResponse findById(String id);

    ReaderDTOResponse findByUsername(String username);

    ReaderDTOResponse findByEmail(String email);

    boolean existsById(String id);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    boolean deleteById(String id);

    boolean deleteByUsername(String id);

    boolean deleteByEmail(String id);

    long count();

    List<ReaderDTOResponse> listAll();

    boolean checkUserPassword(String username, String password);

    //create new record on the friendship table on db with the two ids
    void addFriend(String readerId, String friendId);
}
