package com.qianjg.msgtype.response;

import com.qianjg.baidutranslet.service.Translet;
import com.qianjg.message.request.LocationRequestMessage;
import com.qianjg.message.response.Article;
import com.qianjg.message.response.NewsResponseMessage;
import com.qianjg.message.response.TextResponseMessage;
import com.qianjg.pojomap.BaiduPlace;
import com.qianjg.pojomap.UserLocation;
import com.qianjg.pojorobot.RobotNews;
import com.qianjg.robot.RobotService;
import com.qianjg.util.BaiduMapUtil;
import com.qianjg.util.MessageUtil;
import com.qianjg.util.MySQLUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

/**
 * 用户发送文本消息 响应文本、图文消息给用户
 * 
 * @author ThinkQJG
 *
 */
public class TextResponse {
	private static Logger logger = Logger.getLogger(TextResponse.class);
	private static String key = null;
	private static String to = null;
	private static String keyword = null;

	public static String textMessage(String content, String createTime,
			String responseContent, String fromUserName, String toUserName,
			TextResponseMessage textResponseMessage, HttpServletRequest request) {
		String responseMessage = null;
		if (MessageUtil.isQqFace(content)) {
			responseContent = content;
			responseMessage = MessageUtil.parseTextMessageToXml(
					textResponseMessage, responseMessage, responseContent);
			return responseMessage;
		}
		/*
		 * if (("推荐博客1".equals(content)) || ("推荐博客2".equals(content)) ||
		 * ("推荐博客3".equals(content)) || ("推荐博客4".equals(content)) ||
		 * ("推荐博客5").equals(content)) { responseMessage =
		 * ImageTextResponse.responseImageTextMessage( fromUserName, toUserName,
		 * content); return responseMessage; }
		 */

		if (content.startsWith("翻译")) {
			responseContent = Translet.getTransletUsage();
			responseMessage = MessageUtil.parseTextMessageToXml(
					textResponseMessage, responseMessage, responseContent);

			return responseMessage;
		}
		if ((content.startsWith("01-")) || (content.startsWith("02-"))
				|| (content.startsWith("03-")) || (content.startsWith("04-"))
				|| (content.startsWith("05-")) || (content.startsWith("06-"))
				|| (content.startsWith("07-")) || (content.startsWith("08-"))
				|| (content.startsWith("09-")) || (content.startsWith("10-"))
				|| (content.startsWith("11-")) || (content.startsWith("12-"))
				|| (content.startsWith("13-")) || (content.startsWith("14-"))
				|| (content.startsWith("15-")) || (content.startsWith("16-"))
				|| (content.startsWith("17-")) || (content.startsWith("18-"))
				|| (content.startsWith("19-")) || (content.startsWith("20-"))
				|| (content.startsWith("21-")) || (content.startsWith("22-"))
				|| (content.startsWith("23-")) || (content.startsWith("24-"))
				|| (content.startsWith("25-")) || (content.startsWith("26-"))
				|| (content.startsWith("27-")) || (content.startsWith("28-"))
				|| (content.startsWith("29-"))) {
			keyword = content.replaceAll("^.*-", "");

			key = content.substring(0, 2);
			logger.error("content=========" + content);
			logger.error("key=============" + key);
			if (key.equals("01")) {
				to = "zh";
				responseContent = Translet.translet(keyword, to);
				logger.error("SwitchResponseContent" + responseContent);
			} else if (key.equals("02")) {
				to = "en";
				responseContent = Translet.translet(keyword, to);
				logger.error("SwitchResponseContentEnglish" + responseContent);
			} else if (key.equals("03")) {
				to = "yue";
				responseContent = Translet.translet(keyword, to);
			} else if (key.equals("04")) {
				to = "wyw";
				responseContent = Translet.translet(keyword, to);
			} else if (key.equals("05")) {
				to = "jp";
				responseContent = Translet.translet(keyword, to);
			} else if (key.equals("06")) {
				to = "kor";
				responseContent = Translet.translet(keyword, to);
			} else if (key.equals("07")) {
				to = "fra";
				responseContent = Translet.translet(keyword, to);
			} else if (key.equals("08")) {
				to = "spa";
				responseContent = Translet.translet(keyword, to);
			} else if (key.equals("09")) {
				to = "th";
				responseContent = Translet.translet(keyword, to);
			} else if (key.equals("10")) {
				responseContent = Translet.getMoreTransletUsage();
			} else if (key.equals("11")) {
				to = "ara";
				responseContent = Translet.translet(keyword, to);
			} else if (key.equals("12")) {
				to = "ru";
				responseContent = Translet.translet(keyword, to);
			} else if (key.equals("13")) {
				to = "pt";
				responseContent = Translet.translet(keyword, to);
			} else if (key.equals("14")) {
				to = "de";
				responseContent = Translet.translet(keyword, to);
			} else if (key.equals("15")) {
				to = "it";
				responseContent = Translet.translet(keyword, to);
			} else if (key.equals("16")) {
				to = "el";
				responseContent = Translet.translet(keyword, to);
			} else if (key.equals("17")) {
				to = "nl";
				responseContent = Translet.translet(keyword, to);
			} else if (key.equals("18")) {
				to = "pl";
				responseContent = Translet.translet(keyword, to);
			} else if (key.equals("19")) {
				to = "bul";
				responseContent = Translet.translet(keyword, to);
			} else if (key.equals("20")) {
				responseContent = Translet.getLastTransletUsage();
			} else if (key.equals("21")) {
				to = "est";
				responseContent = Translet.translet(keyword, to);
			} else if (key.equals("22")) {
				to = "dan";
				responseContent = Translet.translet(keyword, to);
			} else if (key.equals("23")) {
				to = "fin";
				responseContent = Translet.translet(keyword, to);
			} else if (key.equals("24")) {
				to = "cs";
				responseContent = Translet.translet(keyword, to);
			} else if (key.equals("25")) {
				to = "rom";
				responseContent = Translet.translet(keyword, to);
			} else if (key.equals("26")) {
				to = "slo";
				responseContent = Translet.translet(keyword, to);
			} else if (key.equals("27")) {
				to = "swe";
				responseContent = Translet.translet(keyword, to);
			} else if (key.equals("28")) {
				to = "hu";
				responseContent = Translet.translet(keyword, to);
			} else if (key.equals("29")) {
				to = "cht";
				responseContent = Translet.translet(keyword, to);
			}
			responseMessage = MessageUtil.parseTextMessageToXml(
					textResponseMessage, responseMessage, responseContent);

			return responseMessage;
		}

		/*
		 * Map<String, String> map = null; StringBuffer stringBuffer = null; if
		 * ((!content.contains("餐厅")) && (!content.contains("酒店")) &&
		 * (!content.contains("路")) && (!content.contains("公交")) &&
		 * (!content.contains("从到")) && (!content.contains("租"))) { map =
		 * RobotService.getRobotResult(content, null, null, null); stringBuffer
		 * = new StringBuffer(); String code = (String) map.get("code");
		 * logger.error("code:" + code); String robotText = (String)
		 * map.get("text"); stringBuffer.append(robotText); if
		 * ((content.contains("图片")) || (content.contains("航班")) ||
		 * (content.contains("车")) || (content.contains("高铁")) ||
		 * (content.contains("电影")) || (content.contains("股票"))) { String url =
		 * (String) map.get("url"); if (url != null) {
		 * stringBuffer.append(url).append("\n"); } else {
		 * stringBuffer.append("亲，您再说的具体一些"); } } responseContent =
		 * stringBuffer.toString(); responseMessage =
		 * MessageUtil.parseTextMessageToXml( textResponseMessage,
		 * responseMessage, responseContent);
		 * 
		 * return responseMessage; } String textLocation; if
		 * ((content.contains("餐厅")) || (content.contains("酒店")) ||
		 * (content.contains("路")) || (content.contains("公交")) ||
		 * (content.contains("从到")) || (content.contains("租"))) {
		 * LocationRequestMessage locationMessage = new
		 * LocationRequestMessage(); String loc = locationMessage.getLabel();
		 * Map<String, String> mapLocation = null; stringBuffer = new
		 * StringBuffer(); if ((content.contains("餐厅")) &&
		 * (!content.contains("酒店")) && (!content.contains("路")) &&
		 * (!content.contains("公交")) && (!content.contains("从到")) &&
		 * (!content.contains("租"))) { String lat =
		 * locationMessage.getLocation_X(); String lon =
		 * locationMessage.getLocation_Y(); mapLocation =
		 * RobotService.getRobotResult(content, loc, lon, lat); } else if
		 * ((content.contains("酒店")) || (content.contains("路")) ||
		 * (content.contains("公交")) || (content.contains("从到")) ||
		 * ((content.contains("租")) && (!content.contains("餐厅")))) { mapLocation
		 * = RobotService.getRobotResult(content, loc, null, null); } String
		 * codeLocation = (String) mapLocation.get("code");
		 * logger.error("codeLocation:" + codeLocation);
		 * 
		 * textLocation = (String) mapLocation.get("text"); String urlLocation =
		 * (String) mapLocation.get("url"); if (urlLocation != null) {
		 * stringBuffer.append(textLocation).append("\n")
		 * .append(urlLocation).append("\n"); } else {
		 * stringBuffer.append("亲，没有查到相关信息"); } responseContent =
		 * stringBuffer.toString(); responseMessage =
		 * MessageUtil.parseTextMessageToXml( textResponseMessage,
		 * responseMessage, responseContent);
		 * 
		 * return responseMessage; }
		 */
		if (content.contains("焦点新闻")) {
			Map<String, String> map = RobotService.getRobotResult(content,
					null, null, null);
			List<RobotNews> news = RobotService.newsList;
			NewsResponseMessage newsResponseMessage = new NewsResponseMessage();
			newsResponseMessage.setToUserName(fromUserName);
			newsResponseMessage.setFromUserName(toUserName);
			newsResponseMessage.setCreateTime(new Date().getTime());
			newsResponseMessage.setMsgType("news");
			newsResponseMessage.setFuncFlag(0);
			List<Article> articList = new ArrayList<Article>();

			int count = 0; // 定义个变量计算图文消息的个数，图文消息要控制在8条以内，否则无响应
			for (RobotNews robotNews : news) {
				if (count < 8) {
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
					count++;
				} else {
					break;
				}

			}
			System.out.println("articleList" + articList);
			newsResponseMessage.setArticleCount(articList.size());

			newsResponseMessage.setArticles(articList);

			responseMessage = MessageUtil.newsMessageToXml(newsResponseMessage);
			return responseMessage;
		}

		// 如果搜索内容为附近
		if (content.equals("附近")) {
			responseContent = getUsage();
		}
		// 如果搜索内容以附近开头
		if (content.startsWith("附近")) {
			String keyWord = content.replaceAll("附近", "").trim();
			// 获取用户最后一次发送的地理位置
			UserLocation location = MySQLUtil.getLastLocation(request,
					fromUserName);
			System.out.println("location-in textResponse:" + location);
			// 未获取到
			if (null == location) {
				responseContent = getUsage();
			} else {
				// 根据转换后（纠偏）的坐标搜索周边POI
				try {
					List<BaiduPlace> placeList = BaiduMapUtil.searchPlace(
							keyWord, location.getBd09Lng(),
							location.getBd09Lat());
					System.out.println("placeList:" + placeList);
					// 未搜索到POI
					if (null == placeList || 0 == placeList.size()) {
						responseContent = String.format(
								"/难过伤心，您发送的位置消息未能搜索到\"%s\"消息!", keyWord);
					} else {
						List<Article> articleList = BaiduMapUtil
								.makeArticleList(placeList,
										location.getBd09Lng(),
										location.getBd09Lat());
						// 回复图文消息
						NewsResponseMessage newsResponseMessage = new NewsResponseMessage();
						newsResponseMessage.setToUserName(fromUserName);
						newsResponseMessage.setFromUserName(toUserName);
						newsResponseMessage.setCreateTime(new Date().getTime());
						newsResponseMessage
								.setMsgType(MessageUtil.RESPONSE_MESSAGE_TYPE_NEWS);
						newsResponseMessage.setArticles(articleList);
						newsResponseMessage.setArticleCount(articleList.size());
						responseMessage = MessageUtil
								.newsMessageToXml(newsResponseMessage);
						return responseMessage;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		responseMessage = MessageUtil.parseTextMessageToXml(
				textResponseMessage, responseMessage, responseContent);

		return responseMessage;
	}

	/**
	 * 周边搜索提示语
	 * 
	 * @return String
	 */
	public static String getUsage() {
		// TODO Auto-generated method stub
		StringBuffer buffer = new StringBuffer();
		buffer.append("您是否有过出门在外四处寻找ATM或厕所的经历？").append("\n");
		buffer.append("您是否有过出差或旅游在外四处寻找美食的经历？").append("\n");
		buffer.append("周边搜索为您出行保驾护航，为您提供专业的周边生活指南").append("\n\n");
		buffer.append("周边搜索只用指南").append("\n");
		buffer.append("1.发送地理位置").append("\n");
		buffer.append("2.指定关键词搜素").append("\n");
		buffer.append("格式:附近+关键词\n 例如：附近银行、附近美食、附近酒店");
		return buffer.toString();
	}

}
