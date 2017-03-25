package com.qianjg.faceservice;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.qianjg.pojoface.Face;

/**
 * 人脸检测服务
 * 
 * @author Administrator
 *
 */
public class FaceService {
	/**
	 * 发送Http请求
	 * 
	 * @param requestUrl 请求地址
	 * @return String    String
	 */
	public static String httpRequest(String requestUrl) {
		StringBuffer buffer = new StringBuffer();
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection httpUrlConn = (HttpURLConnection) url
					.openConnection();
			httpUrlConn.setDoInput(true);
			httpUrlConn.setRequestMethod("GET");
			httpUrlConn.connect();

			// 将返回的输入流转换成字符串，以便于后期Gson解析
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
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
			httpUrlConn.disconnect();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return buffer.toString();
	}

	/**
	 * 调用Face++ API实现人脸识别
	 * 
	 * @param picUrl 图片地址
	 * @return List  集合
	 */
	public static List<Face> faceDetect(String picUrl) {
		List<Face> faceList = new ArrayList<Face>();
		try {
			// 拼接Face++人脸检测的请求地址
			String queryUrl = "http://apicn.faceplusplus.com/v2/detection/detect?url=URL&api_secret=API_SECRET&api_key=API_KEY";
			// 对URL进行编码
			// 凡是通过 GET 传递的参数中可能会包含特殊字符，都必须进行 URL
			// 编码，除了中文以外，特殊字符还包括等号“=”、与“&”、空格“ ”等。
			queryUrl = queryUrl.replace("URL",
					URLEncoder.encode(picUrl, "UTF-8"));
			queryUrl = queryUrl.replace("API_KEY",
					"你Face++平台上的API_KEY");
			queryUrl = queryUrl.replace("API_SECRET",
					"你的API_SECRET");

			// 调用人脸检测接口
			String json = httpRequest(queryUrl);
			// 解析返回json中的Face列表
			// 使用 JSON-lib 解析人脸检测接口返回的 JSON 数据，并将解析结果存入 List中
			JSONArray jsonArray = JSONObject.fromObject(json).getJSONArray(
					"face");

			// 遍历检测到的人脸
			for (int i = 0; i < jsonArray.size(); i++) {
				// face
				JSONObject faceObject = (JSONObject) jsonArray.get(i);
				// attribute
				JSONObject attrObject = faceObject.getJSONObject("attribute");

				// position
				JSONObject posObject = faceObject.getJSONObject("position");

				Face face = new Face();
				face.setFaceId(faceObject.getString("face_id"));
				face.setAgeValue(attrObject.getJSONObject("age")
						.getInt("value"));
				face.setAgeRange(attrObject.getJSONObject("age")
						.getInt("range"));
				face.setGenderValue(genderConvert(attrObject.getJSONObject(
						"gender").getString("value")));
				face.setGenderConfidence(attrObject.getJSONObject("gender")
						.getDouble("confidence"));
				face.setRaceValue(raceConvert(attrObject.getJSONObject("race")
						.getString("value")));
				face.setRaceConfidence(attrObject.getJSONObject("race")
						.getDouble("confidence"));
				face.setSmilingValue(attrObject.getJSONObject("smiling")
						.getDouble("value"));
				face.setCenterX(posObject.getJSONObject("center")
						.getDouble("x"));
				face.setCenterY(posObject.getJSONObject("center")
						.getDouble("y"));
				faceList.add(face);
				// 将检测出的Face从左至右排序
				// 使用 Collections.sort()方法排序的前提是集合中的 Face对象实现了 Comparable 接口。
				Collections.sort(faceList);
			}
		} catch (Exception e) {
			// TODO: handle exception
			faceList = null;
			e.printStackTrace();
		}
		return faceList;
	}

	/**
	 * 把性别从英文翻译到中文
	 * 
	 * @param gender
	 * @return
	 */
	private static String genderConvert(String gender) {
		String result = "男性";
		if ("Male".equals("gender"))
			result = "男性";
		else if ("Female".equals(gender))
			result = "女性";
		return result;
	}

