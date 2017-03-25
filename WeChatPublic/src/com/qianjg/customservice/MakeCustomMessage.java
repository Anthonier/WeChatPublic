package com.qianjg.customservice;

import java.util.List;

import com.qianjg.message.response.Article;
import com.qianjg.message.response.Music;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 组装发送客服消息需要的POST特定格式的JSON数据包
 * @author Administrator
 *
 */
public class MakeCustomMessage {
	/**
	 * 组装文本客服消息
	 * @param openId  消息发送对象
	 * @param content 文本
	 * @return String 字符串
	 */
	public static String makeTextCustomMessage(String openId,String content){
		//对消息内容中的双引号进行转义
		content.replace("\"", "\\\"");
		String jsonMsg="{\"touser\":\"%s\",\"msgtype\":\"text\",\"text\":{\"content\":\"%s\"}}";
		//新字符串使用本地语言环境，制定字符串格式和参数生成格式化的新字符串。 %s代表一个字符串类型
		return String.format(jsonMsg,openId,content);
	}
	/**
	 * 组装图片客服消息
	 * @param openId  开放ID
	 * @param mediaId 多媒体ID
	 * @return String 字符串
	 */
	public static String makeImageCustomMessage(String openId,String mediaId){
		String jsonMsg="{\"touser\":\"%s\",\"msgtype\":\"image\",\"image\":{\"media_id\":\"%s\"}}";
		//新字符串使用本地语言环境，制定字符串格式和参数生成格式化的新字符串。 %s代表一个字符串类型
		return String.format(jsonMsg,openId,mediaId);
	}
	/**
	 * 组装声音客服消息 
	 * @param openId  开放ID
	 * @param mediaId 多媒体ID
	 * @return String 字符串
	 */
	 public static String makeVoiceCustomMessage(String openId, String mediaId)
	  {
	    String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"voice\",\"voice\":{\"media_id\":\"%s\"}}";
	    return String.format(jsonMsg, new Object[] { openId, mediaId });
	  }
	  /**
	   * 组装视频客服消息
	   * @param openId  		开放ID
	   * @param mediaId 		多媒体ID
	   * @param thumbMediaId	缩略ID
	   * @return String			字符串
	   */
	  public static String makeVideoCustomMessage(String openId, String mediaId, String thumbMediaId)
	  {
	    String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"video\",\"video\":{\"media_id\":\"%s\",\"thumb_media_id\":\"%s\"}}";
	    return String.format(jsonMsg, new Object[] { openId, mediaId, thumbMediaId });
	  }
	  /**
	   * 组装音乐客服消息
	   * @param openId	开放ID
	   * @param music	影音
	   * @return String 字符串
	   */
	  public static String makeMusicCustomMessage(String openId, Music music)
	  {
	    String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"music\",\"music\":\"%s\"}}";
	    jsonMsg = String.format(jsonMsg, new Object[] { openId, JSONObject.fromObject(music).toString() });
	    jsonMsg = jsonMsg.replace("thumbmediaid", "thumb_media_id");
	    return jsonMsg;
	  }
	  /**
	   * 组装新闻客服消息
	   * @param openId      开放ID
	   * @param articleList 文章集合
	   * @return String		字符串
	   */
	  public static String makeNewsCustomMessage(String openId, List<Article> articleList)
	  {
	    String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"news\",\"news\":{\"articles\":\"%s\"}";
	    jsonMsg = String.format(jsonMsg, new Object[] { openId, JSONArray.fromObject(articleList).toString().replaceAll("\"", "\\\"") });
	    
	    jsonMsg.replace("picUrl", "picurl");
	    return jsonMsg;
	  }
	
}
