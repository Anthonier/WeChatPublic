package com.qianjg.msgtype.response;

/**
 * 用户发送图片消息 ，响应文本内容给用户
 */
import java.util.Map;

import com.qianjg.faceservice.FaceService;
import com.qianjg.message.response.TextResponseMessage;
import com.qianjg.util.MessageUtil;

public class ImageResponse {

	public static String imageResponseToUser(String picUrl,
			String responseContent, TextResponseMessage textResponseMessage) {
		String responseMessage = null;
		// 人脸检测
		responseContent = FaceService.detect(picUrl);
		responseMessage = MessageUtil.parseTextMessageToXml(
				textResponseMessage, responseMessage, responseContent);
		return responseMessage;
	}

	public static String getFaceUsage() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("人脸识别使用指南").append("\n\n");
		buffer.append("您发送一张清晰的照片，就能帮您分析出种族、年龄、性别等信息").append("\n");
		buffer.append("快来测试下您是不是小鲜肉，小清新吧！");
		return buffer.toString();
	}
}
