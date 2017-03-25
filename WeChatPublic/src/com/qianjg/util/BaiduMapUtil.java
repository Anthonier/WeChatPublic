package com.qianjg.util;

import it.sauronsoftware.base64.Base64;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.json.JSONObject;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.qianjg.message.response.Article;
import com.qianjg.pojomap.BaiduPlace;
import com.qianjg.pojomap.UserLocation;

/**
 * 百度地图操作类
 * 
 * @author Think
 *
 */
public class BaiduMapUtil {
	/**
	 * 圆形区域检索 将请求中的参数query进行UTF-8编码。不管调用任何接口，凡是通过URL传递参数，只要参数中可能为中文，就必须
	 * 显式地对参数进行编码，否则很可能在本机运行正常，但部署到服务器出现问题，因为本机与服务器默认编码方式可能不一样。
	 * 
	 * @param query
	 *            检索关键字
	 * @param lng
	 *            经度
	 * @param lat
	 *            纬度
	 * @return List 位置集合
	 * @throws Exception 异常请求
	 */
	public static List<BaiduPlace> searchPlace(String query, String lng,
			String lat) throws Exception {
		// 拼接请求地址
		String requestUrl = "http://api.map.baidu.com/place/v2/search?query=QUERY&location=LAT,LNG&radius=2000&output=xml&scope=2&page_size=10&page_num=0&ak=ejmWyI8b4QaAwSPbiEXDN4trs8fvS44c";
		requestUrl = requestUrl.replace("QUERY",
				URLEncoder.encode(query, "UTF-8"));
		requestUrl = requestUrl.replace("LAT", lat);
		requestUrl = requestUrl.replace("LNG", lng);
		// 调用Place API 圆形区域检索
		String respXml = httpRequest(requestUrl);
		// 解析返回的xml
		List<BaiduPlace> placeList = parsePlaceXml(respXml);
		return placeList;
	}

	/**
	 * 发送http请求 调用PlaceAPI和坐标转换接口都要用到
	 * 
	 * @param requestUrl
	 * @return String
	 */
	private static String httpRequest(String requestUrl) {
		// TODO Auto-generated method stub
		StringBuffer buffer = new StringBuffer();
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url
					.openConnection();
			httpURLConnection.setDoInput(true);
			httpURLConnection.setRequestMethod("GET");
			httpURLConnection.connect();

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpURLConnection.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "UTF-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpURLConnection.disconnect();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return buffer.toString();
	}

