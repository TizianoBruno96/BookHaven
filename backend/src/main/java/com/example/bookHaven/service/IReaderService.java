package com.example.bookHaven.service;

import com.example.bookHaven.entity.Reader;

import java.util.List;

public interface IReaderService {

    Reader create(Reader reader);
    Reader update(Reader reader);
    Reader findById(String id);
    Reader findByUsername(String username);
    Reader findByEmail(String email);
    boolean existById(String id);
    boolean existByUsername(String username);
    boolean existByEmail(String email);
    boolean deleteById(String id);
    boolean deleteByUsername(String id);
    boolean deleteByEmail(String id);
    long count();
    List<Reader> listAll();
    boolean checkUserPassword(String username, String password);

    //create new record on the friendship table on db with the two ids
    void addFriend(String readerId, String friendId);
}
