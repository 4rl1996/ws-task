package com.gmail._4rl1996.wstesttask.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.client.WebSocketConnectionManager;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TestController {

    private final WebSocketConnectionManager manager;

    /**
     * Вызов используется для тестирования переподключения
     */
    @PostMapping("/stop")
    void stopConnection() {
        manager.stop();
    }
}
