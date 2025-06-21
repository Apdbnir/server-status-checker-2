package com.example.serverstatuschecker.service;

import com.example.serverstatuschecker.model.Server;
import com.example.serverstatuschecker.repository.ServerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServerService {

    private final ServerRepository serverRepository;

    @Transactional
    public Server createServer(Server server) {
        return serverRepository.save(server);
    }

    @Transactional
    public List<Server> getAllServers() {
        return serverRepository.findAll();
    }

    @Transactional
    public Server getServerById(Long id) {
        return serverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Server not found with id: " + id));
    }

    @Transactional
    public Server updateServer(Long id, Server updatedServer) {
        Server server = serverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Server not found with id: " + id));
        server.setName(updatedServer.getName());
        return serverRepository.save(server);
    }

    @Transactional
    public void deleteServer(Long id) {
        serverRepository.deleteById(id);
    }
}