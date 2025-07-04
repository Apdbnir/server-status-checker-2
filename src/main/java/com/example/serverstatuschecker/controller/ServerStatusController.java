package com.example.serverstatuschecker.controller;

import com.example.serverstatuschecker.model.ServerStatus;
import com.example.serverstatuschecker.service.ServerStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/server-status")
@RequiredArgsConstructor
public class ServerStatusController {

    private final ServerStatusService serverStatusService;

    @GetMapping("/check")
    public ResponseEntity<ServerStatus> checkServerStatus(@RequestParam String url) {
        ServerStatus request = new ServerStatus();
        request.setUrl(url);
        ServerStatus response = serverStatusService.checkServerStatus(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ServerStatus> createServerStatus(@RequestBody ServerStatus serverStatus) {
        return ResponseEntity.ok(serverStatusService.createServerStatus(serverStatus));
    }

    @GetMapping
    public ResponseEntity<List<ServerStatus>> getAllServerStatuses() {
        return ResponseEntity.ok(serverStatusService.getAllServerStatuses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServerStatus> getServerStatusById(@PathVariable Long id) {
        return ResponseEntity.ok(serverStatusService.getServerStatusById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServerStatus> updateServerStatus(@PathVariable Long id, @RequestBody ServerStatus serverStatus) {
        return ResponseEntity.ok(serverStatusService.updateServerStatus(id, serverStatus));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServerStatus(@PathVariable Long id) {
        serverStatusService.deleteServerStatus(id);
        return ResponseEntity.noContent().build();
    }
}