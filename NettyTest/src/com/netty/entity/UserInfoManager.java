package com.netty.entity;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
* @author Eden
* @version 创建时间：2018年11月16日 下午3:27:51
* 类说明
*/
public class UserInfoManager {

	private static ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock(true);

    private static ConcurrentMap<Channel, UserInfo> userInfos = new ConcurrentHashMap<Channel, UserInfo>();
    /**
     * 登录注册 channel
     */
    public static void addChannel(Channel channel,String uid) {
        String remoteAddr = channel.remoteAddress().toString();
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(uid);
        userInfo.setAddr(remoteAddr);
        userInfo.setChannel(channel);
        userInfos.put(channel, userInfo);
    }

    /**
     * 普通消息
     */
    public static void broadcastMess(String uid,String message,String sender) {
        if (message!=null && message!="") {
            try {
                rwLock.readLock().lock();
                Set<Channel> keySet = userInfos.keySet();
                for (Channel ch : keySet) {
                    UserInfo userInfo = userInfos.get(ch);
                    if (!userInfo.getUserId().equals(uid) ) continue;
                    String backmessage=sender+","+message;
                    ch.writeAndFlush(new TextWebSocketFrame(backmessage));
                }
            } finally {
                rwLock.readLock().unlock();
            }
        }
    }
    
    /**
     * 获取用户信息
     * @param channel
     * @return
     */
    public static UserInfo getUserInfo(Channel channel){
    	UserInfo userInfo  = null;
    	try {
            rwLock.readLock().lock();
            Set<Channel> keySet = userInfos.keySet();
            for (Channel ch : keySet) {
            	if(channel==ch){
            		userInfo = userInfos.get(ch);
            		break;
            	}
            }
        } finally {
            rwLock.readLock().unlock();
        }
    	return userInfo;
    }
}
