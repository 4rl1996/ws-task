package com.gmail._4rl1996.wstesttask.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.client.WebSocketConnectionManager;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReconnectionService {

    private final WebSocketConnectionManager manager;

    @Scheduled(initialDelay = 10000, fixedRate = 20000)
    public void ke() {
        final boolean connected = manager.isConnected();
        log.info("WebSocket connected? [{}]", connected);
       if (!manager.isConnected()) {
           log.info("Try to restart connection");
           manager.start();
       }
    }
}
