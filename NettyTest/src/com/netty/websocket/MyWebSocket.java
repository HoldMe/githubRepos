package com.netty.websocket;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/wbSocket")
public class MyWebSocket {

	private Session session;
	//客户端对应的MyWebSocket对象
	public static CopyOnWriteArraySet<MyWebSocket> webSockets = new CopyOnWriteArraySet<MyWebSocket>();
	
	
	@OnOpen
	public void onOpen(Session session){
		this.session = session;
		webSockets.add(this);
		System.out.println("New session insert,sessionId is "+ session.getId());
	}
	
	
     @OnClose
     public void onClose(){
    	 webSockets.remove(this);
         System.out.println("A session insert,sessionId is "+ session.getId());
     }
     
     @OnMessage
     public synchronized void onMessage(String message ,Session session) throws IOException{
         System.out.println(message + "from " + session.getId());
         this.session.getAsyncRemote().sendText("server recive msg: "+message);
     }
 
}
