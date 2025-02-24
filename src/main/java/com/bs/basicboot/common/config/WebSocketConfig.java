package com.bs.basicboot.common.config;


import com.bs.basicboot.common.websocket.controller.ChattingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
//09:25
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private ChattingHandler handler;



    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        //핸들러를 등록할때는 두가지가 필요하다. 1. 주소, 2.요청처리 객체 (Chatting Server)
//        registry.addHandler(핸들러, 매핑주소 );
        registry.addHandler(handler, "/chatting" );
    }
}
