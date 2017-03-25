package com.qianjg.message.response;

/**
 * 音乐消息
 * 
 * @author Administrator
 *
 */
public class MusicResponseMessage extends BaseResponseMessage {
	// 音乐
	private Music Music;

	/**
	 * @return the music
	 */
	public Music getMusic() {
		return Music;
	}

	/**
	 * @param music
	 *            the music to set
	 */
	public void setMusic(Music music) {
		Music = music;
	}

}
