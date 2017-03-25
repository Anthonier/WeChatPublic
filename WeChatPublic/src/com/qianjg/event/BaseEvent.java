package com.qianjg.event;
/**
 * 事件基类
 * @author Administrator
 *
 */
public class BaseEvent {
	//开发者微信号
	private String ToUserName;
	//发送方账号（一个OpenID）
	private String FromUserName;
	//消息创建时间 （整型）
	private long CreateTime;
	//消息类型，event
	private String MsgType;
	//事件类型，subscribe(订阅)、unsubscribe(取消订阅)
	private String Event;
	/**
	 * @return the toUserName
	 */
	public String getToUserName() {
		return ToUserName;
	}
	/**
	 * @param toUserName the toUserName to set
	 */
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	/**
	 * @return the fromUserName
	 */
	public String getFromUserName() {
		return FromUserName;
	}
	/**
	 * @param fromUserName the fromUserName to set
	 */
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	/**
	 * @return the createTime
	 */
	public long getCreateTime() {
		return CreateTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}
	/**
	 * @return the msgType
	 */
	public String getMsgType() {
		return MsgType;
	}
	/**
	 * @param msgType the msgType to set
	 */
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	/**
	 * @return the event
	 */
	public String getEvent() {
		return Event;
	}
	/**
	 * @param event the event to set
	 */
	public void setEvent(String event) {
		Event = event;
	}
	
	
}
