package com.qianjg.message.response;

/**
 * 图文model
 * 
 * @author Administrator
 *
 */
public class Article {
	// 图文消息名称
	private String Title;
	// 图文消息描述
	private String Description;
	// 图文链接，支持JPG,PNG格式，较好的效果为大图640*320，小图80*80，限制图片链接的域名需要与开发者填写的基本资料Url基本一致
	private String PicUrl;
	// 点击图文消息跳转链接
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
		return null == Description ? "" : Description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		Description = description;
	}

	/**
	 * @return the picUrl
	 */
	public String getPicUrl() {
		return null == PicUrl ? "" : PicUrl;
	}

	/**
	 * @param picUrl
	 *            the picUrl to set
	 */
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return null == Url ? "" : Url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		Url = url;
	}

}
