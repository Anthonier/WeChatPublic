package com.qianjg.customservice;
import com.qianjg.util.WeChatUtil;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
/**
 * 准备客服消息
 * @author Think
 *
 */
public class PrepareForSendCustomMessage {
	public static Logger logger = Logger.getLogger(PrepareForSendCustomMessage.class);

	public static boolean sendCustomMessage(String accessToken, String jsonMsg) {
		logger.error("消息内容{}" + jsonMsg);
		boolean result = true;

		String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);

		JSONObject jsonObject = WeChatUtil.httpRequest(requestUrl, "POST", jsonMsg);
		if (jsonObject != null) {
			int errCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if (errCode == 0) {
				result = true;
				logger.info("客服消息发送成功,errcode" + errCode + ",errormsg:" + errorMsg);
			} else {
				logger.error("客服消息发送失败,errcode" + errCode + ",errormsg:" + errorMsg);
			}
		}
		return result;
	}
}
