package com.netty.adapter;

import java.net.URI;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;

/**
* @author Eden
* @version 
* 客户端处理器
*/
public class SimpleChatClientHandler extends SimpleChannelInboundHandler<String>{

	private String wsUri = "ws://127.0.0.1:9999/ws";
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		// TODO Auto-generated method stub
		WebSocketClientHandshaker handshaker = WebSocketClientHandshakerFactory.newHandshaker(new URI(wsUri), null, null, false, null);
		String rMsg = (String)msg;
		Channel channel = ctx.channel();
		System.out.println(channel.remoteAddress()+" :"+rMsg+"/n");
	}
	
	@Override
	protected void messageReceived(ChannelHandlerContext ctx, String msg) throws Exception {
		// TODO Auto-generated method stub
		Channel channel = ctx.channel();
		System.out.println(channel.remoteAddress()+" :"+msg+"/n");
	}
	
	@SuppressWarnings("unused")
	private String bufToString(ByteBuf bf){
        byte[] byteArray = new byte[bf.readableBytes()];  
        bf.readBytes(byteArray);  
        String result = new String(byteArray);
        return result;
	}

}
