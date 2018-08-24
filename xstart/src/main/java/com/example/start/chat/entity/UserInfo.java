package com.example.start.chat.entity;

import java.io.Serializable;

import io.netty.channel.Channel;

public class UserInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1289074956074061022L;
	private String id;
	private String name;
	private String addr;
	private Channel channel;
	
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Channel getChannel() {
		return channel;
	}
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	@Override
	public String toString() {
		return "MyMessage [id=" + id + ", addr=" + addr + ", name=" + name + ", channel=" + channel
				+ "]";
	}
}
