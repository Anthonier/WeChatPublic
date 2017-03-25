package com.qianjg.message.response;
/**
 * 语音消息
 * @author Administrator
 *
 */
public class VoiceMessage extends BaseResponseMessage {
	//语音
	private Voice Voice;

	/**
	 * @return the voice
	 */
	public Voice getVoice() {
		return Voice;
	}

	/**
	 * @param voice the voice to set
	 */
	public void setVoice(Voice voice) {
		Voice = voice;
	}
	
	
	
}
