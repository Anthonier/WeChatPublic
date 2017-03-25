package com.qianjg.test;

import java.io.UnsupportedEncodingException;

public class testString {
	public static void main(String[] args) {
//		String string="11-我要翻译";
//		String keyword=string.replaceAll("^.*-", "").trim();
//		System.out.println(keyword);
//		test1();
		test2();
	}
	public static void test1(){
		String content="11-翻译";
		String key=content.substring(0, 2);
		System.out.println(key);
	}
	public static void test2(){
		String string="微信二次开发";
		try {
			String result=new String(string.getBytes(), "utf-8");
			System.out.println(result);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
