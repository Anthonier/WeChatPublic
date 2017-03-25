package com.qianjg.message.response;

import java.util.List;

/**
 * 响应消息之图文消息
 * 
 * @author Administrator
 *
 */
public class NewsResponseMessage extends BaseResponseMessage {
	/**
	 * 文本消息
	 */
	// 图文消息个数，限制为10条以内
	private int ArticleCount;
	// 多条图文消息信息，默认第一个item为大图
	private List<Article> Articles;

	/**
	 * @return the articleCount
	 */
	public int getArticleCount() {
		return ArticleCount;
	}

	/**
	 * @param articleCount
	 *            the articleCount to set
	 */
	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}

	/**
	 * @return the articles
	 */
	public List<Article> getArticles() {
		return Articles;
	}

	/**
	 * @param articles the articles to set
	 */
	public void setArticles(List<Article> articles) {
		Articles = articles;
	}



}
