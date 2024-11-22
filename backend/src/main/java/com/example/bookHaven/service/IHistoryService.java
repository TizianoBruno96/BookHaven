package com.example.bookHaven.service;


import com.example.bookHaven.entity.dto.request.BookDTORequest;
import com.example.bookHaven.entity.dto.request.HistoryDTORequest;
import com.example.bookHaven.entity.dto.request.ReaderDTORequest;
import com.example.bookHaven.entity.dto.response.HistoryDTOResponse;

import java.util.List;

public interface IHistoryService {

    HistoryDTOResponse create(HistoryDTORequest request);

    HistoryDTOResponse update(HistoryDTORequest request);

    HistoryDTOResponse findById(String id);

    List<HistoryDTOResponse> findByBook(String bookId);

    List<HistoryDTOResponse> findByBook(BookDTORequest bookRequest);

    List<HistoryDTOResponse> findByReader(String readerId);

    List<HistoryDTOResponse> findByReader(ReaderDTORequest readerRequest);

    boolean existsById(String id);

    boolean existsByBook(String bookId);

    boolean existsByBook(BookDTORequest bookRequest);

    boolean existsByReader(String readerId);

    boolean existsByReader(ReaderDTORequest readerRequest);

    boolean deleteById(String id);

    boolean deleteByBook(String bookId);

    boolean deleteByBook(BookDTORequest bookRequest);

    boolean deleteByReader(String readerId);

    boolean deleteByReader(ReaderDTORequest readerRequest);

    List<HistoryDTOResponse> listAll();

    int count();
}
