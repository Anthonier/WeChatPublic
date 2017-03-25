package com.qianjg.baidutranslet.service;

import com.qianjg.util.BaiduTransletUtil;
import com.qianjg.util.MessageUtil;


public class Translet {
	  // 在平台申请的APP_ID
    private static final String APP_ID = "平台申请的APP_ID";
    private static final String SECURITY_KEY = "APP_ID对应的密钥";
    private static final String FROM="auto";
	public static String translet(String query,String to){
		BaiduTransletUtil baiduTransletUtil=new BaiduTransletUtil(Translet.APP_ID,Translet.SECURITY_KEY);
		String dst=baiduTransletUtil.getTransResult(query, Translet.FROM, to);
		return dst;
	}
	public static String getTransletUsage(){
		StringBuffer buffer=new StringBuffer();
		buffer.append("欢迎使用Full Stack社区的智能翻译系统").append(MessageUtil.emoji(0x263A)).append("\n");
		buffer.append("最专业的平台，最优质的服务，全世界语言供您选择").append("\n");
		buffer.append("实例：翻译我有一个中国梦").append("\n");
		buffer.append("请回复任意数字选择您要翻译的目标语言").append("\n");
		buffer.append("01-翻译成中文").append("\n");
		buffer.append("02-翻译成英语").append("\n");
		buffer.append("03-翻译成粤语").append("\n");
		buffer.append("04-翻译成文言文").append("\n");
		buffer.append("05-翻译成日语").append("\n");
		buffer.append("06-翻译成韩语").append("\n");
		buffer.append("07-翻译成法语").append("\n");
		buffer.append("08-翻译成西班牙语").append("\n");
		buffer.append("09-翻译成泰语").append("\n");
		buffer.append("10-更多语言").append("\n");
		buffer.append("实例：02-我有一个中国梦").append("\n");
		buffer.append("如需更多语言，请回复10-").append("\n");
		return buffer.toString();
	}
	public static String getMoreTransletUsage(){
		StringBuffer buffer=new StringBuffer();
		buffer.append("11-翻译成阿拉伯语").append("\n");
		buffer.append("12-翻译成俄语").append("\n");
		buffer.append("13-翻译成葡萄牙语").append("\n");
		buffer.append("14-翻译成德语").append("\n");
		buffer.append("15-翻译成意大利语").append("\n");
		buffer.append("16-翻译成希腊语").append("\n");
		buffer.append("17-翻译成荷兰语").append("\n");
		buffer.append("18-翻译成波兰语").append("\n");
		buffer.append("19-翻译成保加利亚语").append("\n");
		buffer.append("20-更多语言").append("\n");
		return buffer.toString();
	}
	public static String getLastTransletUsage(){
		StringBuffer buffer=new StringBuffer();
		buffer.append("21-翻译成保加利亚语").append("\n");
		buffer.append("22-翻译成丹麦语").append("\n");
		buffer.append("23-翻译成芬兰语").append("\n");
		buffer.append("24-翻译成捷克语").append("\n");
		buffer.append("25-翻译成罗马尼亚语").append("\n");
		buffer.append("26-翻译成斯洛文尼亚语").append("\n");
		buffer.append("27-翻译成瑞典语").append("\n");
		buffer.append("28-翻译成匈牙利语").append("\n");
		buffer.append("29-翻译成繁体中文").append("\n");
		return buffer.toString();
	}
	
	
}
