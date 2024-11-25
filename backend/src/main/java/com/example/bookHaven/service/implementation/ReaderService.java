package com.example.bookHaven.service.implementation;

import com.example.bookHaven.entity.Reader;
import com.example.bookHaven.entity.dto.request.ReaderDTORequest;
import com.example.bookHaven.entity.dto.response.ReaderDTOResponse;
import com.example.bookHaven.repository.ReaderRepository;
import com.example.bookHaven.service.IReaderService;
import com.example.bookHaven.service.mappers.ReaderMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ReaderService implements IReaderService {

    @Autowired
    private ReaderRepository repository;
    @Autowired
    private ReaderMapper mapper;

    @Override
    @Transactional
    public ReaderDTOResponse create(ReaderDTORequest request) {
        if (existByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Reader already exists. Please use update instead.");
        }

        Reader reader = mapper.toEntity(request);
        return mapper.toResponse(repository.save(reader));
    }

    @Override
    @Transactional
    public ReaderDTOResponse update(ReaderDTORequest request) {
        if (!existByUsername(request.getUsername())) {
            throw new NoSuchElementException("Reader not found. Please use save instead.");
        }

        Reader reader = mapper.toEntity(request);
        return mapper.toResponse(repository.save(reader));
    }

    @Override
    public ReaderDTOResponse findById(String id) {
        Optional<Reader> reader = repository.findById(id);
        return reader.map(mapper::toResponse).orElse(null);
    }

    @Override
    public ReaderDTOResponse findByUsername(String username) {
        Optional<Reader> reader = repository.findByUsername(username);
        return reader.map(mapper::toResponse).orElse(null);
    }

    @Override
    public ReaderDTOResponse findByEmail(String email) {
        Optional<Reader> reader = repository.findByEmail(email);
        return reader.map(mapper::toResponse).orElse(null);
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
        return !repository.existsByUsername(username);
    }

    @Override
    @Transactional
    public boolean deleteByEmail(String email) {
        repository.deleteByEmail(email);
        return !repository.existsByEmail(email);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public List<ReaderDTOResponse> listAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .toList();
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

        if (reader.isEmpty()) {
            throw new NoSuchElementException("Reader with id " + readerId + " not found.");
        }
        if (friend.isEmpty()) {
            throw new NoSuchElementException("Reader with id " + friendId + " not found.");
        }

        Reader readerEntity = reader.get();
        Reader friendEntity = friend.get();

        if (!readerEntity.getFriends().contains(friendEntity)) {
            readerEntity.getFriends().add(friendEntity);
        }

        repository.save(readerEntity);
    }
}
