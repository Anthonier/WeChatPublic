package com.qianjg.robot;

import com.google.gson.Gson;
import com.qianjg.pojorobot.Robot;
import com.qianjg.pojorobot.RobotNews;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 * 图灵机器人接口调用
 */
public class RobotService {
	protected static final int SOCKET_TIMEOUT = 10000;
	private static Logger logger = Logger.getLogger(RobotService.class);
	public static List<RobotNews> newsList = new ArrayList<RobotNews>();

	public static String httpRequest(String info, String loc, String lon,
			String lat) {
		String requestUrl = null;
		try {
			if ((info != null) && (loc == null) && (lon == null)
					&& (lat == null)) {
				requestUrl = "http://www.tuling123.com/openapi/api?key=KEY&info=INFO&userid=USERID";
				requestUrl = requestUrl.replace("KEY",
						"图灵平台的api_key");
				requestUrl = requestUrl.replace("USERID", "你的USERID");//USERID为上下文字段，用于开发者给自己的用户分配的唯一标志（对应自己的每一个用户）
				requestUrl = requestUrl.replace("INFO",
						URLEncoder.encode(info, "UTF-8"));
			} else if ((info != null) && (loc != null) && (lon == null)
					&& (lat == null)) {
				requestUrl = "http://www.tuling123.com/openapi/api?key=KEY&info=INFO&userid=USERID&loc=LOC";
				requestUrl = requestUrl.replace("KEY",
						"图灵平台的api_key");
				requestUrl = requestUrl.replace("USERID", "你的USERID");
				requestUrl = requestUrl.replace("INFO",
						URLEncoder.encode(info, "UTF-8"));
				requestUrl = requestUrl.replace("LOC",
						URLEncoder.encode(loc, "UTF-8"));
			} else if ((info != null) && (loc != null) && (lon != null)
					&& (lat != null)) {
				requestUrl = "http://www.tuling123.com/openapi/api?key=KEY&info=INFO&userid=USERID&loc=LOC&lon=LON&lat=LAT";
				requestUrl = requestUrl.replace("KEY",
						"图灵平台的api_key");
				requestUrl = requestUrl.replace("USERID", "你的USERID");
				requestUrl = requestUrl.replace("INFO",
						URLEncoder.encode(info, "UTF-8"));
				requestUrl = requestUrl.replace("LON", lon);
				requestUrl = requestUrl.replace("LAT", lat);
				requestUrl = requestUrl.replace("LOC",
						URLEncoder.encode(loc, "UTF-8"));
			}
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		StringBuffer buffer = new StringBuffer();
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url
					.openConnection();

			httpURLConnection.setDoInput(true);

			httpURLConnection.setDoOutput(true);

			httpURLConnection.setUseCaches(false);

			httpURLConnection.setInstanceFollowRedirects(true);
			httpURLConnection.setRequestProperty("accept", "*/*");

			httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
			httpURLConnection.setRequestProperty("Charset", "UTF-8");
			httpURLConnection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");

			httpURLConnection.setConnectTimeout(10000);
			httpURLConnection.setRequestMethod("GET");

			httpURLConnection.connect();

			int statusCode = httpURLConnection.getResponseCode();
			logger.error(Integer.valueOf(statusCode));
			if (statusCode != 200) {
				logger.error("http错误码：" + statusCode);
			}
			InputStream inputStream = httpURLConnection.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(inputStream, "UTF-8"));
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStream.close();
			inputStream = null;
			httpURLConnection.disconnect();
		} catch (Exception localException) {
		}
		return buffer.toString();
	}

	public static Map<String, String> getRobotResult(String info, String loc,
			String lon, String lat) {
		String result = httpRequest(info, loc, lon, lat);
		logger.error("result:" + result);

		Gson gson = new Gson();
		Robot robotResult = (Robot) gson.fromJson(result, Robot.class);
		logger.error("robotResult" + robotResult);
		int code = robotResult.getCode();
		logger.error("code" + code);
		String text = robotResult.getText();
		String url = robotResult.getUrl();
		newsList = robotResult.getList();
		System.out.println("newsList"+newsList+"");
		Map<String, String> map = new HashMap<String,String>();
		map.put("code", code + "");
		map.put("text", text);
		map.put("url", url);
		return map;
	}
}
