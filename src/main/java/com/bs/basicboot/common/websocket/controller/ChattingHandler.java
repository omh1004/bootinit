package com.bs.basicboot.common.websocket.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;
import java.util.Map;

//스프링에서는 TextWebSocketHandler 상속받은 클래스가 핸들러 역할을 한다.
// 메소드를 상속받으면 상황마다 알아서 기능들이 실행된다.
@Component
@Slf4j
public class ChattingHandler extends TextWebSocketHandler {


    private Map<String, WebSocketSession> clients = new HashMap<>();


    public ChattingHandler() {
        super();
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
    }
}
