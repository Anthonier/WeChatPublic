package com.qianjg.customservice;

import com.qianjg.util.WeChatUtil;
/**
 * 发送客服消息
 * @author Think
 *
 */
public class SendCustomMessage {
	public static void main(String[] args) {
		String accessToken = WeChatUtil.getAccessToken("你的appid", "你的app_secret")
				.getToken();

		String jsonTextMsg = MakeCustomMessage.makeTextCustomMessage("订阅的微信号ID",
				"这是一条客服消息");

		PrepareForSendCustomMessage.sendCustomMessage(accessToken, jsonTextMsg);
	}
}