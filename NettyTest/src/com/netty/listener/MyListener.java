package com.netty.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.netty.Server.SimpleChatServer;

/**
* @author Eden
* @version 创建时间：2018年11月16日 下午3:55:02
* 启动监听
*/
public class MyListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		SimpleChatServer chatServer = new SimpleChatServer(9999);
		chatServer.run();
	}

}
