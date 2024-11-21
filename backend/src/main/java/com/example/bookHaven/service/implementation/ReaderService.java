package com.example.bookHaven.service.implementation;

import com.example.bookHaven.entity.Reader;
import com.example.bookHaven.repository.ReaderRepository;
import com.example.bookHaven.service.IReaderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ReaderService implements IReaderService {

    @Autowired
    ReaderRepository repository;

    @Override
    @Transactional
    public Reader create(Reader reader) {
        if (existByUsername(reader.getUsername())) {
            throw new IllegalArgumentException();
        }

        return repository.save(reader);
    }

    @Override
    @Transactional
    public Reader update(Reader reader) {
        if (!existByUsername(reader.getUsername())) {
            throw new NoSuchElementException();
        }

        return repository.save(reader);
    }

    @Override
    public Reader findById(String id) {
        Optional<Reader> reader = repository.findById(id);
        return reader.orElse(null);
    }

    @Override
    public Reader findByUsername(String username) {
        Optional<Reader> reader = repository.findByUsername(username);
        return reader.orElse(null);
    }

    @Override
    public Reader findByEmail(String email) {
        Optional<Reader> reader = repository.findByEmail(email);
        return reader.orElse(null);
    }

    @Override
    public boolean existById(String id) {
        return repository.existsById(id);
    }

    @Override
    public boolean existByUsername(String username) {
        return repository.existsByUsername(username);
    }

    @Override
    public boolean existByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    @Transactional
    public boolean deleteById(String id) {
        if (!existById(id)) {
            return false;
        }
        repository.deleteById(id);
        return !existById(id);
    }


    @Override
    @Transactional
    public boolean deleteByUsername(String username) {
        repository.deleteByUsername(username);
        return !existByUsername(username);
    }

    @Override
    @Transactional
    public boolean deleteByEmail(String email) {
        repository.deleteByEmail(email);
        return !existByEmail(email);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public List<Reader> listAll() {
        return repository.findAll();
    }

    @Override
    public boolean checkUserPassword(String username, String password) {
        return repository.existsByUsernameAndPassword(username, password);
    }

    @Override
    @Transactional
    public void addFriend(String readerId, String friendId) {
        Optional<Reader> reader = repository.findById(readerId);
        Optional<Reader> friend = repository.findById(friendId);

        if (reader.isPresent() && friend.isPresent()) {
            Reader readerEntity = reader.get();
            Reader friendEntity = friend.get();

            if (!readerEntity.getFriends().contains(friendEntity)) {
                readerEntity.getFriends().add(friendEntity);
            }

            repository.save(readerEntity);
        } else {
            throw new IllegalArgumentException("Either Reader or Friend not found");
        }
    }
}
