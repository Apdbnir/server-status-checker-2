package com.example.serverstatus.service;

import com.example.serverstatus.model.ServerGroup;
import com.example.serverstatus.repository.ServerGroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServerGroupService {
    private final ServerGroupRepository serverGroupRepository;

    public ServerGroupService(ServerGroupRepository serverGroupRepository) {
        this.serverGroupRepository = serverGroupRepository;
    }

    public List<ServerGroup> findAll() {
        return serverGroupRepository.findAll();
    }

    public Optional<ServerGroup> findById(Long id) {
        return serverGroupRepository.findById(id);
    }

    public ServerGroup save(ServerGroup group) {
        return serverGroupRepository.save(group);
    }

    public void deleteById(Long id) {
        serverGroupRepository.deleteById(id);
    }
}
