package com.qianjg.message.request;

/**
 * 请求消息之文本消息
 * 
 * @author Administrator
 *
 */
public class TextRequestMessage extends BaseRequestMessage {
	private String Content;

	/**
	 * @return the content
	 */
	public String getContent() {
		return Content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.Content = content;
	}

}
