package com.qianjg.message.request;

/**
 * 请求消息之链接消息
 * 
 * @author Administrator
 *
 */
public class LinkRequestMessage extends BaseRequestMessage {
	// 消息的标题
	private String Title;
	// 消息的描述
	private String Description;
	// 消息的链接
	private String Url;

	/**
	 * @return the title
	 */
	public String getTitle() {
		return Title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		Title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return Description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		Description = description;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return Url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		Url = url;
	}

}
