package com.example.serverstatus.service;

import com.example.serverstatus.model.ServerGroup;
import com.example.serverstatus.repository.ServerGroupRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Collections;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ServerGroupServiceTest {
    @Mock
    private ServerGroupRepository repository;

    @InjectMocks
    private ServerGroupService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        when(repository.findAll()).thenReturn(Collections.emptyList());
        assertTrue(service.findAll().isEmpty());
    }

    @Test
    void testFindById() {
        ServerGroup group = new ServerGroup();
        group.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(group));
        assertTrue(service.findById(1L).isPresent());
    }
}
