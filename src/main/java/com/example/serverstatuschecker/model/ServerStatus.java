package com.example.serverstatuschecker.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "server_statuses")
@Data
public class ServerStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "url")
    private String url;

    @Column(name = "is_available")
    private boolean isAvailable;

    @Column(name = "message")
    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "server_id")
    private Server server;

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}