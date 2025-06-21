package com.example.serverstatuschecker.repository;

import com.example.serverstatuschecker.model.ServerStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerStatusRepository extends JpaRepository<ServerStatus, Long> {
}