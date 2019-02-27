package com.netty.Server;

import com.netty.init.SimpleChatServerInitializer;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
* @author Eden
* @version 
* 服务端程序入口
*/
public class SimpleChatServer {
	private int port;
	
	public SimpleChatServer(int port){
		this.port = port;
	}
	
	public void run(){
		// 接收进来的链接
		EventLoopGroup worker = new NioEventLoopGroup();
		// 处理已接收的链接
		EventLoopGroup boss = new NioEventLoopGroup();
		try {
			// 
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(boss, worker)
				.channel(NioServerSocketChannel.class)          // 设置如何接受链接
				.childHandler(new SimpleChatServerInitializer())    // 配置chanel
				.option(ChannelOption.SO_BACKLOG, 128)          // 配置缓存
				.childOption(ChannelOption.SO_KEEPALIVE, true); // 开启心跳机制
			System.out.println("SimpleChatServer stating....");
			ChannelFuture future = bootstrap.bind(port).sync();
			future.channel().closeFuture().sync();
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			// TODO: handle finally clause
			worker.shutdownGracefully();
			boss.shutdownGracefully();
			System.out.println("SimpleChatServer shutdowning....");
		}
	}
	
	public static void main(String[] args){
		int port = 8080;
		new SimpleChatServer(port).run();
	}
}
