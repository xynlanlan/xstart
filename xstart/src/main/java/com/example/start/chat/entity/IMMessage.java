package com.example.start.chat.entity;

import java.io.Serializable;

public class IMMessage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5835578206705220317L;
	
	private String sendId;
	private String sendName;
	private String friendId;
	private String friendName;
	private String msg;
	private boolean isFlower;
	private int type; // 0: 系统消息 1:登录  2：单聊  3:群聊  4:下线

	public IMMessage() {
	}

	public IMMessage(int type, String msg) {
		this.msg = msg;
		this.type = type;
	}

	public String getSendId() {
		return sendId;
	}
	public void setSendId(String sendId) {
		this.sendId = sendId;
	}
	public String getFriendId() {
		return friendId;
	}
	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getSendName() {
		return sendName;
	}
	public void setSendName(String sendName) {
		this.sendName = sendName;
	}
	public String getFriendName() {
		return friendName;
	}
	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}
	public boolean getIsFlower() {
		return isFlower;
	}
	public void setIsFlower(boolean isFlower) {
		this.isFlower = isFlower;
	}
	@Override
	public String toString() {
		return "IMMessage [sendId=" + sendId + ", sendName=" + sendName + ", friendId=" + friendId + ", friendName="
				+ friendName + ", msg=" + msg + ", isFlower=" + isFlower + ", type=" + type + "]";
	}
}
