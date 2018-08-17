package com.example.start.chat.protocol;

import com.alibaba.fastjson.JSON;
import org.msgpack.annotation.Message;



/**
 * 自定义消息实体类
 *
 */
@Message
public class IMMessage{
	private int type;	// 1:单聊 2::群聊
	private String chatRoom;	//聊天室id
	private String chatRoomName = "群聊";	//聊天室名称
	private String addr;	//IP地址及端口
	private String cmd;		//命令类型[LOGIN]或者[SYSTEM]或者[LOGOUT]
	private long time;		//命令发送时间
	private int online;		//当前在线人数
	private String sender;  //发送人
	private String receiver;	//接收人
	private String content;	//消息内容
	
	public IMMessage(){}
	
	public IMMessage(String cmd,long time,int online,String content){
		this.cmd = cmd;
		this.time = time;
		this.online = online;
		this.content = content;
	}
	public IMMessage(String cmd,long time,String sender){
		this.cmd = cmd;
		this.time = time;
		this.sender = sender;
	}
	public IMMessage(String cmd,long time,String sender,String content){
		this.cmd = cmd;
		this.time = time;
		this.sender = sender;
		this.content = content;
	}

	public String getChatRoom() {
		return chatRoom;
	}

	public void setChatRoom(String chatRoom) {
		this.chatRoom = chatRoom;
	}

	public String getCmd() {
		return cmd;
	}
	
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public int getOnline() {
		return online;
	}
	public void setOnline(int online) {
		this.online = online;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getChatRoomName() {
		return chatRoomName;
	}

	public void setChatRoomName(String chatRoomName) {
		this.chatRoomName = chatRoomName;
	}

	public IMMessage setChatRoomId(String chatRoom) {
		this.setChatRoom(chatRoom);
		return this;
	}

	public String toJson() {
		return JSON.toJSONString(this);
	}

	public static IMMessage strJson2Mage(String text) {
		return JSON.parseObject(text, IMMessage.class);
	}
}
