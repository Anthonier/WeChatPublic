package com.qianjg.msgtype.response;

import com.qianjg.message.response.Article;
import com.qianjg.message.response.NewsResponseMessage;
import com.qianjg.util.MessageUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * 图文消息响应
 * 
 * @author ThinkQJG
 *
 */
public class ImageTextResponse {
	private static Logger logger = Logger.getLogger(ImageTextResponse.class);

	public static String responseImageTextMessage(String fromUserName,
			String toUserName, String content) {
		String responseMessage = null;

		NewsResponseMessage newsResponseMessage = new NewsResponseMessage();
		newsResponseMessage.setToUserName(fromUserName);
		newsResponseMessage.setFromUserName(toUserName);
		newsResponseMessage.setCreateTime(new Date().getTime());
		newsResponseMessage.setMsgType(MessageUtil.RESPONSE_MESSAGE_TYPE_NEWS);
		newsResponseMessage.setFuncFlag(0);
		List<Article> articList = new ArrayList();
		if ("推荐博客1".equals(content)) {
			Article article = new Article();
			article.setTitle("注册Google账号");
			article.setDescription("相信很多人在注册谷歌账户时都会遇到手机号无法验证的问题。通过以下两个简单的步骤即可轻松解决.");
			article.setUrl("http://blog.csdn.net/geekseattle/article/details/52982146");
			article.setPicUrl("http://img.blog.csdn.net/20161031143022205?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQv/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center");
			articList.add(article);

			newsResponseMessage.setArticleCount(1);

			newsResponseMessage.setArticles(articList);

			responseMessage = MessageUtil.newsMessageToXml(newsResponseMessage);
		} else if ("推荐博客2".equals(content)) {
			Article article = new Article();
			article.setTitle("注册Google账号");

			article.setDescription("/::D"
					+ MessageUtil.emoji(128690)
					+ ""
					+ "相信很多人在注册谷歌账户时都会遇到手机号无法验证的问题。\n\n通过以下两个简单的步骤即可轻松解决.1 注册电子邮箱时一定要用个人邮箱  比如 1263639596@qq.com   qjg@qianjg.com.\n\n注册时 手机号默认不填  位置 默认美国.验证账户时  默认美国  手机号形式  类如 +86 177 1265 5471");

			article.setPicUrl("");
			article.setUrl("http://blog.csdn.net/geekseattle/article/details/52982146");
			articList.add(article);
			newsResponseMessage.setArticleCount(articList.size());
			newsResponseMessage.setArticles(articList);
			responseMessage = MessageUtil.newsMessageToXml(newsResponseMessage);
		} else if ("推荐博客3".equals(content)) {
			Article article1 = new Article();
			article1.setTitle("注册Google账号");
			article1.setDescription("");
			article1.setPicUrl("http://img.blog.csdn.net/20161031143022205?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQv/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center");
			article1.setUrl("http://blog.csdn.net/geekseattle/article/details/52982146");

			Article article2 = new Article();
			article2.setTitle("JAVA学习总结之JVM概述");
			article2.setDescription("");
			article2.setPicUrl("http://images.cnitblog.com/i/288799/201405/281630330728961.jpg");
			article2.setUrl("http://blog.csdn.net/my_confesser/article/details/52980025");

			Article article3 = new Article();
			article3.setTitle("安卓7.1 新特性Shortcut");
			article3.setDescription("");
			article3.setPicUrl("http://img.blog.csdn.net/20161030190725976");
			article3.setUrl("http://blog.csdn.net/qfanmingyiq/article/details/52973484");

			articList.add(article1);
			articList.add(article2);
			articList.add(article3);
			newsResponseMessage.setArticleCount(articList.size());
			newsResponseMessage.setArticles(articList);
			responseMessage = MessageUtil.newsMessageToXml(newsResponseMessage);
		} else if ("推荐播客4".equals(content)) {
			Article article1 = new Article();
			article1.setTitle("注册Google账号");
			article1.setDescription("");

			article1.setPicUrl("");
			article1.setUrl("http://blog.csdn.net/geekseattle/article/details/52982146");

			Article article2 = new Article();
			article2.setTitle("Android TV开发总结（六）构建一个TV app的直播节目实例");
			article2.setDescription("");
			article2.setPicUrl("http://img.blog.csdn.net/20161029191642389");
			article2.setUrl("http://blog.csdn.net/hejjunlin/article/details/52966319");

			Article article3 = new Article();
			article3.setTitle("Android平台安全概念篇");
			article3.setDescription("");
			article3.setPicUrl("http://img.blog.csdn.net/20161023152958653");
			article3.setUrl("http://blog.csdn.net/u011617742/article/details/52901906");

			Article article4 = new Article();
			article4.setTitle(" Android自定义View之仿QQ侧滑菜单实现");
			article4.setDescription("");
			article4.setPicUrl("http://img.blog.csdn.net/20161028172707783");
			article4.setUrl("http://blog.csdn.net/bingjianit/article/details/52905754");
			articList.add(article1);
			articList.add(article2);
			articList.add(article3);
			articList.add(article4);

			newsResponseMessage.setArticleCount(articList.size());
			newsResponseMessage.setArticles(articList);
			responseMessage = MessageUtil.newsMessageToXml(newsResponseMessage);
		} else if ("推荐播客5".equals(content)) {
			Article article1 = new Article();
			article1.setTitle("排序算法 基于Javascript");
			article1.setDescription("");
			article1.setPicUrl("http://img.blog.csdn.net/20161025200702901");
			article1.setUrl("http://blog.csdn.net/faremax/article/details/52926192");

			Article article2 = new Article();
			article2.setTitle("Android线程池框架下的线程池策略");
			article2.setDescription("");
			article2.setPicUrl("http://img.blog.csdn.net/20161027184130025?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQv/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center");
			article2.setUrl("http://blog.csdn.net/roshen_android/article/details/52948480");

			Article article3 = new Article();
			article3.setTitle(" Android屏幕适配");
			article3.setDescription("");

			article3.setPicUrl("");
			article3.setUrl("http://blog.csdn.net/mynameishuangshuai/article/details/52925848");
			articList.add(article1);
			articList.add(article2);
			articList.add(article3);

			newsResponseMessage.setArticleCount(articList.size());
			newsResponseMessage.setArticles(articList);
			responseMessage = MessageUtil.newsMessageToXml(newsResponseMessage);
		}
		return responseMessage;
	}
}
