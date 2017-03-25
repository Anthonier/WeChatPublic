package com.qianjg.servlet;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.qianjg.service.MainService;
import com.qianjg.util.SignUtil;

/**
 * 当用户向公众账号发消息时，微信服务器会将消息通过POST方式提交给在接口配置信息中填写的URL
 * 因此就需要在URL所指向的请求消息类的doPost方法中接受消息、处理消息和响应消息。
 * 
 * @author Administrator
 * 
 *         核心请求处理类
 */
public class MainServlet extends HttpServlet {
	private static Logger logger = Logger.getLogger(MainServlet.class);  
	/**
	 * 确认请求来自微信服务
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 微信加密签名
		String signature = request.getParameter("signature");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		// 随机字符串
		String echostr = request.getParameter("echostr");
//		logger.debug("signature:"+signature+"--timestamp"+timestamp+"--nonce"+nonce+"--echostr:"+echostr);
		PrintWriter out = response.getWriter();
		// 通过检验微信加密签名对请求进行校验，若校验成功则返回字符串，表示接入成功，否则接入失败
		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
			out.print(echostr);
		}
		// 清空打印流
		out.close();
		out = null;
	}

	/**
	 * 处理微信服务器用Post方式发送过来的消息
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		logger.debug("doPost调用了");
		// TODO Auto-generated method stub
		// 消息的接收，处理，响应

		/**
		 * 将请求.响应的编码均设置为UTF-8(防止中文乱码)。
		 * 微信服务器Post消息时用的是UTF-8编码，在接收时也要用到同样的编码，否则会乱码。
		 */
		request.setCharacterEncoding("UTF-8");
		/**
		 * 在响应消息（回复消息给用户）时，也要将编码方式设置为UTF-8。
		 */
		response.setCharacterEncoding("UTF-8");

		/**
		 * 调用核心业务接受消息、处理消息 调用MainService类的handleRequest方法接收、处理消息，并得到处理结果。
		 */
		String responseMessage = MainService.handleRequest(request);
		logger.debug("从MainService类返回的responseMessage"+responseMessage);
		/**
		 * 响应消息 调用response.getWriter.write()方法将消息的处理结果返回给用户
		 */
		PrintWriter out = response.getWriter();
		out.print(responseMessage);
		out.close();
		
	}
}
