package com.qianjg.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import com.qianjg.baidutranslet.service.Translet;

public class testTranslet {
	public static void main(String[] args) throws IOException {
		String sendUrl = "http://api.fanyi.baidu.com/api/trans/vip/translate?sign=52a26c58fcc9960d822cf4e10be2fab0&to=en&q=%E6%B8%A9%E6%9A%96&appid=你的APPID&from=auto&salt=1478485365022";
		URL uri;
		try {
			uri = new URL(sendUrl);
			HttpURLConnection conn = (HttpURLConnection) uri.openConnection();

			conn.setConnectTimeout(1000); // 设置响应超时
			conn.setRequestMethod("GET");
			int statusCode = conn.getResponseCode();
			if (statusCode != HttpURLConnection.HTTP_OK) {
				System.out.println("Http错误码：" + statusCode);

			}

			// 读取服务器的数据
			InputStream is = conn.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"));
			StringBuilder builder = new StringBuilder();
			String line = null;
			while ((line = br.readLine()) != null) {
				builder.append(line);
			}

			String text = builder.toString();
			System.out.println(text);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 创建URL对象

	}
}
