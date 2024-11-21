package com.example.bookHaven.controller;

import com.example.bookHaven.configuration.TestSecurityConfig;
import com.example.bookHaven.service.implementation.ReaderService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReaderController.class)
@Import(TestSecurityConfig.class)
public class ReaderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReaderService readerService;

    @Test
    public void addFriend_shouldReturnBadRequest_whenServiceThrowsException() throws Exception {
        // Simula l'eccezione lanciata dal servizio
        Mockito.doThrow(new IllegalArgumentException("Reader or friend not found"))
                .when(readerService).addFriend("1", "2");

        // Esegui il test
        mockMvc.perform(post("/reader/addFriend")
                        .param("readerId", "1")
                        .param("friendId", "2"))
                .andExpect(status().isBadRequest()) // Controlla che lo stato sia 400
                .andExpect(content().string("Reader or friend not found")); // Controlla il messaggio di errore
    }
}
