package com.netty.init;

import com.netty.adapter.SimpleChatServerHandler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
* @author Eden
* @version 
* 初始化服务端处理器
*/
public class SimpleChatServerInitializer extends ChannelInitializer<SocketChannel>{

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		// TODO Auto-generated method stub
		ChannelPipeline pipeline = ch.pipeline();
		//添加处理类
		//使用'\r','\n'分割帧
//		pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
		// 解码器
//		pipeline.addLast("decoder", new StringDecoder());
		// 编码器
//		pipeline.addLast("encoder", new StringEncoder());
		
		pipeline.addLast("http-codec", new HttpServerCodec()); 
		// Http消息组装  
        pipeline.addLast("aggregator", new HttpObjectAggregator(65536)); // Http消息组装  
        // WebSocket通信支持  
        pipeline.addLast("http-chunked", new ChunkedWriteHandler()); 
        // 自定义处理器
 		pipeline.addLast("handler", new SimpleChatServerHandler());
        
		System.out.println("SimpleChatClient: " + ch.remoteAddress() + "已连接");
	}

}
