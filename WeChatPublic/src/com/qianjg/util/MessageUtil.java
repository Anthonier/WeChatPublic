package com.qianjg.util;

import java.io.InputStream;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.qianjg.message.response.Article;
import com.qianjg.message.response.MusicResponseMessage;
import com.qianjg.message.response.NewsResponseMessage;
import com.qianjg.message.response.TextResponseMessage;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

/**
 * 消息工具类
 * 关于消息及消息处理工具的封装，对请求消息/响应消息建立了与之对应的Java类、对请求的xml消息进行解析、将响应消息的java对象转换成xml。
 * 
 * @author Administrator
 *
 */
public class MessageUtil {
	/**
	 * 返回消息类型：文本
	 */
	public static final String RESPONSE_MESSAGE_TYPE_TEXT = "text";

	/**
	 * 返回消息类型：音乐
	 */
	public static final String RESPONSE_MESSAGE_TYPE_MUSIC = "music";

	/**
	 * 返回消息类型：图文
	 */
	public static final String RESPONSE_MESSAGE_TYPE_NEWS = "news";

	/**
	 * 请求消息类型：文本
	 */
	public static final String REQUEST_MESSAGE_TYPE_TEXT = "text";

	/**
	 * 请求消息类型：图片
	 */
	public static final String REQUEST_MESSAGE_TYPE_IMAGE = "image";
	/**
	 * 请求消息类型：链接
	 */
	public static final String REQUEST_MESSAGE_TYPE_LINK = "link";
	/**
	 * 请求消息类型：地理位置
	 */
	public static final String REQUEST_MESSAGE_TYPE_LOCATION = "location";
	/**
	 * 请求消息类型：音频
	 */
	public static final String REQUEST_MESSAGE_TYPE_VOICE = "voice";
	/**
	 * 请求消息类型：视频
	 */
	public static final String REQUEST_MESSAGE_TYPE_VIDEO = "video";
	/**
	 * 请求消息类型：小视频
	 */
	public static final String REQUEST_MESSAGE_TYPE_SHORTVIDEO = "shortvideo";
	/**
	 * 请求消息类型：推送
	 */
	public static final String REQUEST_MESSAGE_TYPE_EVENT = "event";
	/**
	 * 事件类型：subscribe(订阅)
	 */
	public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
	/**
	 * 事件类型：unsubscribe(取消订阅)
	 */
	public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
	/**
	 * 事件类型：CLICK(自定义菜单点击事件)
	 */
	public static final String EVENT_TYPE_CLICK = "CLICK";
	/**
	 * 事件类型：CLICK(点击菜单跳转链接)
	 */
	public static final String EVENT_TYPE_VIEW = "VIEW";
	/**
	 * 事件类型：SCAN(扫描带参数二维码)
	 */
	public static final String EVENT_TYPE_SCAN = "SCAN";
	/**
	 * 事件类型：Location(上报地理位置)
	 */
	public static final String EVENT_TYPE_LOCATION = "LOCATION";

	/**
	 * 解析微信发来的请求(xml)
	 * 
	 * @param request 请求
	 * @return Map 集合
	 * @throws Exception 异常
	 * 
	 */
	@SuppressWarnings("unckecked")
	public static Map<String, String> parseXml(HttpServletRequest request)
			throws Exception {
		// 将解析结果存储在HashMap中
		Map<String, String> map = new HashMap<String, String>();

		// 从request中取得输入流
		InputStream inputStream = request.getInputStream();
		/**
		 * 1.创建解析器 2.得到document 3.得到根节点 4.得到所有子节点 5.遍历所有子节点
		 */
		// 创建解析器
		SAXReader saxReader = new SAXReader();
		// 读取输入流，得到document
		Document document = saxReader.read(inputStream);
		// 得到xml根节点
		Element root = document.getRootElement();
		// 得到所有子节点
		List<Element> elementList = root.elements();

		// 遍历所有子节点
		for (Element element : elementList) {
			// 得到每一个子节点下面的name元素和文本
			map.put(element.getName(), element.getText());
		}
		// 释放资源
		inputStream.close();
		inputStream = null;

		return map;
	}

	/**
	 * 将响应消息转换成xml返回
	 * 
	 * @author Administrator
	 *
	 */
	/**
	 * 文本消息对象转换成xml
	 * 
	 * @param textMessage
	 *            文本消息对象
	 * @return xml
	 */
	public static String textMessageToXml(TextResponseMessage textMessage) {
		xstream.alias("xml", textMessage.getClass());
		return xstream.toXML(textMessage);
	}

	/**
	 * 音乐消息对象转换成xml
	 * 
	 * @param musicMessage
	 *            音乐消息对象
	 * @return xml
	 */
	public static String musicMessageToXml(MusicResponseMessage musicMessage) {
		xstream.alias("xml", musicMessage.getClass());
		return xstream.toXML(musicMessage);
	}

