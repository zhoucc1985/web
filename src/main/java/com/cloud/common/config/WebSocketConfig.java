package com.cloud.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.socket.config.annotation.*;

/**
 * 实现接口来配置Websocket请求的路径和拦截器。
 * @author Pan jianneng
 * @time 2018/10/21 19:16
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig  implements WebSocketConfigurer{

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {

        //handler是webSocket的核心，配置入口
        registry.addHandler(new SocketHandler(), "/socketHandler/{userId}").
                setAllowedOrigins("*").
                addInterceptors(new WebSocketInterceptor());

    }
}