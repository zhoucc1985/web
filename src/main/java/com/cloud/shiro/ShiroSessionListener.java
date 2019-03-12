package com.cloud.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 会话监听
 *
 * @author Pan jianneng
 * @time 2018/10/21 9:56
 */
public class ShiroSessionListener implements SessionListener {

    private static final Logger logger = LoggerFactory.getLogger(ShiroSessionListener.class);

    @Override
    public void onStart(Session session) {
        // 会话创建时触发
        logger.info("ShiroSessionListener session {} 被创建", session.getId());
    }

    @Override
    public void onStop(Session session) {
        // 会话被停止时触发
        logger.info("ShiroSessionListener session {} 被销毁", session.getId());
        //会话被销毁也将添加到过期缓存中
        LoginSessionCache.addSession(session.getId(),BasicControlFilterTool.SESSION_EXPIRATION);
    }

    @Override
    public void onExpiration(Session session) {
        //会话过期时触发
        logger.info("ShiroSessionListener session {} 过期", session.getId());
        //会话过期将添加到过期缓存中
        LoginSessionCache.addSession(session.getId(),BasicControlFilterTool.SESSION_EXPIRATION);
    }
}