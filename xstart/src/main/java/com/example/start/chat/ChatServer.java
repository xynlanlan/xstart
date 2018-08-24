package com.example.start.chat;

import com.example.start.chat.handler.HttpHandler;
import com.example.start.chat.handler.WebSocktHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.apache.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class ChatServer{
	
	private static Logger LOG = Logger.getLogger(ChatServer.class);
	
    public void start(int port) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, 1024)
            .childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline pipeline = ch.pipeline();
                    //5分钟未读
                    pipeline.addLast(new IdleStateHandler(1,0,0, TimeUnit.MINUTES));
                    //websocket协议本身是基于http协议的，所以这边也要使用http解编码器
                    pipeline.addLast(new HttpServerCodec());
                    //以块的方式来写的处理器
                    pipeline.addLast(new ChunkedWriteHandler());
                    //netty是基于分段请求的，HttpObjectAggregator的作用是将请求分段再聚合,参数是聚合字节的最大长度
                    pipeline.addLast(new HttpObjectAggregator(64 * 1024));
                    pipeline.addLast(new HttpHandler("chat.html"));

                    pipeline.addLast(new WebSocketServerProtocolHandler("/im"));
                    pipeline.addLast(new WebSocktHandler());
                }
            }); 
            ChannelFuture f = b.bind(port).sync();
            LOG.info("服务已启动,监听端口" + port);
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
    
    
    /*public static void main(String[] args) throws IOException{
        new ChatServer().start(80);
    }*/
    
}
