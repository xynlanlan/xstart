package com.example.start.chat.handler;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.URL;

import com.google.common.io.Resources;
import io.netty.handler.codec.http.*;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.stream.ChunkedNioFile;
import org.apache.log4j.Logger;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.DefaultFileRegion;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.util.ResourceUtils;

public class HttpHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
	private static Logger LOG = Logger.getLogger(HttpHandler.class);

	// 获取class路径
	private URL baseURL = HttpHandler.class.getProtectionDomain().getCodeSource().getLocation();
	private final String wsUri;
	public final static String webroot = "classpath:wchat/";

	public HttpHandler(String url) {
		this.wsUri = url;
	}

	private File getResource(String fileName) throws Exception {
		String path = ResourceUtils.getURL(webroot + fileName).getPath();
		LOG.info("=====path:" + path);
		return new File(path);
	}
	@Override
	public void messageReceived(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
		String uri = request.getUri();

		RandomAccessFile file = null;
		try {
			String page = uri.equals("/") ? wsUri : uri;
			file = new RandomAccessFile(getResource(page), "r");
		} catch (Exception e) {
			ctx.fireChannelRead(request.retain());
			return;
		}
		if (HttpHeaders.is100ContinueExpected(request)) {
			send100Continue(ctx);
		}
		HttpResponse response = new DefaultHttpResponse(request.getProtocolVersion(), HttpResponseStatus.OK);
		String contextType = "text/html;";
		if (uri.endsWith(".css")) {
			contextType = "text/css;";
		} else if (uri.endsWith(".js")) {
			contextType = "text/javascript;";
		} else if (uri.toLowerCase().matches("(jpg|png|gif)$")) {
			String ext = uri.substring(uri.lastIndexOf("."));
			contextType = "image/" + ext;
		}
		response.headers().set(HttpHeaders.Names.CONTENT_TYPE, contextType + "charset=utf-8;");

		boolean keepAlive = HttpHeaders.isKeepAlive(request);

		if (keepAlive) {
			response.headers().set(HttpHeaders.Names.CONTENT_LENGTH, file.length());
			response.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
		}
		ctx.write(response);

		if (ctx.pipeline().get(SslHandler.class) == null) {
			ctx.write(new DefaultFileRegion(file.getChannel(), 0, file.length()));
		} else {
			ctx.write(new ChunkedNioFile(file.getChannel()));
		}
		ChannelFuture future = ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);
		if (!keepAlive) {
			future.addListener(ChannelFutureListener.CLOSE);
		}

		file.close();
	}

	private static void send100Continue(ChannelHandlerContext ctx) {
		FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.CONTINUE);
		ctx.writeAndFlush(response);
	}
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		Channel client = ctx.channel();
		LOG.info("Client:" + client.remoteAddress() + "异常");
		// 当出现异常就关闭连接
		cause.printStackTrace();
		ctx.close();
	}

}
