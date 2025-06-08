package com.example.serverstatus.controller;

import org.springframework.web.bind.annotation.*;
import com.example.serverstatus.model.ServerCheck;
import com.example.serverstatus.service.ServerCheckService;
import java.util.List;

@RestController
@RequestMapping("/checks")
public class ServerCheckController {
    private final ServerCheckService service;

    public ServerCheckController(ServerCheckService service) {
        this.service = service;
    }

    @GetMapping("/status")
    public ServerCheck checkStatus(@RequestParam("url") String url) {
        return service.checkStatus(url);
    }

    @GetMapping
    public List<ServerCheck> all() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ServerCheck one(@PathVariable Long id) {
        return service.findById(id)
                      .orElseThrow(() -> new RuntimeException("ServerCheck not found with id " + id));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PutMapping("/{id}")
    public ServerCheck updateCheck(@PathVariable Long id, @RequestBody ServerCheck checkDetails) {
        ServerCheck existingCheck = service.findById(id)
                                         .orElseThrow(() -> new RuntimeException("ServerCheck not found with id " + id));
        existingCheck.setUrl(checkDetails.getUrl());
        existingCheck.setStatus(checkDetails.getStatus());
        existingCheck.setCode(checkDetails.getCode());
        existingCheck.setGroup(checkDetails.getGroup());
        return service.save(existingCheck);
    }

    @PostMapping
    public ServerCheck createCheck(@RequestBody ServerCheck check) {
        return service.save(check);
    }

}
