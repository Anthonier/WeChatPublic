package com.qianjg.msgtype.response;

import com.qianjg.message.request.LocationRequestMessage;
import com.qianjg.message.response.Article;
import com.qianjg.message.response.NewsResponseMessage;
import com.qianjg.message.response.TextResponseMessage;
import com.qianjg.pojorobot.RobotNews;
import com.qianjg.robot.RobotService;
import com.qianjg.util.MessageUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 * 响应语音消息给用户
 * 
 * @author ThinkQJG
 *
 */
public class VoiceResponse {
	private static Logger logger = Logger.getLogger(VoiceResponse.class);

	public static String judgeVoice(String recognition, String responseContent,
			String responseMessage, TextResponseMessage textResponseMessage,
			String fromUserName, String toUserName) {
		StringBuffer stringBuffer = null;
		// 如果语音中不出现餐厅，酒店，路，公交，从到，租，那么进入图灵对话模式
		if ((!recognition.contains("餐厅")) && (!recognition.contains("酒店"))
				&& (!recognition.contains("路"))
				&& (!recognition.contains("公交"))
				&& (!recognition.contains("从到"))
				&& (!recognition.contains("租"))) {
			Map<String, String> map = RobotService.getRobotResult(recognition,
					null, null, null);
			stringBuffer = new StringBuffer();
			String code = (String) map.get("code");
			logger.error("code:" + code);
			String robotText = (String) map.get("text");
			stringBuffer.append(robotText).append("\n");
			if ((recognition.contains("图片")) || (recognition.contains("航班"))
					|| (recognition.contains("车"))
					|| (recognition.contains("高铁"))
					|| (recognition.contains("电影"))
					|| (recognition.contains("股票"))) {
				String url = (String) map.get("url");
				if (url != null) {
					stringBuffer.append(url).append("\n");
				} else {
					stringBuffer.append("亲，您再说的具体一些");
				}
			}
		}
		String textLocation;
		// 语音中出现餐厅，酒店，路，公交，从到，租
		if ((recognition.contains("餐厅")) || (recognition.contains("酒店"))
				|| (recognition.contains("路")) || (recognition.contains("公交"))
				|| (recognition.contains("从到")) || (recognition.contains("租"))) {
			LocationRequestMessage locationMessage = new LocationRequestMessage();
			String loc = locationMessage.getLabel();
			Map<String, String> mapLocation = null;
			stringBuffer = new StringBuffer();
			// 语音中出现餐厅，那么需要位置信息，纬度和经度
			if ((recognition.contains("餐厅")) && (!recognition.contains("酒店"))
					&& (!recognition.contains("路"))
					&& (!recognition.contains("公交"))
					&& (!recognition.contains("从到"))
					&& (!recognition.contains("租"))) {
				String lat = locationMessage.getLocation_X();
				String lon = locationMessage.getLocation_Y();
				mapLocation = RobotService.getRobotResult(recognition, loc,
						lon, lat);
			}
			// 语音中没有出现餐厅，那么只需要具体的位置信息即可
			else if ((recognition.contains("酒店"))
					|| (recognition.contains("路"))
					|| (recognition.contains("公交"))
					|| (recognition.contains("从到"))
					|| ((recognition.contains("租")) && (!recognition
							.contains("餐厅")))) {
				mapLocation = RobotService.getRobotResult(recognition, loc,
						null, null);
			}
			String codeLocation = (String) mapLocation.get("code");
			logger.error("codeLocation:" + codeLocation);

			textLocation = (String) mapLocation.get("text");
			String urlLocation = (String) mapLocation.get("url");
			if (urlLocation != null) {
				stringBuffer.append(textLocation).append("\n")
						.append(urlLocation).append("\n");
			} else {
				stringBuffer.append("亲，没有查到相关信息");
			}
		}
		// 如果语音中出现新闻的关键字，那么调用封装好的类直接返回responseMessage
		/*if (recognition.contains("新闻")) {
			List<RobotNews> news = RobotService.newsList;

			NewsResponseMessage newsResponseMessage = new NewsResponseMessage();
			newsResponseMessage.setToUserName(fromUserName);
			newsResponseMessage.setFromUserName(toUserName);
			newsResponseMessage.setCreateTime(new Date().getTime());
			newsResponseMessage.setMsgType("news");
			newsResponseMessage.setFuncFlag(0);
			List<Article> articList = new ArrayList<Article>();
			for (RobotNews robotNews : news) {
				String article = robotNews.getArticle();
				String source = robotNews.getSource();
				String detailUrl = robotNews.getDetailurl();
				String icon = robotNews.getIcon();

				Article imageTextArticle = new Article();
				imageTextArticle.setTitle(article);
				imageTextArticle.setDescription(source);
				imageTextArticle.setUrl(detailUrl);
				imageTextArticle.setPicUrl(icon);
				articList.add(imageTextArticle);
			}
			newsResponseMessage.setArticleCount(articList.size());

			newsResponseMessage.setArticles(articList);

			responseMessage = MessageUtil.newsMessageToXml(newsResponseMessage);
			return responseMessage;
		}*/
		responseContent = stringBuffer.toString();
		responseMessage = MessageUtil.parseTextMessageToXml(
				textResponseMessage, responseMessage, responseContent);

		return responseMessage;
	}
}
