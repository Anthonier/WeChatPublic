package com.qianjg.msgtype.response;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.qianjg.baidutranslet.service.Translet;
import com.qianjg.message.response.Article;
import com.qianjg.message.response.NewsResponseMessage;
import com.qianjg.message.response.TextResponseMessage;
import com.qianjg.pojorobot.RobotNews;
import com.qianjg.robot.RobotService;
import com.qianjg.service.MainService;
import com.qianjg.util.MessageUtil;

/**
 * 响应事件消息
 * 
 * @author Think
 *
 */
public class EventResponse {
	private static Logger logger = Logger.getLogger(EventResponse.class);

	public static String responseEventMessage(String eventType,
			StringBuffer buffer, String eventKey, String fromUserName,
			String toUserName, String responseMessage,TextResponseMessage textResponseMessage) {
		String responseContent = null;
		// 订阅
		if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
			buffer = new StringBuffer();
			buffer.append("亲，我是微秘助手，想你了，您的咖啡已经泡好！").append("\n\n");
			buffer.append("1  焦点新闻").append("\n");
			buffer.append("2  周边搜索").append("\n");
			buffer.append("3  趣味人脸识别").append("\n");
			buffer.append("4  世界语言翻译").append("\n");
			buffer.append("5  微机器人助手").append("\n");
			buffer.append("6  美食部落").append("\n");
			buffer.append("7  商务英语平台").append("\n");
			buffer.append("8  记事本系统").append("\n");
			buffer.append("9  友情链接").append("\n");
			buffer.append("10  关于我们").append("\n");
			// 由于href属性值必须使用双引号，所以需要转义
			buffer.append("欢迎访问<a href=\"http://www.qianjg.com#wechat_webview_type=1\">网站</a>");
			responseContent = buffer.toString();
			// logger.debug("订阅消息：" + responseContent);
		}
		// 取消订阅
		else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
			// 取消订阅后用户不再收到公众号发送的消息，因此不需要回复
		}
		// 自定义菜单点击事件
		else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
			// 事件Key值，与创建自定义菜单式的key值对应
			if (eventKey.equals("11")) {
				//焦点新闻
				responseMessage=TextResponse.textMessage("焦点新闻", null, responseContent, fromUserName, toUserName, null, null);
				return responseMessage;
			} else if (eventKey.equals("12")) {
				//周边搜索
				responseContent=TextResponse.getUsage();
			} else if (eventKey.equals("21")) {
				//趣味人脸识别
				responseContent = ImageResponse.getFaceUsage();
			} else if (eventKey.equals("22")) {
				//世界语言翻译
				responseContent=Translet.getTransletUsage();
			} else if (eventKey.equals("23")) {
				//机器人助手
				responseContent = "您可以和我语音";
			} 
		}
		// 处理扫描带参数二维码事件
		else if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN)) {
			responseContent = "扫描带参数二维码";
		}
		// 处理上报地理位置事件
		else if (eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)) {
			responseContent = "上报地理位置";
		}
		// 点击菜单跳转链接时的事件推送
		else if (eventType.equals(MessageUtil.EVENT_TYPE_VIEW)) {
			responseContent = "点击菜单跳转链接";
		}
		
		responseMessage = MessageUtil.parseTextMessageToXml(
				textResponseMessage, responseMessage, responseContent);
		return responseMessage;
	}
}
