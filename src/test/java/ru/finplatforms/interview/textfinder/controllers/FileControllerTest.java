package ru.finplatforms.interview.textfinder.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class FileControllerTest {

    @InjectMocks
    FileController underTest;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
    }

    @Test
    void when_providing_invalid_directory_then_receiving_error_message() throws Exception {

        mockMvc.perform(post("/directory")
                .param("pathName", "invalid directory"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("error", "directory does not exist, try again"))
                .andExpect(view().name("newDirectoryView"));
    }
}