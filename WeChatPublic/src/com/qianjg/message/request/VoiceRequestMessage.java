package com.qianjg.message.request;

/**
 * 请求消息之语音消息
 * 
 * @author Administrator
 *
 */
public class VoiceRequestMessage extends BaseRequestMessage {
	// 媒体ID
	private String MediaId;
	// 语音的格式
	private String Format;
	// 语音识别结果，UTF8编码
	private String Recognition;
	/**
	 * @return the recognition
	 */
	public String getRecognition() {
		return Recognition;
	}

	/**
	 * @param recognition the recognition to set
	 */
	public void setRecognition(String recognition) {
		Recognition = recognition;
	}

	/**
	 * @return the mediaId
	 */
	public String getMediaId() {
		return MediaId;
	}

	/**
	 * @param mediaId
	 *            the mediaId to set
	 */
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	/**
	 * @return the format
	 */
	public String getFormat() {
		return Format;
	}

	/**
	 * @param format
	 *            the format to set
	 */
	public void setFormat(String format) {
		Format = format;
	}

}
