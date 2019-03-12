package com.cloud.common.config;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 类描述
 *
 * @author: Pan Jianneng
 * @create: 2018/12/07 10:32
 */
@Service
public class SocketHandler implements WebSocketHandler {
    /**
     * 已经打开连接的用户
     */
    private static final Map<String,Map> OPEN_SOCKET_USERS;

    static {
        OPEN_SOCKET_USERS = new HashMap<>();
    }

    /**
     * 打开socket连接
     * @author Pan Jianneng
     * @date 2018/12/7 10:40 AM
     * @param
     * @return
    */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String userId = session.getUri().toString().split("userId=")[1];
        if (userId != null) {
            Map map=new HashMap(0);
            if(OPEN_SOCKET_USERS.get(userId)!=null&&OPEN_SOCKET_USERS.get(userId).size()>0){
                map=OPEN_SOCKET_USERS.get(userId);
            }
            map.put(session.getId(),session);
            OPEN_SOCKET_USERS.put(userId, map);
            //session.sendMessage(new TextMessage("成功建立socket连接"));
        }
    }

    /**
     * 接受客户端消息
     * @author Pan Jianneng
     * @date 2018/12/9 6:07 PM
     * @params [webSocketSession, webSocketMessage]
     * @return void
     */
    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) {

    }

    /**
     * 发送信息给指定用户
     * @param clientId
     * @param message
     * @return
     */
    public boolean sendMessageToUser(String clientId, TextMessage message) {
        if (OPEN_SOCKET_USERS.get(clientId) == null){
            return false;
        }
        Iterator it=OPEN_SOCKET_USERS.get(clientId).entrySet().iterator();
        while (it.hasNext()){
            Map.Entry entry=(Map.Entry)it.next();
            Object key=entry.getKey();
            Object value=entry.getValue();
            WebSocketSession session = (WebSocketSession)value;
            if (!session.isOpen()) {
                return false;
            }
            try {
                session.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }

    /**
     * 连接出错时，回调的方法。
     * 主要逻辑是：一旦有连接出错的Socket,就从OPEN_SOCKET_USERS里进行移除，有提供该Socket的参数，可直接获取ID，进行移除。
     * 这个在客户端没有正常关闭连接时，会进来，所以在开发客户端时，记得关闭连接
     * @author Pan Jianneng
     * @date 2018/12/7 11:45 AM
     * @param
     * @return
    */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        if(OPEN_SOCKET_USERS.get(getClientId(session)).size()>0){
            OPEN_SOCKET_USERS.get(getClientId(session)).remove(session.getId());
        }else{
            OPEN_SOCKET_USERS.remove(getClientId(session));
        }
    }

    /**
     * 连接关闭时，回调的方法。
     * 主要逻辑：一旦客户端/服务器主动关闭连接时，将个socket从OPEN_SOCKET_USERS里移除，有提供该Socket的参数，可直接获取ID，进行移除。
     * @author Pan Jianneng
     * @date 2018/12/7 11:45 AM
     * @param
     * @return
    */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        if(OPEN_SOCKET_USERS.get(getClientId(session)).size()>0){
            OPEN_SOCKET_USERS.get(getClientId(session)).remove(session.getId());
        }else{
            OPEN_SOCKET_USERS.remove(getClientId(session));
        }
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 获取用户标识
     * @param session
     * @return
     */
    private String getClientId(WebSocketSession session) {
        try {
            String str=(String) session.getAttributes().get("WEBSOCKET_USERID");
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
