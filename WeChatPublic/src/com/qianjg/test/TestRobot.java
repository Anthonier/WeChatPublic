package com.qianjg.test;

import com.qianjg.pojorobot.RobotNews;
import com.qianjg.robot.RobotService;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;

public class TestRobot {
	public static void main(String[] args) {
		testRobotNews();
	//	testRobotLocation2();
	}

	private static void testRobot() {
		Map<String, String> map = RobotService.getRobotResult("讲个笑话", null, null, null);
		String code = (String) map.get("code");
		String text = (String) map.get("text");
		System.out.println("code" + code + "text:" + text);
	}

	public static void testRobotNews() {
		Map<String, String> map = RobotService.getRobotResult("焦点新闻", null, null, null);
		List<RobotNews> robotList = null;
		String code = (String) map.get("code");
		String text = (String) map.get("text");
		String url = (String) map.get("url");
		List<RobotNews> news = RobotService.newsList;
		
		int count=0;
		for (RobotNews robotNews : news) {
			if(count<8){
				String article = robotNews.getArticle();
				String detailUrl = robotNews.getDetailurl();
				String icon = robotNews.getIcon();
				String source = robotNews.getSource();
				count++;
				System.out.println(code + text + article + detailUrl + icon + source);
			}else{
				break;
			}
		}
	}

	public static void testRobotLocation1() {
		Map<String, String> map = RobotService.getRobotResult("上地华联附近的餐厅", "北京市海淀区信息路28号", "119.239293", "39.45492");
		String code = (String) map.get("code");
		String text = (String) map.get("text");
		String url = (String) map.get("url");
		System.out.println("code" + code + "text:" + text + "url" + url);
	}

	public static void testRobotLocation2() {
		Map<String, String> map = RobotService.getRobotResult("上地信息路堵吗", "北京市海淀区信息路28号", null, null);
		String code = (String) map.get("code");
		String text = (String) map.get("text");
		String url = (String) map.get("url");
		System.out.println("code" + code + "text:" + text + "url" + url);
	}
}
