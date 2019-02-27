package com.netty.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.netty.init.SimpleChatClientInitializer;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
* @author Eden
* @version
* 客户端程序入口
*/
public class SimpleChatClient {

	private String host;
	private int port;
	SimpleChatClient(String host,int port){
		this.host = host;
		this.port = port;
	}
	
	public void run(){
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(group)
				.channel(NioSocketChannel.class)
				.handler(new SimpleChatClientInitializer());
			Channel channel = bootstrap.connect(host, port).sync().channel();
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			while(true){
				channel.writeAndFlush(reader.readLine()+"\r\n");
			}
		} catch(Exception e){
			e.printStackTrace();
		}finally {
			// TODO: handle finally clause
			group.shutdownGracefully();
		}
	}
	
	public static void main(String[] args) {
		new SimpleChatClient("127.0.0.1", 8080).run();
	}
	
}
