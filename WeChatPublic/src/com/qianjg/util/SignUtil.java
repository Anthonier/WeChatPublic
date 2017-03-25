package com.qianjg.util;

import java.security.MessageDigest;
import java.util.Arrays;
/**
 *  1）将token、timestamp、nonce三个参数进行字典序排序
 *  2）将三个参数字符串拼接成一个字符串进行sha1加密
 *	3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
 * @author Administrator
 *
 */
public class SignUtil {
	// 与微信接口配置里的Token一致
	private static String token = "微信接口配置里的Token";

	/**
	 * 验证签名
	 * 
	 * @param signature 微信加密签名
	 * @param timestamp 时间戳
	 * @param nonce     随机数
	 * @return boolean  布尔值
	 */
	public static boolean checkSignature(String signature, String timestamp,
			String nonce) {

		String arr[] = new String[] { token, timestamp, nonce };
		// 将token,timestamp,nonce三个参数进行排序
		Arrays.sort(arr);
		StringBuilder content = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			content.append(arr[i]);
		}
		MessageDigest md = null;
		String tmpStr = null;

		try {
			// 利用java.security.MessageDigest类就可以获取字符串和文件的MD5以及SHA1结果
			md = MessageDigest.getInstance("SHA-1");
			// 将三个参数字符串拼接成一个字符串进行sha1加密
			byte[] digest = md.digest(content.toString().getBytes());
			tmpStr = byteToStr(digest);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 清空StringBuilder
		content = null;
		// 将sha1加密后的字符串与signature对比，表示该请求来源于微信
		return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
	}

	/**
	 * 将字节数组转换为十六进制字符串
	 * 
	 * @param byteArray
	 * @return
	 */
	private static String byteToStr(byte[] byteArray) {
		// TODO Auto-generated method stub
		String strDigest = "";
		for (int i = 0; i < byteArray.length; i++) {
			strDigest += byteToHexStr(byteArray[i]);
		}
		return strDigest;
	}

	/**
	 * 将字节转换为十六进制字符串
	 * 
	 * @param byte
	 * @return
	 */
	private static String byteToHexStr(byte myByte) {
		// TODO Auto-generated method stub
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
				'B', 'C', 'D', 'E', 'F' };
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(myByte >>> 4) & 0X0F];
		tempArr[1] = Digit[myByte & 0X0F];

		String s = new String(tempArr);
		return s;
	}
}
