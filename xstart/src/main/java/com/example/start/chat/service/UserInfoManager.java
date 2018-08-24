package com.example.start.chat.service;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.alibaba.fastjson.JSON;

import com.example.start.chat.entity.IMMessage;
import com.example.start.chat.entity.UserInfo;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

public class UserInfoManager {
	private static ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock(true);

    private static ConcurrentMap<Channel, UserInfo> userInfos = new ConcurrentHashMap<>();
    /**
     * 登录注册 channel
     *
     *  
     */
    public static void addChannel(Channel channel,IMMessage msg) {
        String remoteAddr = channel.remoteAddress().toString().replaceFirst("/","");
        UserInfo user = new UserInfo();
        user.setId(msg.getSendId());
        user.setName(msg.getSendName());
        user.setAddr(remoteAddr);
        user.setChannel(channel);
        userInfos.put(channel, user);
    }

    /**
     * 发送系统消息
     * @param msg
     */
    public static void systemChatMsg(Channel incoming, String msg) {
        try {
            rwLock.readLock().lock();
            Set<Channel> keySet = userInfos.keySet();
            for (Channel channel : keySet) {
                if(incoming != channel){
                    UserInfo user = userInfos.get(channel);
                    IMMessage imMsg = new IMMessage(0, msg);
                    user.getChannel().writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(imMsg)));
                }
            }
        } finally {
            rwLock.readLock().unlock();
        }
    }
    /**
     * 普通消息
     *
     * @param msg
     */
    public static void broadcastMess(IMMessage msg) {
       if(msg.getType() == 1){// 登录
    		 try {
                 rwLock.readLock().lock();
                 Set<Channel> keySet = userInfos.keySet();
                 for (Channel channel : keySet) {
                 	 UserInfo user = userInfos.get(channel);
                 	 if (user.getId() == msg.getSendId()) {continue;}
                     user.getChannel().writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(msg)));
                 }
             } finally {
                 rwLock.readLock().unlock();
             }
    	}else if(msg.getType() == 2){
    		if(msg.getFriendId()==null || msg.getMsg() == null && msg.getMsg().trim().length() == 0){
    			return;
    		}
    		try {
                rwLock.readLock().lock();
                Set<Channel> keySet = userInfos.keySet();
                for (Channel channel : keySet) {
                	UserInfo user = userInfos.get(channel);
                    if (user.getId().equals(msg.getSendId())){	
                    	user.getChannel().writeAndFlush(JSON.toJSONString(msg));
                    }else if(user.getId().equals(msg.getFriendId())){
                    	msg.setFriendId(user.getId());
                        msg.setFriendName(user.getName());
                        user.getChannel().writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(msg)));
                    }
                }
            } finally {
                rwLock.readLock().unlock();
            }
    	}else if(msg.getType() == 3){/** 群聊 **/
    		if(msg.getMsg() == null && msg.getMsg().trim().length() == 0){
    			return;
    		}
    		try {
                rwLock.readLock().lock();
                Set<Channel> keySet = userInfos.keySet();
                for (Channel channel : keySet) {
                	UserInfo user = userInfos.get(channel);
                	msg.setFriendId(user.getId());
                    msg.setFriendName(user.getName());
                    user.getChannel().writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(msg)));
                }
            } finally {
                rwLock.readLock().unlock();
            }
    	}else if(msg.getType() == 4){// 下线
    		try {
                rwLock.readLock().lock();
                userInfos.remove(msg.getSendId());
            } finally {
                rwLock.readLock().unlock();
            }
    	}
    }

	public static void removeChannel(Channel channel) {
		userInfos.remove(channel);
	}

	public static UserInfo getImMsg(Channel channel) {
		return userInfos.get(channel);
	}
}
