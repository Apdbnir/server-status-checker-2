package com.example.serverstatus.controller;

import com.example.serverstatus.model.ServerGroup;
import com.example.serverstatus.service.ServerGroupService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ServerGroupControllerTest {
    ServerGroupService service;
    ServerGroupController controller;

    @BeforeEach
    void setUp() {
        service = mock(ServerGroupService.class);
        controller = new ServerGroupController(service);
    }

    @Test
    void testAll() {
        when(service.findAll()).thenReturn(Collections.emptyList());
        assertTrue(controller.all().isEmpty());
    }

    @Test
    void testOne() {
        ServerGroup group = new ServerGroup();
        group.setId(1L);
        when(service.findById(1L)).thenReturn(Optional.of(group));
        assertEquals(1L, controller.one(1L).getId());
    }
}