	/**
	 * 根据百度地图返回的流解析出地址信息 调用Place
	 * API返回的XML结果，在解析完成后使用了Collections.sort()方法对BaiduPlace集合按距离由近及远排序。
	 * 在Java中要对集合中的对象进行排序
	 * ，可以使用Comparator或者Comparable接口，BaiduPlace实现Comparable接口，并实现接口的ComparaTo
	 * ()方法。
	 * 
	 * @param inputStream
	 *            输入流
	 * @return List<BaiduPlace>
	 */
	@SuppressWarnings("unchecked")
	private static List<BaiduPlace> parsePlaceXml(String xml) {
		// TODO Auto-generated method stub
		List<BaiduPlace> placeList = null;
		try {
			Document document = DocumentHelper.parseText(xml);
			// 得到xml根元素
			Element root = document.getRootElement();
			// 从根元素获取<results>
			Element resultsElement = root.element("results");
			// 从<results>中获取<result>集合
			List<Element> resultElementList = resultsElement.elements("result");
			// 判断<result>集合的大小
			if (resultElementList.size() > 0) {
				placeList = new ArrayList<BaiduPlace>();
				// POI名称
				Element nameElement = null;
				// POI地址信息
				Element addressElement = null;
				// POI经纬度坐标
				Element locationElement = null;
				// POI电话信息
				Element telephonElement = null;
				// POI拓展信息
				Element detailInfoElement = null;
				// 距离中心点的距离
				Element distanceElement = null;
				// 遍历<result>集合
				for (Element resultElement : resultElementList) {
					nameElement = resultElement.element("name");
					addressElement = resultElement.element("address");
					locationElement = resultElement.element("location");
					telephonElement=resultElement.element("telephone");
					detailInfoElement = resultElement.element("detail_info");

					BaiduPlace place = new BaiduPlace();
					place.setName(nameElement.getText());
					place.setAddress(addressElement.getText());
					place.setLng(locationElement.element("lng").getText());
					place.setLat(locationElement.element("lat").getText());
					// 当<telephone>元素存在时获取电话号码
					if (null != telephonElement)
						place.setTelephone(telephonElement.getText());
					// 当<detail_info>元素存在时获取<distance>
					if (null != detailInfoElement) {
						distanceElement = detailInfoElement.element("distance");
						if (null != distanceElement)
							place.setDistance(Integer.parseInt(distanceElement
									.getText()));
					}
					placeList.add(place);
				}
				Collections.sort(placeList);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return placeList;
	}

	/**
	 * 根据place组装图文列表
	 * 组装图文消息所需的Article集合，Article的URL指向的是根目录下的步行导航界面route.jsp，当用户点击图文消息时，
	 * 就会通过微信浏览器打开百度地图，并且显示用户发送位置至目标位置的步行线路。
	 * 
	 * @param placeList 位置集合
	 * @param bd09Lng
	 *            经度
	 * @param bd09Lat
	 *            纬度
	 * @return List 文章列表
	 */
	public static List<Article> makeArticleList(List<BaiduPlace> placeList,
			String bd09Lng, String bd09Lat) {
		// 项目的根路径
//		String basePath = "http://www.qianjg.com/WeChat/";
		String basePath="http://wechat.qianjg.com/WeChat/";
		List<Article> list = new ArrayList<Article>();
		BaiduPlace place = null;
		int count=0;  //计算图文消息的个数，控制图文消息的长度，如果超过8条则不响应
		for (int i = 0; i < placeList.size(); i++) {
			if(count<8){
				count++;
				place = placeList.get(i);
				Article article = new Article();
				article.setTitle(place.getName() + "\n距离约" + place.getDistance()
						+ "米");
				// P1标识用户发送的位置（坐标转换后）,p2标识当前POI所在位置
				article.setUrl(String.format(basePath
						+ "route.jsp?p1=%s,%s&p2=%s,%s", bd09Lng, bd09Lat,
						place.getLng(), place.getLat()));
				// 将收条图文的图片位置为大图
				if (i == 0)
					article.setPicUrl(basePath + "images/poisearch.png");
				else
					article.setPicUrl(basePath + "images/navi.png");
				list.add(article);
			}else{
				break;
			}
			
		}
		return list;

	}

	/**
	 * 将微信定位的坐标转换成百度坐标GCJ-02 到Baidu,解析结果用到了json-lib和base64工具
	 * 
	 * @param lng 经度
	 * @param lat 纬度
	 * @return UserLocation 用户定位
	 */
	public static UserLocation convertCoord(String lng, String lat) {
		// 百度坐标转换接口
		String convertUrl = "http://api.map.baidu.com/ag/coord/convert?from=2&to=4&x={x}&y={y}";
		convertUrl = convertUrl.replace("{x}", lng);
		convertUrl = convertUrl.replace("{y}", lat);

		UserLocation location = new UserLocation();
		try {
			String jsonCoord = httpRequest(convertUrl);
			JSONObject jsonObject = JSONObject.fromObject(jsonCoord);
			// 对转换后的坐标进行Base64解码
			location.setBd09Lng(Base64.decode(jsonObject.getString("x"),
					"UTF-8"));
			location.setBd09Lat(Base64.decode(jsonObject.getString("y"),
					"UTF-8"));
		} catch (Exception e) {
			// TODO: handle exception
			location = null;
			e.printStackTrace();
		}
		return location;
	}
}
