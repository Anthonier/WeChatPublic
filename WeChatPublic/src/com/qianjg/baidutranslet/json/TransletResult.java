package com.qianjg.baidutranslet.json;

import java.util.List;

/**
 * 创建与百度翻译API返回的JSON相对应的Java类
 * 调用百度翻译api查询结果
 * @author Administrator
 *
 */
public class TransletResult {
	//实际采用的源语言
	private String from;
	//实际采用的目标语言
	private String to;
	//结果体
	private List<ResultPair> trans_result;
	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}
	/**
	 * @param from the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}
	/**
	 * @return the to
	 */
	public String getTo() {
		return to;
	}
	/**
	 * @param to the to to set
	 */
	public void setTo(String to) {
		this.to = to;
	}
	/**
	 * @return the trans_result
	 */
	public List<ResultPair> getTrans_result() {
		return trans_result;
	}
	/**
	 * @param trans_result the trans_result to set
	 */
	public void setTrans_result(List<ResultPair> trans_result) {
		this.trans_result = trans_result;
	}
	
	
	
}
