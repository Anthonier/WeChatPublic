package com.qianjg.createmenu;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.qianjg.pojomenu.AccessToken;
import com.qianjg.pojomenu.Button;
import com.qianjg.pojomenu.CommonButton;
import com.qianjg.pojomenu.ComplexButton;
import com.qianjg.pojomenu.Menu;
import com.qianjg.pojomenu.ViewButton;
import com.qianjg.util.WeChatUtil;

/**
 * 调用封装的方法创建自定义菜单 菜单管理类
 * 
 * @author Administrator
 *
 */
public class MenuManager implements ServletContextListener {
	private static Logger log = Logger.getLogger(MenuManager.class);

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * 创建菜单
	 */
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		log.error("MenuCreate~~~~~~~~~~~~~");
		// 第三方用户唯一凭证
		String appId = "第三方用户唯一凭证";
		// 第三方用户唯一凭证密钥，即appsecret
		String appSecret = "第三方用户唯一凭证密钥，即appsecret";

		// 调用接口获取access_token
		// 调用WeChatUtil工具类中封装好的getAccessToken方法，得到AccessToken对象，再调用getToken()得到access_token
		AccessToken accessToken = WeChatUtil.getAccessToken(appId, appSecret);
		log.error("access_token===============" + accessToken);
		if (null != accessToken) {
			// 调用接口创建菜单
			int result = WeChatUtil.createMenu(getMenu(),
					accessToken.getToken());

			// 判断菜单创建结果
			if (0 == result) {
				log.error("菜单创建成功");
			} else {
				log.error("菜单创建失败,错误码:" + result);
			}
		}
	}

	/**
	 * 查询菜单
	 * @param accessToken 	accessToken
	 * @return String  		String 
	 */
	public static String searchMenu(String accessToken) {
		String result = null;
		String appId = "第三方用户唯一凭证";
		String appSecret = "第三方用户唯一凭证密钥，即appsecret";
		AccessToken accessToken1 = WeChatUtil.getAccessToken(appId, appSecret);
		return null;
	}

	/**
	 * 组装菜单数据
	 * 
	 * @return
	 */
	private static Menu getMenu() {
		// TODO Auto-generated method stub
		CommonButton commonButton11 = new CommonButton();
		commonButton11.setName("焦点新闻");
		commonButton11.setType("click");
		commonButton11.setKey("11");

		CommonButton commonButton12 = new CommonButton();
		commonButton12.setName("周边搜索");
		commonButton12.setType("click");
		commonButton12.setKey("12");

		CommonButton commonButton21 = new CommonButton();
		commonButton21.setName("趣味人脸识别");
		commonButton21.setType("click");
		commonButton21.setKey("21");

		CommonButton commonButton22 = new CommonButton();
		commonButton22.setName("世界语言翻译");
		commonButton22.setType("click");
		commonButton22.setKey("22");

		CommonButton commonButton23 = new CommonButton();
		commonButton23.setName("微机器人助手");
		commonButton23.setType("click");
		commonButton23.setKey("23");

		ViewButton viewButton1 = new ViewButton();
		viewButton1.setName("美食部落");
		viewButton1.setType("view");
		viewButton1.setUrl("http://www.qianjg.com/DeliciousFood/index.html");

		ViewButton viewButton2 = new ViewButton();
		viewButton2.setName("商务英语平台");
		viewButton2.setType("view");
		viewButton2.setUrl("http://www.qianjg.com/CommerceEnglish/gallery-index-list.html");

		ViewButton viewButton3 = new ViewButton();
		viewButton3.setName("记事本系统");
		viewButton3.setType("view");
		viewButton3.setUrl("http://www.qianjg.com/WeChatNote/index.htm");

		ViewButton viewButton4 = new ViewButton();
		viewButton4.setName("平台介绍");
		viewButton4.setType("view");
		viewButton4.setUrl("http://www.qianjg.com/CommerceEnglish/introduction.html");

		ComplexButton complexButton1 = new ComplexButton();
		complexButton1.setName("微服务");
		complexButton1.setSub_button(new Button[] { commonButton11,
				commonButton12 });

		ComplexButton complexButton2 = new ComplexButton();
		complexButton2.setName("微智能");
		complexButton2.setSub_button(new Button[] { commonButton21,
				commonButton22, commonButton23 });

		ComplexButton complexButton3 = new ComplexButton();
		complexButton3.setName("微平台");
		complexButton3.setSub_button(new Button[] { viewButton1, viewButton2,
				viewButton3, viewButton4 });
		
	/*	log.error("button1-----" + complexButton1 + "button2----"
				+ complexButton2 + "button3" + complexButton3);*/
		/**
		 * 若某个一级菜单下没有二级菜单，应该这样定义： 如 第三个一级菜单项不是"更多体验"，而直接是"幽默笑话"
		 * menu.setButton(new
		 * Button[]{complexButton1,complexButton2,CommonButton33});
		 */

		Menu menu = new Menu();
		menu.setButton(new Button[] { complexButton1, complexButton2,
				complexButton3 });
		log.error("menu~~~~~~~~~~~~~~~~~" + menu);
		return menu;
	}

}
