package com.example.start.chat.processor;

import com.example.start.chat.protocol.IMMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 存储信息
 */
public class InformationOperateMap {

    public static ConcurrentMap<String, ConcurrentMap<String, InformationOperateMap>> map = new ConcurrentHashMap<>();

    private ChannelHandlerContext ctx;
    private IMMessage mage;

    private InformationOperateMap(ChannelHandlerContext ctx, IMMessage mage) {
        this.ctx = ctx;
        this.mage = mage;
    }

    /**
     * 添加用户信息
     * @param ctx
     * @param mage
     */
    public static void add(ChannelHandlerContext ctx, IMMessage mage) {
        InformationOperateMap iom = new InformationOperateMap(ctx, mage);
        ConcurrentMap<String, InformationOperateMap> cmap = new ConcurrentHashMap<>();
        if (map.containsKey(mage.getChatRoom())) {
            map.get(mage.getChatRoom()).put(mage.getSender(), iom);
        } else {
            cmap.put(mage.getSender(), iom);
            map.put(mage.getChatRoom(), cmap);
        }
    }

    /**
     * 删除用户信息
     * @param id
     * @param table
     */
    public static void delete(String id, String table) {
        ConcurrentMap<String, InformationOperateMap> cmap = map.get(table);
        if (cmap.size() <= 1) {
            map.remove(table);
        } else {
            cmap.remove(id);
        }
    }


    /**
     * 判断是否存在该用户
     * @param mage
     * @return 存在false 不存在true
     */
    public static boolean isNo(IMMessage mage) {
        return map.containsKey(mage.getChatRoom()) ? map.get(mage.getChatRoom()).containsKey(mage.getSender()) ? false : true : true;
    }

    /**
     * 给用户发送消息
     * @param mage
     * @throws Exception
     */
    public void sead(IMMessage mage) throws Exception{
        //this.ctx.channel().write(new TextWebSocketFrame(mage.toJson()));
        //this.ctx.flush();
        ctx.writeAndFlush(new TextWebSocketFrame(mage.toJson()));
    }

    public IMMessage getMage() {
        return mage;
    }
}