	/**
	 * 图文消息对象转换成xml
	 * 
	 * @param newsMessage
	 *            图文消息对象
	 * @return xml
	 */
	public static String newsMessageToXml(NewsResponseMessage newsMessage) {
		xstream.alias("xml", newsMessage.getClass());
		xstream.alias("item", new Article().getClass());
		return xstream.toXML(newsMessage);
	}

	/**
	 * xstram框架本身并不支持CDATA块的生成，所以拓展xstream，使其支持在生成xml各元素值时添加CDATA块
	 * 
	 * @author Administrator
	 *
	 */

	// 创建一个匿名对象
	public static XStream xstream = new XStream(new XppDriver() {
		// HierarchicalStreamWriter 是一个接口，从字面上意思来说它是有等级的输入流。
		public HierarchicalStreamWriter createWriter(Writer out) {
			// 用 PrettyPrintWriter 方法输出的为有分隔有一定格式的 XML 文件
			return new PrettyPrintWriter(out) {
				// 对所有xml节点的转换都增加CDATA标记
				boolean cdata = true;

				// 是给编译器一条指令，告诉它对被批注的代码元素内部的某些警告保持静默。 允许您选择性地取消特定代码段（即，类或方法）中的警告
				@SuppressWarnings("unchecked")
				public void startNode(String name, Class clazz) {
					super.startNode(name, clazz);
				}

				public void writeText(QuickWriter writer, String text) {
					if (cdata) {
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					} else {
						writer.write(text);
					}

				}
			};
		}
	});

	/**
	 * 计算采用utf-8编码方式时字符串所占字节数
	 * 
	 * @param content 文本
	 * @return int 字节
	 */
	public static int getByteSize(String content) {
		int size = 0;
		if (null != content) {
			try {
				// 汉子采用utf-8编码时占3个字节
				size = content.getBytes().length;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return size;
	}

	/**
	 * 判断是否是QQ表情
	 * 
	 * @param content 文本
	 * @return boolean 布尔型
	 */
	public static boolean isQqFace(String content) {
		boolean result = false;

		// 判断QQ表情的正则表达式
		String qqfaceRegex = "/::\\)|/::~|/::B|/::\\||/:8-\\)|/::<|/::$|/::X|/::Z|/::'\\(|/::-\\||/::@|/::P|/::D|/::O|/::\\(|/::\\+|/:--b|/::Q|/::T|/:,@P|/:,@-D|/::d|/:,@o|/::g|/:\\|-\\)|/::!|/::L|/::>|/::,@|/:,@f|/::-S|/:\\?|/:,@x|/:,@@|/::8|/:,@!|/:!!!|/:xx|/:bye|/:wipe|/:dig|/:handclap|/:&-\\(|/:B-\\)|/:<@|/:@>|/::-O|/:>-\\||/:P-\\(|/::'\\||/:X-\\)|/::\\*|/:@x|/:8\\*|/:pd|/:<W>|/:beer|/:basketb|/:oo|/:coffee|/:eat|/:pig|/:rose|/:fade|/:showlove|/:heart|/:break|/:cake|/:li|/:bome|/:kn|/:footb|/:ladybug|/:shit|/:moon|/:sun|/:gift|/:hug|/:strong|/:weak|/:share|/:v|/:@\\)|/:jj|/:@@|/:bad|/:lvu|/:no|/:ok|/:love|/:<L>|/:jump|/:shake|/:<O>|/:circle|/:kotow|/:turn|/:skip|/:oY|/:#-0|/:hiphot|/:kiss|/:<&|/:&>";
		Pattern p = Pattern.compile(qqfaceRegex);
		Matcher m = p.matcher(content);
		if (m.matches()) {
			result = true;
		}
		return result;
	}

	/**
	 * 将createTime转换为常用格式
	 * 
	 * @param createTime 创建时间
	 * @return String 返回字符串
	 */
	public static String formatTime(String createTime) {
		// 将微信传入的CreateTime转换成long类型，再乘以1000.微信消息接口中的 CreateTime 表示距离 1970
		// 年的秒数.而new Date()传入的是秒数。
		long msgCreateTime = Long.parseLong(createTime) * 1000L;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(new Date(msgCreateTime));
	}

	/**
	 * emoji表情转换 hex  to utf-16
	 * 
	 * @param hexEmoji 十六进制标签
	 * @return String 字符串
	 */
	public static String emoji(int hexEmoji) {
		// 将emoji代码表中的U+替换为0x
		return String.valueOf(Character.toChars(hexEmoji));
	}
	/**
	 * 组装要返回的文本消息对象,封装成xml
	 * @param textResponseMessage 文本消息对象
	 * @param responseMessage	   响应消息
	 * @param responseContent	   响应内容
	 * @return String 字符串
	 */
	public static String parseTextMessageToXml(
			TextResponseMessage textResponseMessage, String responseMessage,String responseContent) {
		// 判断消息内容长度，使其小于2048字节
		if (getByteSize(responseContent) < 2048) {
			// 组装要返回的文本消息对象
			textResponseMessage.setContent(responseContent);
		} else {
			textResponseMessage.setContent("消息回复字数超过限制");
		}

		return responseMessage = textMessageToXml(textResponseMessage);
	}
}
