package com.example.serverstatus.controller;

import org.springframework.web.bind.annotation.*;

import com.example.serverstatus.model.ServerCheck;
import com.example.serverstatus.servise.ServerCheckService;
import com.example.serverstatus.repository.ServerCheckRepository;

import java.util.List;

@RestController
@RequestMapping("/checks")
public class ServerCheckController {
    private final ServerCheckRepository repository;
    private final ServerCheckService service;

    public ServerCheckController(ServerCheckRepository repository, ServerCheckService service) {
        this.repository = repository;
        this.service = service;
    }

    // Проверка статуса сервера по URL
    @GetMapping("/status")
    public ServerCheck checkStatus(@RequestParam("url") String url) {
        ServerCheck check = service.checkStatus(url);
        repository.save(check);
        return check;
    }

    // CRUD: получить все проверки
    @GetMapping
    public List<ServerCheck> all() {
        return repository.findAll();
    }

    // CRUD: получить одну проверку
    @GetMapping("/{id}")
    public ServerCheck one(@PathVariable Long id) {
        return repository.findById(id).orElseThrow();
    }

    // CRUD: удалить
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