	/**
	 * 把人种从英文翻译到中文
	 * 
	 * @param race
	 * @return
	 */
	private static String raceConvert(String race) {
		String result = "黄肤色";
		if ("Asian".equals(race))
			result = "黄肤色";
		else if ("White".equals(race))
			result = "白肤色";
		else if ("Black".equals(race))
			result = "黑肤色";
		return result;
	}

	/**
	 * 根据人脸识别结果组装消息
	 * 
	 * @param faceList
	 *            人脸列表
	 * @return
	 */
	private static String makeMessage(List<Face> faceList) {
		StringBuffer buffer = new StringBuffer();
		// 检测到1张脸
		if (1 == faceList.size()) {
			buffer.append("共检测到").append(faceList.size()).append("张脸")
					.append("\n");
			for (Face face : faceList) {
				buffer.append(face.getRaceValue()).append("人种，");
				buffer.append(face.getGenderValue()).append(",");
//				int bigAge=face.getAgeValue()+face.getAgeRange();
//				int smallAge=face.getAgeValue()-face.getAgeRange();
				buffer.append("亲，您看起来很年轻！才").append(face.getAgeValue()).append("岁哦！").append("\n");
			}
		}
		// 检测到2-10张脸
		else if (faceList.size() > 1 && faceList.size() <= 10) {
			buffer.append("共检测到").append(faceList.size())
					.append("张脸，按脸部中心位置从左至右依次为：").append("\n");
			for (Face face : faceList) {
				buffer.append(face.getRaceValue()).append("人种，");
				buffer.append(face.getGenderValue()).append(",");
//				int bigAge=face.getAgeValue()+face.getAgeRange();
//				int smallAge=face.getAgeValue()-face.getAgeRange();
				buffer.append("亲，你看起来很年轻！才").append(face.getAgeValue()).append("岁哦！").append("\n");
			}
		}
		// 检测到10张脸以上
		else if (faceList.size() > 10) {
			buffer.append("共检测到").append(faceList.size()).append("张脸")
					.append("\n");
			// 统计各人种，性别的个数
			int asiaMale = 0;
			int asiaFemale = 0;
			int whiteMale = 0;
			int whiteFemale = 0;
			int blackMale = 0;
			int blackFemale = 0;
			for (Face face : faceList) {
				if ("黄肤色".equals(face.getRaceValue()))
					if ("男性".equals(face.getGenderValue()))
						asiaMale++;
					else
						asiaFemale++;
				else if ("白肤色".equals(face.getRaceValue()))
					if ("男性".equals(face.getGenderValue()))
						whiteMale++;
					else
						whiteFemale++;
				else if ("黑肤色".equals(face.getRaceValue()))
					if ("男性".equals(face.getGenderValue()))
						blackMale++;
					else
						blackFemale++;
			}
			if (0 != asiaMale || 0 != asiaFemale)
				buffer.append("黄肤色人种：").append(asiaMale).append("男")
						.append(asiaFemale).append("女").append("\n");
			if (0 != whiteMale || 0 != whiteFemale)
				buffer.append("白肤色人种：").append(whiteMale).append("男")
						.append(whiteFemale).append("女").append("\n");
			if (0 != blackMale || 0 != blackFemale)
				buffer.append("黑肤色人种：").append(blackMale).append("男")
						.append(blackFemale).append("女").append("\n");
		}
		// 移除末尾空格
		buffer = new StringBuffer(buffer.substring(0, buffer.lastIndexOf("\n")));
		return buffer.toString();
	}
	
	/**
	 * 提供给外部调用的人脸检测方法
	 * @param picUrl 待检测 图片的访问地址
	 * @return String
	 */
	public static String detect(String picUrl) {
		// 默认回复消息
		String result = "未能正常识别，请换一张清晰的照片再试！";
		List<Face> faceList = faceDetect(picUrl);
		if (null != faceList) {
			result = makeMessage(faceList);
		}
		return result;
	}
}
