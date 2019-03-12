package com.cloud.common.utils;

import com.cloud.common.config.SocketHandler;
import com.cloud.common.vo.ShiroUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;

import java.util.Map;

/**
 * 消息推送管理消息管理
 *
 * @author Pan jianneng
 * @time 2018/10/21 20:47
 */
@Component
public class WebSocketSentMsgManager {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketSentMsgManager.class);

    @Autowired
    private SocketHandler socketHandler;

    public Object callback(Map<String,Object> msgMap) {
        try{
            ShiroUser user = UserUtils.getInstance().getCurrentlyUserInfo();
            logger.info("发送消息到前端{}.【{}】",user.getLoginName(),msgMap);
            socketHandler.sendMessageToUser(user.getId().toString(), new TextMessage(CommonUtils.getJsonStrByObject(msgMap)));
        }catch (Exception e){
            logger.error("消息发送异常："+e.getMessage());
        }
        return "";
    }

    /**
     * 发送消息给用户
     * @author Pan Jianneng
     * @date 2018/12/10 3:21 PM
     * @params [msgMap, user]
     * @return java.lang.Object
     */
    public Object sendMsgToUser(Map<String,Object> msgMap,ShiroUser user) {
        try{
            logger.info("发送消息到前端{}.【{}】",user.getLoginName(),msgMap);
            socketHandler.sendMessageToUser(user.getId().toString(), new TextMessage(CommonUtils.getJsonStrByObject(msgMap)));
        }catch (Exception e){
            logger.error("消息发送异常："+e.getMessage());
        }
        return "";
    }
}
