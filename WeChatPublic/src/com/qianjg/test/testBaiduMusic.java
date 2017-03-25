package com.qianjg.test;

import com.qianjg.test.BaiduMusicService;
import com.qianjg.message.response.Music;

public class testBaiduMusic {
	public static void main(String[] args) {
		Music music=BaiduMusicService.searchMusic("演员","薛之谦");
		System.out.println("音乐名称："+music.getTitle());
		System.out.println("音乐描述："+music.getDescription());
		System.out.println("普通品质链接："+music.getMusicUrl());
		System.out.println("高品质链接："+music.getHQmusicUrl());
	}
}
