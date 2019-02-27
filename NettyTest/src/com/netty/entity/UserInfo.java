package com.netty.entity;

import java.io.Serializable;

import io.netty.channel.Channel;

/**
* @author Eden
* @version 创建时间：2018年11月16日 下午3:23:48
* 类说明
*/
public class UserInfo implements Serializable{

	private static final long serialVersionUID = 6817502942176115473L;
	
	//主键
	private String userId;
	//管道
	private Channel channel;
	//地址
	private String addr;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Channel getChannel() {
		return channel;
	}
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
}
