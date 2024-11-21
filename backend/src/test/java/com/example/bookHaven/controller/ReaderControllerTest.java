package com.example.bookHaven.controller;

import com.example.bookHaven.configuration.TestSecurityConfig;
import com.example.bookHaven.service.implementation.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ReaderController.class)
@Import(TestSecurityConfig.class)
public class ReaderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReaderService readerService;
}
