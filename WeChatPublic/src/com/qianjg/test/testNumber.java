package com.qianjg.test;

public class testNumber {
	// math在0-28之间
	public static double number = Math.random() * 28;
	// 在1-29之间
	public static int num = (int) Math.ceil(number);
	public static String key = num + "";

	public static void main(String[] args) {
		System.out.println(key);
	}
}
