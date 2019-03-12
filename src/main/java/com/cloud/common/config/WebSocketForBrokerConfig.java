package com.cloud.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

/**
 * websocket
 * @EnableWebSocketMessageBroker 开启使用STOMP协议来传输基于代理的消息，Broker是代理
 * @author Pan jianneng
 * @time 2018/10/21 19:16
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketForBrokerConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * websocket访问地址
     */
    public static String WEBSOCKETPATH = "/my-websocket";
    /**
     * 消息代理路径
     */
    public static String WEBSOCKETBROADCASTPATH = "/topic";
    /**
     * 消息点对点推送
     */
    public static final String P2PPUSHBASEPATH = "/user";
    /**
     * 点对点消息推送地址后缀,最后的地址为/user/用户识别码/callback
     */
    public static final String P2PPUSHPATH = "/callback";

    /**
     * 配置消息代理，由于我们是实现推送功能，这里的消息代理是/topic
     * @param config
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        //服务端发送消息给客户端的域,多个用逗号隔开
        config.enableSimpleBroker(WEBSOCKETBROADCASTPATH,P2PPUSHBASEPATH);
        //定义一对一推送的时候前缀
        config.setUserDestinationPrefix(P2PPUSHBASEPATH);
    }

    /**
     * 注册STOMP协议的节点，并指定映射的URL
     *  注册websocket，客户端用ws://host:port/项目名/my-websocket 访问
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        //表示支持以SockJS方式连接服务器
        registry.addEndpoint(WEBSOCKETPATH).addInterceptors(new WebSocketInterceptor()).setAllowedOrigins("*").withSockJS();
    }

}