package com.example.start.chat.processor;

import com.example.start.chat.protocol.IMMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;

/**
 * 存储信息
 */
public class InformationOperateMapMatching {

    public static ConcurrentMap<String, ConcurrentMap<String, InformationOperateMapMatching>> map = new ConcurrentHashMap<>();
    public static Queue<InformationOperateMapMatching> queue = new ConcurrentLinkedQueue<>();
    public static ConcurrentMap<String, String> login = new ConcurrentHashMap<>();

    private ChannelHandlerContext ctx;
    private IMMessage mage;

    private InformationOperateMapMatching(ChannelHandlerContext ctx, IMMessage mage) {
        this.ctx = ctx;
        this.mage = mage;
    }

    /**
     * 添加到队列当中等待其他用户登录后匹配
     * @param ctx
     * @param mage
     */
    public static void offer(ChannelHandlerContext ctx, IMMessage mage) {
        queue.offer(new InformationOperateMapMatching(ctx, mage));
    }

    /**
     * 添加用户信息
     * @param ctx
     * @param mage
     */
    public static void add(ChannelHandlerContext ctx, IMMessage mage) {
        InformationOperateMapMatching iom = new InformationOperateMapMatching(ctx, mage);
        ConcurrentMap<String, InformationOperateMapMatching> cmap = new ConcurrentHashMap<>();
        if (map.containsKey(mage.getChatRoom())) {
            map.get(mage.getChatRoom()).put(mage.getSender(), iom);
        } else {
            cmap.put(mage.getSender(), iom);
            map.put(mage.getChatRoom(), cmap);
        }
        login.replace(mage.getSender(), mage.getChatRoom());
    }

    /**
     * 删除用户信息
     * @param id
     * @param table
     */
    public static void delete(String id, String table) {
        ConcurrentMap<String, InformationOperateMapMatching> cmap = map.get(table);
        if (cmap.size() <= 1) {
            map.remove(table);
        } else {
            cmap.remove(id);
        }
    }

    /**
     * 给用户发送消息
     * @param mage
     * @throws Exception
     */
    public void sead(IMMessage mage) throws Exception{
        this.ctx.writeAndFlush(new TextWebSocketFrame(mage.toJson()));
    }

    public IMMessage getIMMessage() {
        return this.mage;
    }

    public ChannelHandlerContext getChannelHandlerContext() {
        return this.ctx;
    }

    public InformationOperateMapMatching setChatRoomId(String chatRoomId) {
        this.mage.setChatRoom(chatRoomId);
        return this;
    }
}
