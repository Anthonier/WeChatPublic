package com.qianjg.util;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.qianjg.pojomap.UserLocation;

/**
 * Mysql数据库操作类
 * 
 * 调试有没有接收到数据时 可以在mysql -u root -p ; show databases; user wechat; show tables;
 * select * from user_location;
 * 
 * @author Think
 *
 */
/**
 * 通过ngrok本地调试 //mysql的用户名改为本地的用户root 以及修改接口 url 为
 * http://wechat.qianjg.com/WeChat/mainServlet
 * 
 * @author Think
 *
 */
public class MySQLUtil {
	/**
	 * 获取数据库连接
	 * 
	 * @param request
	 * @return Connection
	 */
	private Connection getConn(HttpServletRequest request) {
		String url = "jdbc:mysql://localhost:3306/wechat?"  			//wechat为数据库名
				+ "useUnicode=true&characterEncoding=UTF8&useSSL=true";
		String driver = "com.mysql.jdbc.Driver";
		String username = "root";     //mysql数据库用户名
		String password = "password"; //mysql数据库密码
		Connection conn = null;
		try {
			Class.forName(driver);
			System.out.println("成功加载MySql驱动程序");
			// 获取数据库连接
			conn = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 保存用户地理位置
	 * 
	 * @param request
	 *            请求对象
	 * @param openId
	 *            用户的OpenId
	 * @param lng
	 *            用户发送的经度
	 * @param lat
	 *            用户发送的纬度
	 * @param bd09_lng
	 *            经过百度坐标转换后的经度
	 * @param bd09_lat
	 *            经过百度坐标转换后的纬度
	 */
	public static void saveUserLocation(HttpServletRequest request,
			String openId, String lng, String lat, String bd09_lng,
			String bd09_lat) {
		// 直接在mysql命令行中执行 创建user_locaton表 create table user_location(id int not
		// null auto_increment primary key,open_id varchar(50) not null,lng
		// varchar(30) not null,lat varchar(30) not null,bd09_lng
		// varchar(30),bd09_lat varchar(30));
		// create table user_location(id int not null auto_increment primary key,open_id varchar(50) not null,lng varchar(30) not null,lat varchar(30) not null,bd09_lng varchar(30),bd09_lat varchar(30));
		/**
		 * ? 中不能加'' （如setString(1,"a");sql语句为：insert into table1 (c1,c2) values
		 * ('?','?')）。此时？会被作为参数传入，而不会再传入 setString里面的值。
		 */
		String sql = "insert into user_location(open_id,lng,lat,bd09_lng,bd09_lat) values (?,?,?,?,?)";
		try {
			Connection conn = new MySQLUtil().getConn(request);
			PreparedStatement ps = conn.prepareStatement(sql);
			// 编写将要输入的列，值
			ps.setString(1, openId);
			ps.setString(2, lng);
			ps.setString(3, lat);
			ps.setString(4, bd09_lng);
			ps.setString(5, bd09_lat);
			ps.executeUpdate();

			// 释放资源
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取用户最后一次发送的地理位置
	 * @param request 请求
	 * @param openId  公共ID
	 * @return UserLocation 用户地址
	 */
	public static UserLocation getLastLocation(HttpServletRequest request,
			String openId) {
		UserLocation userLocation = null;
		// 按降序只取第一行
		String sql = "select open_id, lng, lat, bd09_lng, bd09_lat from user_location where open_id=? order by id desc limit 0,1";
		try {
			Connection conn = new MySQLUtil().getConn(request);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, openId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				userLocation = new UserLocation();
				userLocation.setOpenId(rs.getString("open_id"));
				userLocation.setLng(rs.getString("lng"));
				userLocation.setLat(rs.getString("lat"));
				userLocation.setBd09Lng(rs.getString("bd09_lng"));
				userLocation.setBd09Lat(rs.getString("bd09_lat"));
			}
			// 释放资源
			rs.close();
			ps.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println("userLocation in MySQLUtil" + userLocation);
		return userLocation;
	}
}
