package ru.finplatforms.interview.textfinder.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import ru.finplatforms.interview.textfinder.services.FileService;

class FileControllerTest {

    @Mock
    FileService fileService;

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

        //when
        when(fileService.pathIsNotDirectory("invalid directory")).thenReturn(true);

        //then
        mockMvc.perform(post("/directory")
                .param("pathName", "invalid directory"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("error", "directory does not exist, try again"))
                .andExpect(view().name("newDirectoryView"));
    }
}