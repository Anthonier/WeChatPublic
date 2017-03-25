package com.qianjg.message.request;

/**
 * 请求消息之图片消息
 * 
 * @author Administrator
 *
 */
public class ImageRequestMessage extends BaseRequestMessage {

	// 图片链接
	private String PicUrl;

	/**
	 * @return the PicUrlS
	 */
	public String getPicUrl() {
		return PicUrl;
	}

	/**
	 * @param picUrl
	 *            the picUrl to set
	 */
	public void setPicUrl(String picUrl) {
		this.PicUrl = picUrl;
	}

}
