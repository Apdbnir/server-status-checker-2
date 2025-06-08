package com.example.serverstatus.service;

import com.example.serverstatus.model.ServerCheck;
import com.example.serverstatus.repository.ServerCheckRepository;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Optional;

@Service
public class ServerCheckService {

    private final ServerCheckRepository serverCheckRepository;

    public ServerCheckService(ServerCheckRepository serverCheckRepository) {
        this.serverCheckRepository = serverCheckRepository;
    }

    public ServerCheck checkStatus(String url) {
        ServerCheck check = new ServerCheck();
        check.setUrl(url);
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setConnectTimeout(2000);
            connection.connect();
            int code = connection.getResponseCode();
            check.setStatus((code >= 200 && code < 400) ? "available" : "unavailable");
            check.setCode(code);
        } catch (Exception e) {
            check.setStatus("unavailable");
            check.setCode(-1);
        }
        return serverCheckRepository.save(check);
    }

    public List<ServerCheck> findAll() {
        return serverCheckRepository.findAll();
    }

    public Optional<ServerCheck> findById(Long id) {
        return serverCheckRepository.findById(id);
    }

    public ServerCheck save(ServerCheck check) {
        return serverCheckRepository.save(check);
    }

    public void deleteById(Long id) {
        serverCheckRepository.deleteById(id);
    }
}
