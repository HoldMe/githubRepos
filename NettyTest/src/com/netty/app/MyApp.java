package com.netty.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

public class MyApp {

	public Session session;
	protected  void initSession(){
		String url = "ws://127.0.0.1:8080/wbSocket";
		WebSocketContainer container = ContainerProvider.getWebSocketContainer();
		try {
			session = container.connectToServer(String.class, new URI(url));
			System.out.println("Connecting to " + url);
		} catch (DeploymentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException {
		MyApp app = new MyApp();
		app.initSession();
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		while(true){
			String msg = bf.readLine();
			app.session.getAsyncRemote().sendText(msg);
		}
	}
}
