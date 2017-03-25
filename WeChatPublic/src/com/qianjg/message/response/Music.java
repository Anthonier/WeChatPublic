package com.qianjg.message.response;

/**
 * Music类
 * 
 * @author Administrator
 *
 */
public class Music {
	/**
	 * 音乐模型
	 */
	// 音乐名称
	private String Title;
	// 音乐描述
	private String Description;
	// 音乐连接
	private String musicUrl;
	// 高质量音乐链接，WIFI环境优先使用该链接播放音乐
	private String HQmusicUrl;

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
	 * @return the musicUrl
	 */
	public String getMusicUrl() {
		return musicUrl;
	}

	/**
	 * @param musicUrl
	 *            the musicUrl to set
	 */
	public void setMusicUrl(String musicUrl) {
		this.musicUrl = musicUrl;
	}

	/**
	 * @return the hQmusicUrl
	 */
	public String getHQmusicUrl() {
		return HQmusicUrl;
	}

	/**
	 * @param hQmusicUrl
	 *            the hQmusicUrl to set
	 */
	public void setHQmusicUrl(String hQmusicUrl) {
		HQmusicUrl = hQmusicUrl;
	}

}
