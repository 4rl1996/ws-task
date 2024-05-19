package com.gmail._4rl1996.wstesttask.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail._4rl1996.wstesttask.data.TestMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
public class WsHandler extends TextWebSocketHandler {

    final ObjectMapper objectMapper = new ObjectMapper();

    private final static String PING_OPERATION = "ping";
    private final static String SUBSCRIBE_OPERATION = "subscribe";

    private final List<String> topics;

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        log.info(message.getPayload());
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession wsSession) throws Exception {
        if (wsSession.isOpen()) {
            final TestMessage subscribeMessage = new TestMessage();
            subscribeMessage.setOp(SUBSCRIBE_OPERATION);
            subscribeMessage.setArgs(topics);
            final String subscribeMessageJson = objectMapper.writeValueAsString(subscribeMessage);
            log.info("Subscribe to topics with message: " + subscribeMessageJson);
            wsSession.sendMessage(new TextMessage(subscribeMessageJson));

            final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
            scheduledExecutorService.scheduleAtFixedRate(() -> {
                try {
                    final TestMessage pingMessage = new TestMessage();
                    pingMessage.setOp(PING_OPERATION);
                    final String pingMessageJson = objectMapper.writeValueAsString(pingMessage);
                    log.info("Ping with message: " + pingMessageJson);
                    wsSession.sendMessage(new TextMessage(pingMessageJson));
                } catch (Exception e) {
                    log.error("Some error occurred", e);
                }
            }, 20, 20, TimeUnit.SECONDS);
        }
    }
}



