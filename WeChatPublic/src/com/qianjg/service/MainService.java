package com.qianjg.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javassist.compiler.ast.StringL;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.qianjg.message.request.LocationRequestMessage;
import com.qianjg.message.response.Article;
import com.qianjg.message.response.NewsResponseMessage;
import com.qianjg.message.response.TextResponseMessage;
import com.qianjg.msgtype.response.EventResponse;
import com.qianjg.msgtype.response.ImageResponse;
import com.qianjg.msgtype.response.ImageTextResponse;
import com.qianjg.msgtype.response.TextResponse;
import com.qianjg.msgtype.response.VoiceResponse;
import com.qianjg.pojomap.UserLocation;
import com.qianjg.util.BaiduMapUtil;
import com.qianjg.util.MessageUtil;
import com.qianjg.util.MySQLUtil;

/**
 * 核心服务类 MainServlet的doPost()方法调用MainService中的handleRequest()方法接受、处理消息
 * 目的是为了解耦，即业务相关的操作都不在Servlet里处理，而是完全交由核心服务类MainService去做。
 * 
 * @author Administrator
 *
 */
public class MainService {
	private static Logger logger = Logger.getLogger(MainService.class);

	/**
	 * 
	 * @param request 请求
	 * @return String 字符串
	 * @throws UnsupportedEncodingException 不支持编码异常
	 */
	public static String handleRequest(HttpServletRequest request)
			throws UnsupportedEncodingException {
		String responseMessage = null;
		StringBuffer buffer = null;
		String responseContent = null;
		try {
			// 默认返回的文本消息内容

			/**
			 * xml请求解析 调用消息工具类MessageUtil解析微信发来的xml格式的消息，解析的结果放在HashMap里。
			 */
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// logger.debug("requestMap"+requestMap);
			/**
			 * 从HashMap中取出消息中的字段
			 */
			// 发送方帐号（一个OpenID）
			String fromUserName = requestMap.get("FromUserName");
			// 开发者微信号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			// 消息ID
			String msgId = requestMap.get("MsgId");
			// logger.debug("接收的fromUserName"+fromUserName+"-----接收的toUserName"+toUserName+"-----接收的msgType"+msgType);
			// logger.debug("Content is"+content);
			// 消息创建时间
			String createTime = requestMap.get("CreateTime");

			/**
			 * 组装要返回的文本消息对象
			 */

			// 默认回复此文本消息
			TextResponseMessage textResponseMessage = new TextResponseMessage();
			textResponseMessage.setToUserName(fromUserName);
			textResponseMessage.setFromUserName(toUserName);
			textResponseMessage.setCreateTime(new Date().getTime());
			textResponseMessage
					.setMsgType(MessageUtil.RESPONSE_MESSAGE_TYPE_TEXT);
			textResponseMessage.setFuncFlag(0);
			textResponseMessage
					.setContent("欢迎访问<a href=\"http://www.qianjg.com/CommerceEnglish/introduction.html\">网站</a>!");
			// 将文本消息转换成xml字符串
			responseMessage = MessageUtil.textMessageToXml(textResponseMessage);

			/**
			 * 接受微信发送的各类消息，根据MsgType判断属于哪种类型的消息
			 */
			if (msgType.equals(MessageUtil.REQUEST_MESSAGE_TYPE_TEXT)) {
				/**
				 * 实例化文本信息、文本消息
				 */
				// 消息内容
				String content = requestMap.get("Content");

				responseMessage = TextResponse.textMessage(content, createTime,
						responseContent, fromUserName, toUserName,
						textResponseMessage, request);
				logger.debug("ServiceResponseMessage----------------------"
						+ responseMessage);
			}

			// 图片消息
			else if (msgType.equals(MessageUtil.REQUEST_MESSAGE_TYPE_IMAGE)) {
				// 图片链接
				String picUrl = requestMap.get("PicUrl");

				// responseContent = "您发送的是图片消息！";
				// responseMessage=MessageUtil.parseTextMessageToXml(textResponseMessage,
				// responseMessage, responseContent);
				responseMessage = ImageResponse.imageResponseToUser(picUrl,
						responseContent, textResponseMessage);
				// logger.debug("ImageInformation:"+responseContent);
			}
			// 地理位置消息
			else if (msgType.equals(MessageUtil.REQUEST_MESSAGE_TYPE_LOCATION)) {
				responseContent = "您发送的是地理位置消息！";
				/**
				 * 用户发送的经纬度
				 */
				// 地理位置纬度
				String lat = requestMap.get("Location_X");
				// 地理位置经度
				String lon = requestMap.get("Location_Y");

				// 地理位置信息
				String loc = requestMap.get("Label");
				// 地图缩放大小
				String scale = requestMap.get("Scale");

				if (loc != null) {
					LocationRequestMessage locationMessage = new LocationRequestMessage();
					locationMessage.setLabel(loc);
					locationMessage.setLocation_X(lat);
					locationMessage.setLocation_Y(lon);
					logger.error("location:" + locationMessage.getLabel());
				}
				logger.error("Location Message-lat:" + lat + ",lon" + "lon"
						+ ",loc" + loc);

				/**
				 * 坐标转换后的经纬度 注：lon即lng 经度
				 */
				String bd09Lng = null;
				String bd09Lat = null;
				// 调用接口转换坐标
				UserLocation usreLocation = BaiduMapUtil.convertCoord(lon, lat);
				if (null != usreLocation) {
					bd09Lng = usreLocation.getBd09Lng();
					bd09Lat = usreLocation.getBd09Lat();
				}
				// 保存用户地理位置
				if (null != bd09Lng && null != bd09Lat) {
					MySQLUtil.saveUserLocation(request, fromUserName, lon, lat,
							bd09Lng, bd09Lat);
				} else {
					logger.error("bd09Lng and bd09Lat are null,经纬度为空");
				}
				StringBuffer bufferLocation = new StringBuffer();
				bufferLocation.append("[愉快]").append("成功接收您发送的位置！")
						.append("\n\n");
				bufferLocation.append("您可以输入搜索任意关键字获取周边信息了，例如：").append("\n");
				bufferLocation.append("附近餐厅").append("\n");
				bufferLocation.append("附近酒店").append("\n");
				bufferLocation.append("附近银行").append("\n");
				bufferLocation.append("以\"附近\"两字开头的任意关键字！");
				responseContent = bufferLocation.toString();
				responseMessage = MessageUtil.parseTextMessageToXml(
						textResponseMessage, responseMessage, responseContent);

				/*
				 * responseMessage =
				 * MessageUtil.parseTextMessageToXml(textResponseMessage,
				 * responseMessage, responseContent);
				 */
				// logger.debug("地理位置消息："+responseContent);
			}
			// 链接消息
			else if (msgType.equals(MessageUtil.REQUEST_MESSAGE_TYPE_LINK)) {
				responseContent = "您发送的是链接消息！";
				// 消息标题
				String title = requestMap.get("Title");
				// 消息描述
				String description = requestMap.get("Description");
				// 消息链接
				String url = requestMap.get("Url");

				responseMessage = MessageUtil.parseTextMessageToXml(
						textResponseMessage, responseMessage, responseContent);
				// logger.debug("链接消息："+responseMessage);
			}
			// 语音消息
			else if (msgType.equals(MessageUtil.REQUEST_MESSAGE_TYPE_VOICE)) {
				// responseContent = "您发送的是语音消息！";
				// 媒体文件标识
				String mediaId = requestMap.get("MediaId");
				// 文件格式
				String format = requestMap.get("Format");
				// 语音识别结果，UTF8编码
				String recognition = new String(requestMap.get("Recognition"));

				responseMessage = VoiceResponse.judgeVoice(recognition,
						responseContent, responseMessage, textResponseMessage,
						fromUserName, toUserName);
				/*
				 * responseMessage =
				 * MessageUtil.parseTextMessageToXml(textResponseMessage,
				 * responseMessage, responseContent);
				 */
				// logger.debug("音频消息："+responseMessage);

			}
			// 视频消息
			else if (msgType.equals(MessageUtil.REQUEST_MESSAGE_TYPE_VIDEO)) {
				// 视频消息媒体id，可以调用多媒体文件下载接口拉取数据。
				String mediaId = requestMap.get("MediaId");
				// 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
				String thumbMediaId = requestMap.get("ThumbMediaId");
			}
			// 小视频消息
			else if (msgType
					.equals(MessageUtil.REQUEST_MESSAGE_TYPE_SHORTVIDEO)) {
				// 视频消息媒体id，可以调用多媒体文件下载接口拉取数据。
				String mediaId = requestMap.get("MediaId");
				// 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
				String thumbMediaId = requestMap.get("ThumbMediaId");
			}

			// 事件推送
			else if (msgType.equals(MessageUtil.REQUEST_MESSAGE_TYPE_EVENT)) {
				String eventType = requestMap.get("Event");
				String eventKey = requestMap.get("EventKey");
				/**
				 * 实例化订阅，取消订阅，自定义菜单实例化
				 */

				responseMessage = EventResponse.responseEventMessage(eventType,
						buffer, eventKey, fromUserName, toUserName,
						responseMessage,textResponseMessage);

				// logger.error("event subcribe--"+responseMessage);

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		buffer = null;
		String value = responseMessage;
		logger.error("Last Value~~~~~~~~~~~~~~~" + value);
		return responseMessage;
	}
}
