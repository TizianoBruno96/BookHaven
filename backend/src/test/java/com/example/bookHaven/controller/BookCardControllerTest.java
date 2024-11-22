package com.example.bookHaven.controller;

import com.example.bookHaven.entity.dto.request.BookCardDTORequest;
import com.example.bookHaven.entity.dto.response.BookCardDTOResponse;
import com.example.bookHaven.service.IBookCardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BookCardControllerTest {

    @Mock
    private IBookCardService bookCardService;

    @InjectMocks
    private BookCardController bookCardController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bookCardController).build();
    }

    @Test
    void testCreateBookCard() throws Exception {
        BookCardDTORequest request = new BookCardDTORequest();
        BookCardDTOResponse response = new BookCardDTOResponse();
        response.setId("1");

        when(bookCardService.create(any(BookCardDTORequest.class))).thenReturn(response);

        mockMvc.perform(post("/bookCard/create")
                        .contentType("application/json")
                        .content("{\"id\": \"1\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"));

        verify(bookCardService, times(1)).create(any(BookCardDTORequest.class));
    }

    @Test
    void testUpdateBookCard() throws Exception {
        BookCardDTORequest request = new BookCardDTORequest();
        BookCardDTOResponse response = new BookCardDTOResponse();
        response.setId("1");

        when(bookCardService.update(any(BookCardDTORequest.class))).thenReturn(response);

        mockMvc.perform(put("/bookCard/update")
                        .contentType("application/json")
                        .content("{\"id\": \"1\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"));

        verify(bookCardService, times(1)).update(any(BookCardDTORequest.class));
    }

    @Test
    void testFindById() throws Exception {
        BookCardDTOResponse response = new BookCardDTOResponse();
        response.setId("1");

        when(bookCardService.findById("1")).thenReturn(response);

        mockMvc.perform(get("/bookCard/findById/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"));

        verify(bookCardService, times(1)).findById("1");
    }

    @Test
    void testFindByBook() throws Exception {
        BookCardDTOResponse response = new BookCardDTOResponse();
        response.setId("1");

        when(bookCardService.findByBook("1")).thenReturn(Collections.singletonList(response));

        mockMvc.perform(get("/bookCard/findByBook/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"));

        verify(bookCardService, times(1)).findByBook("1");
    }

    @Test
    void testListAllBookCards() throws Exception {
        BookCardDTOResponse response = new BookCardDTOResponse();
        response.setId("1");

        when(bookCardService.listAll()).thenReturn(Collections.singletonList(response));

        mockMvc.perform(get("/bookCard/listAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"));

        verify(bookCardService, times(1)).listAll();
    }

    @Test
    void testCountBookCards() throws Exception {
        when(bookCardService.count()).thenReturn(10);

        mockMvc.perform(get("/bookCard/count"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(10));

        verify(bookCardService, times(1)).count();
    }
}
