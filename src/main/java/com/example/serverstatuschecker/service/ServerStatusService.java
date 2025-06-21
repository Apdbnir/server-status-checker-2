package com.example.serverstatuschecker.service;

import com.example.serverstatuschecker.model.Server;
import com.example.serverstatuschecker.model.ServerStatus;
import com.example.serverstatuschecker.repository.ServerRepository;
import com.example.serverstatuschecker.repository.ServerStatusRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ServerStatusService {

    private final ServerRepository serverRepository;
    private final ServerStatusRepository serverStatusRepository;

    @Transactional
    public ServerStatus checkServerStatus(ServerStatus request) {
        ServerStatus response = new ServerStatus();
        response.setUrl(request.getUrl());

        try {
            URL serverUrl = new URL(request.getUrl());
            HttpURLConnection connection = (HttpURLConnection) serverUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int responseCode = connection.getResponseCode();
            response.setIsAvailable(responseCode >= 200 && responseCode < 300);
            response.setMessage(response.isAvailable() ? "Server is available" :
                    "Server responded with status code: " + responseCode);

            connection.disconnect();
        } catch (Exception e) {
            log.error("Error checking server status for URL: {}", request.getUrl(), e);
            response.setIsAvailable(false);
            response.setMessage("Failed to connect: " + e.getMessage());
        }
        Server server = serverRepository.findById(1L).orElseGet(() -> {
            Server newServer = new Server();
            newServer.setName("Default Server");
            return serverRepository.save(newServer);
        });
        response.setServer(server);
        return serverStatusRepository.save(response);
    }

    @Transactional
    public ServerStatus createServerStatus(ServerStatus serverStatus) {
        return serverStatusRepository.save(serverStatus);
    }

    @Transactional
    public List<ServerStatus> getAllServerStatuses() {
        return serverStatusRepository.findAll();
    }

    @Transactional
    public ServerStatus getServerStatusById(Long id) {
        return serverStatusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ServerStatus not found with id: " + id));
    }

    @Transactional
    public ServerStatus updateServerStatus(Long id, ServerStatus updatedStatus) {
        ServerStatus status = serverStatusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ServerStatus not found with id: " + id));
        status.setUrl(updatedStatus.getUrl());
        status.setIsAvailable(updatedStatus.isAvailable());
        status.setMessage(updatedStatus.getMessage());
        return serverStatusRepository.save(status);
    }

    @Transactional
    public void deleteServerStatus(Long id) {
        serverStatusRepository.deleteById(id);
    }
}