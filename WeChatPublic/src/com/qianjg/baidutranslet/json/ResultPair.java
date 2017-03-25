package com.qianjg.baidutranslet.json;
/**
 * 结果对
 * 类名可以任意取，但是成员变量的名字应于翻译 API 返回的 JSON 字符串中
 * 的属性名保持一致，否则将 JSON 转换成 TranslateResult 对象时会报错。
 * @author Administrator
 *
 */
public class ResultPair {
	//原文
	private String src;
	//译文
	private String dst;
	/**
	 * @return the src
	 */
	public String getSrc() {
		return src;
	}
	/**
	 * @param src the src to set
	 */
	public void setSrc(String src) {
		this.src = src;
	}
	/**
	 * @return the dst
	 */
	public String getDst() {
		return dst;
	}
	/**
	 * @param dst the dst to set
	 */
	public void setDst(String dst) {
		this.dst = dst;
	}
	
	
}
