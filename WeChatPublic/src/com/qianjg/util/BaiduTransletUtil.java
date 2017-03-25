package com.qianjg.util;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.qianjg.baidutranslet.json.*;
/**
 * 百度翻译工具
 * @author Think
 *
 */
public class BaiduTransletUtil {
	public static Logger logger=Logger.getLogger(BaiduTransletUtil.class);
	public static final String TRANS_API_HOST = "http://api.fanyi.baidu.com/api/trans/vip/translate";
	// APP ID
	private String appid;
	// 密钥
	private String securityKey;

	// 实例化时调用
	public BaiduTransletUtil(String appid, String securityKey) {
		this.appid = appid;
		this.securityKey = securityKey;
	}

	// 得到翻译后的结果
	public String getTransResult(String query, String from, String to){
		String dst=null;
		Map<String, String> params = buildParams(query, from, to);
		logger.error("params============"+params);
		//获取百度翻译平台返回的字符串
		String result=HttpGet.get(TRANS_API_HOST, params);
		logger.error("return json"+result);
		//解析字符串里的json数据，通过Gson工具将result转换成TransletResult对象
		Gson gson=new Gson();
		//若返回的json数据里有中文使用unicode表示的，Gson内部已经帮我们进行了编码。
		TransletResult transletResult=gson.fromJson(result, TransletResult.class);
		//取出transletResult中的译文
		dst=transletResult.getTrans_result().get(0).getDst();
		logger.error("BaiduTransletUtil.dst========"+dst);
		if(null==dst)
			dst="翻译系统异常，请稍候再试！";
		return dst;
	}
	
	//构建通过POST或GET方法需要发送的字段
	private Map<String, String> buildParams(String query, String from, String to) {
		// TODO Auto-generated method stub
		Map<String, String> params = new HashMap<String, String>();
		params.put("q", query);
		params.put("from", from);
		params.put("to", to);

		params.put("appid", appid);
		 // 随机数
        String salt = String.valueOf(System.currentTimeMillis());
        params.put("salt", salt);
        // 签名
        String src;
		try {
			src = appid + query+ salt + securityKey;
			logger.error("src====="+src);
			 // 加密前的原文      
	        params.put("sign", MD5.md5(src));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return params;
	}

}
