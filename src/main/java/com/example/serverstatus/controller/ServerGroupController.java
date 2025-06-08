package com.example.serverstatus.controller;

import org.springframework.web.bind.annotation.*;
import com.example.serverstatus.model.ServerGroup;
import com.example.serverstatus.service.ServerGroupService;
import java.util.List;

@RestController
@RequestMapping("/groups")
public class ServerGroupController {
    private final ServerGroupService groupService;

    public ServerGroupController(ServerGroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public List<ServerGroup> all() {
        return groupService.findAll();
    }

    @PostMapping
    public ServerGroup create(@RequestBody ServerGroup group) {
        return groupService.save(group);
    }

    @GetMapping("/{id}")
    public ServerGroup one(@PathVariable Long id) {
        return groupService.findById(id).orElseThrow(() -> new RuntimeException("ServerGroup not found with id " + id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        groupService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ServerGroup update(@PathVariable Long id, @RequestBody ServerGroup groupDetails) {
        ServerGroup existingGroup = groupService.findById(id)
                .orElseThrow(() -> new RuntimeException("ServerGroup not found with id " + id));
        existingGroup.setName(groupDetails.getName());
        existingGroup.setChecks(groupDetails.getChecks());
        return groupService.save(existingGroup);
    }
}
