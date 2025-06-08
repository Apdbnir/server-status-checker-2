package com.example.serverstatus.controller;

import com.example.serverstatus.model.ServerCheck;
import com.example.serverstatus.service.ServerCheckService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Collections;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ServerCheckControllerTest {
    @Mock
    private ServerCheckService service;

    @InjectMocks
    private ServerCheckController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAll() {
        when(service.findAll()).thenReturn(Collections.emptyList());
        assertTrue(controller.all().isEmpty());
    }

    @Test
    void testOne() {
        ServerCheck check = new ServerCheck();
        check.setId(1L);
        when(service.findById(1L)).thenReturn(Optional.of(check));
        assertEquals(1L, controller.one(1L).getId());
    }
}
