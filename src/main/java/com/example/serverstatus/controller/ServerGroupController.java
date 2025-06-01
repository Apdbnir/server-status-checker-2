package com.example.serverstatus.controller;

import org.springframework.web.bind.annotation.*;

import com.example.serverstatus.repository.ServerGroupRepository;
import com.example.serverstatus.model.ServerGroup;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class ServerGroupController {
    private final ServerGroupRepository groupRepo;

    public ServerGroupController(ServerGroupRepository groupRepo) {
        this.groupRepo = groupRepo;
    }

    @GetMapping
    public List<ServerGroup> all() {
        return groupRepo.findAll();
    }

    @PostMapping
    public ServerGroup create(@RequestBody ServerGroup group) {
        return groupRepo.save(group);
    }

    @GetMapping("/{id}")
    public ServerGroup one(@PathVariable Long id) {
        return groupRepo.findById(id).orElseThrow();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        groupRepo.deleteById(id);
    }
}
