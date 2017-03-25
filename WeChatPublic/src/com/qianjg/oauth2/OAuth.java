package com.qianjg.oauth2;

import com.qianjg.util.WeChatUtil;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
/**
 * 网页授权凭证方法封装
 * @author Think
 *
 */
public class OAuth
{
  public static Logger logger = Logger.getLogger(OAuth.class);
  /**
   * 请求方法	
   * @param appId			ID
   * @param appSecret		Secret
   * @param code			Code
   * @return OAuth2Token	OAuth2Token
   */
  public static OAuth2Token getOauth2AccessToken(String appId, String appSecret, String code)
  {
    OAuth2Token oToken = null;
   //拼接请求地址
    String requestUrl = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
    requestUrl = requestUrl.replace("APPID", appId);
    requestUrl = requestUrl.replace("SECRET", appSecret);
    requestUrl = requestUrl.replace("CODE", code);
    //获取网页授权凭证
    JSONObject jsonObject = WeChatUtil.httpRequest(requestUrl, "GET", null);
    try
    {
      oToken = new OAuth2Token();
      oToken.setAccessToken(jsonObject.getString("accessToken"));
      oToken.setExpiresIn(jsonObject.getInt("expires_in"));
      oToken.setRefreshToken(jsonObject.getString("refresh_token"));
      oToken.setOpenId(jsonObject.getString("openid"));
      oToken.setScope(jsonObject.getString("scope"));
    }
    catch (Exception e)
    {
      oToken = null;
      int errCode = jsonObject.getInt("errcode");
      String errorMsg = jsonObject.getString("errmsg");
      logger.error("获取网页授权凭证失败 errcode" + errCode + ",errmsg" + errorMsg);
    }
    return oToken;
  }
  /**
   * 封装刷新access_token方法
   * @param appId 		  Id 
   * @param refreshToken  refreshToken
   * @return OAuth2Token  OAuth2Token 
   */
  public static OAuth2Token refreshOauth2AccessToken(String appId, String refreshToken)
  {
    OAuth2Token oToken = null;
    
    String requestUrl = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
    requestUrl = requestUrl.replace("APPID", appId);
    requestUrl = requestUrl.replace("REFRESH_TOKEN", refreshToken);
    
    JSONObject jsonObject = WeChatUtil.httpRequest(requestUrl, "GET", null);
    if (jsonObject != null) {
      try
      {
        oToken = new OAuth2Token();
        oToken.setAccessToken(jsonObject.getString("accessToken"));
        oToken.setExpiresIn(jsonObject.getInt("expires_in"));
        oToken.setRefreshToken(jsonObject.getString("refresh_token"));
        oToken.setOpenId(jsonObject.getString("openid"));
        oToken.setScope(jsonObject.getString("scope"));
      }
      catch (Exception e)
      {
        oToken = null;
        int errCode = jsonObject.getInt("errcode");
        String errorMsg = jsonObject.getString("errmsg");
        logger.error("获取网页授权凭证失败 errcode" + errCode + ",errmsg" + errorMsg);
      }
    }
    return oToken;
  }
}
