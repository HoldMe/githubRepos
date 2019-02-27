package com.netty.adapter;

import com.netty.entity.UserInfoManager;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
* @author Eden
* @version
* 服务端处理器
*/
/**
 * @author Administrator
 * 服务端处理器
 */
public class SimpleChatServerHandler extends SimpleChannelInboundHandler<String>{
	
	public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	private WebSocketServerHandshaker handshaker;
    private final String wsUri = "/ws";
	
	/**
	 * 客户端加入
	 */
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		Channel channel = ctx.channel();
		channels.writeAndFlush("[server] - "+channel.remoteAddress()+" 上线\n");
		channels.add(channel);
	}

	/**
	 * 客户端退出
	 */
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		Channel channel = ctx.channel();
		channels.writeAndFlush("[server] - "+channel.remoteAddress()+" 下线\n");
	}

	/**
	 * 读取客户端内容并转发
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		// TODO Auto-generated method stub
		if (msg instanceof FullHttpRequest) {// 如果是HTTP请求，进行HTTP操作
            handleHttpRequest(ctx, (FullHttpRequest) msg);
        } else if (msg instanceof WebSocketFrame) {// 如果是Websocket请求，则进行websocket操作
            handleWebSocketFrame(ctx, (WebSocketFrame) msg);
        }else{
        	String rMsg = (String)msg;
        	Channel inConming = ctx.channel();
        	if(channels.size()>0){
        		for(Channel channel : channels){
        			if(channel!=inConming){
        				channel.writeAndFlush(channel.remoteAddress()+": "+rMsg+"\n");
        			}else{
        				channel.writeAndFlush("[you]: "+rMsg+"\n");
        			}
        		}
        	}else{
        		System.out.println("暂无在线用户\n");
        	}
        }
	}

	/*
	 * 客户端连接
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
	}

	/**
	 * 客户端掉线
	 */
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		Channel channel = ctx.channel();
		System.out.println(channel.remoteAddress()+"掉线/n");
	}

	/**
	 * 客户端异常捕捉
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
		Channel channel = ctx.channel();
		System.out.println(channel.remoteAddress()+"客户端异常："+cause.getMessage());
	}

	/* 
	 * 
	 */
	@Override
	protected void messageReceived(ChannelHandlerContext ctx, String msg) throws Exception {
		// TODO Auto-generated method stub
		Channel inConming = ctx.channel();
		if(channels.size()>0){
			for(Channel channel : channels){
				if(channel!=inConming){
					channel.writeAndFlush("[you]: "+msg+"\n");
				}else{
					channel.writeAndFlush(channel.remoteAddress()+": "+msg+"\n");
				}
			}
		}else{
			System.out.println("暂无在线用户\n");
		}
	}
	
	@SuppressWarnings("unused")
	private String bufToString(ByteBuf bf){
        byte[] byteArray = new byte[bf.readableBytes()];  
        bf.readBytes(byteArray);  
        String result = new String(byteArray);
        return result;
	}
	
	/**
	 * 功能：处理HTTP的代码
	 */
	private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req){
		// 如果HTTP解码失败，返回HHTP异常
		if(req instanceof HttpRequest){
			// 如果是websocket请求就握手升级
			if(req.uri().equalsIgnoreCase(wsUri)){
				WebSocketServerHandshakerFactory shFactory = new WebSocketServerHandshakerFactory(wsUri, null, false);
				handshaker = shFactory.newHandshaker(req);
				if(handshaker==null){
					WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
				}else{
					handshaker.handshake(ctx.channel(), req);
				}
			}
		}
	}
	
	/*
     * 处理Websocket的代码
     */
    private void handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
        // 判断是否是关闭链路的指令
        //System.out.println("websocket get");
        if (frame instanceof CloseWebSocketFrame) {
            handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
            return;
        }
        // 判断是否是Ping消息
        if (frame instanceof PingWebSocketFrame) {
            ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
            return;
        }
        // 文本消息，不支持二进制消息
        if (frame instanceof TextWebSocketFrame) {
            // 返回应答消息
            String requestmsg = ((TextWebSocketFrame) frame).text();
            System.out.println("websocket消息======"+requestmsg);
            String[] array= requestmsg.split(",");
            // 将通道加入通道管理器
            UserInfoManager.addChannel(ctx.channel(),array[0]);
//            UserInfo userInfo = UserInfoManager.getUserInfo(ctx.channel());
            if (array.length== 3) {
	            // 将信息返回给h5
	            String sendid=array[0];String friendid=array[1];String messageid=array[2];
	            UserInfoManager.broadcastMess(friendid,messageid,sendid);
            }
        }
    }
	
}
