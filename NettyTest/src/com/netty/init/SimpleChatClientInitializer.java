package com.netty.init;

import com.netty.adapter.SimpleChatClientHandler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
* @author Eden
* @version 
* 初始化客户端处理器
*/
public class SimpleChatClientInitializer extends ChannelInitializer<SocketChannel>{

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		// TODO Auto-generated method stub
		ChannelPipeline pipeline = ch.pipeline();
		//添加处理类
		//使用'\r','\n'分割帧
		pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
		// 解码器
		pipeline.addLast("decoder", new StringDecoder());
		// 编码器
		pipeline.addLast("encoder", new StringEncoder());
		// 处理器
		pipeline.addLast("handler", new SimpleChatClientHandler());
	}

}
