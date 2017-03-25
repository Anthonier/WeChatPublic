package com.qianjg.test;


import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import javax.persistence.criteria.Root;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.qianjg.message.response.Music;

/**
 * 百度音乐搜索API操作类
 * 
 * @author AdministratAor
 *
 */
public class BaiduMusicService {
	/**
	 * 根据名称（和作者）    搜索音乐
	 * @param musicTitle  音乐标题
	 * @param musicAuthor 音乐作者
	 * @return Music      音乐对象 
	 */
	public static Music searchMusic(String musicTitle, String musicAuthor) {
		// 百度音乐搜索的地址
		String requestUrl = "http://box.zhangmen.baidu.com/x?op=12&count=1&title={TITLE}$${AUTHOR}$$$$";

		// 对音乐名称，作者URL进行编码
		requestUrl = requestUrl.replace("{TITLE}", urlEncodeUTF8(musicTitle));
		requestUrl = requestUrl.replace("{AUTHOR}", urlEncodeUTF8(musicAuthor));
		// 处理名称、作者中间的空格
		requestUrl.replaceAll("\\+", "%20");

		// 查询并获取返回结果，因为解析时直接读取一个输入流，所以直接返回输入流即可，若是json,则需要通过BufferReader返回一个字符串，然后通过Gson解析字符串中的Json数据
		InputStream inputStream = httpRequest(requestUrl);
		// 从返回结果中解析出Music
		Music music = parseMusic(inputStream);

		// 如果music部位null,设置标题和描述
		if (null != music) {
			music.setTitle(musicTitle);
			// 如果作者不为"",将描述位置设置为作者
			if (!"".equals(musicAuthor))
				music.setTitle(musicTitle);
			else
				music.setDescription("来自Full Stack社区");
		}
		return music;
	}

	@SuppressWarnings("unchecked")
	private static Music parseMusic(InputStream inputStream) {
		// TODO Auto-generated method stub
		Music music = null;
		try {
			// 使用dom4j解析xml字符串
			// 创建解析器
			SAXReader reader = new SAXReader();
			// 直接读取一个输入流，得到document
			Document document = reader.read(inputStream);
			// 得到xml根节点
			Element root = document.getRootElement();
			// count表示搜索到的歌曲数
			String count = root.element("count").getText();
			// 当搜索的歌曲数大于0时
			if (!"0".equals(count)) {
				// 得到所有普通品质的集合
				List<Element> urlList = root.elements("url");
				// 得到所有高品质的集合
				List<Element> durlList = root.elements("durl");

				// 普通品质的encode,decode  默认读取第一条
				String urlEncode = urlList.get(0).element("encode").getText();
				String urlDecode = urlList.get(0).element("decode").getText();

				// 普通品质音乐的URL
				// 包含头不包含尾
				String url = urlEncode.substring(0,
						urlEncode.lastIndexOf("/") + 1) + urlDecode;
				// 如果urlDecode后面带参数
				if (-1 != urlDecode.lastIndexOf("&"))
					url = urlEncode
							.substring(0, urlEncode.lastIndexOf("/") + 1)
							+ urlDecode
									.substring(0, urlDecode.lastIndexOf("&"));

				// 默认情况下，高品质音乐的URL就等于普通品质音乐的URL
				String durl = url;

				// 判断高品质节点是否存在
				Element durlElement = durlList.get(0).element("encode");
				if (null != durlElement) {
					// 高品质的encode、decode 默认读取第一条
					String durlEncode = durlList.get(0).element("encode")
							.getText();
					String durlDecode = durlList.get(0).element("decode")
							.getText();

					// 高品质音乐的URL
					// 高品质音乐的URL
					// 包含头不包含尾
					durl = durlEncode.substring(0,
							durlEncode.lastIndexOf("/") + 1) + durlDecode;
					// 如果durlDecode后面带参数
					if (-1 != durlDecode.lastIndexOf("&"))
						durl = durlEncode.substring(0,
								durlEncode.lastIndexOf("/") + 1)
								+ durlDecode.substring(0,
										durlDecode.lastIndexOf("&"));
				}
				music = new Music();
				// 设置普通品质音乐链接
				music.setMusicUrl(url);
				// 设置高品质音乐链接
				music.setHQmusicUrl(durl);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return music;
	}

	/**
	 * utf-8编码
	 * 
	 * @param source 来源
	 * @return String 字符串
	 */
	public static String urlEncodeUTF8(String source) {
		if (source == null) {
			return "";
		}
		String result = source;
		try {
			result = URLEncoder.encode(result, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 发送http get请求返回的输入流
	 * 
	 * @param requestUrl
	 * @return
	 */
	private static InputStream httpRequest(String requestUrl) {
		InputStream inputStream = null;
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection httpUrlConn = (HttpURLConnection) url
					.openConnection();
			// 开启输入
			httpUrlConn.setDoInput(true);
			httpUrlConn.setRequestMethod("GET");
			httpUrlConn.connect();
			// 获得返回的输入流
			inputStream = httpUrlConn.getInputStream();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return inputStream;
	}
}
