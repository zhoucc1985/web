package com.cloud.common.config;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

/**
 * WebSocket拦截器
 *
 * @author: Pan Jianneng
 * @create: 2018/12/07 10:30
 */
public class WebSocketInterceptor implements HandshakeInterceptor {

    /**
     * 进入hander之前的拦截
     * @author Pan Jianneng
     * @date 2018/12/7 10:32 AM
     * @param
     * @return
    */
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        if (request instanceof ServletServerHttpRequest) {
            String userId = request.getURI().toString().split("userId=")[1];
            map.put("WEBSOCKET_USERID",userId);
        }
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {
    }
}