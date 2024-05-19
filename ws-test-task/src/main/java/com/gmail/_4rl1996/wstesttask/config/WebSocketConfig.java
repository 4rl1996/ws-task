package com.gmail._4rl1996.wstesttask.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Configuration
@EnableWebSocket
public class WebSocketConfig {

    @Value("${test.task.uri}")
    private String uri;

    @Value("${test.task.topics}")
    private List<String> topics;

    @Bean
    public WebSocketHandler handler() {
        return new WsHandler(topics);
    }

    @Bean
    public WebSocketConnectionManager wsConnectionManager() throws URISyntaxException {

        WebSocketConnectionManager manager = new WebSocketConnectionManager(
                new StandardWebSocketClient(),
                new WsHandler(topics),
                new URI(uri));

        manager.setAutoStartup(true);
        return manager;
    }
}
