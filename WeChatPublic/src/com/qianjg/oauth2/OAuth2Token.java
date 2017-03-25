package com.qianjg.oauth2;

public class OAuth2Token{
  //网页授权接口凭证
  private String accessToken;
  //凭证有效时长
  private int expiresIn;
  //用于刷新凭证
  private String refreshToken;
  //用户标识
  private String openId;
  //用户授权作用域
  private String scope;
  
  public String getAccessToken()
  {
    return this.accessToken;
  }
  
  public void setAccessToken(String accessToken)
  {
    this.accessToken = accessToken;
  }
  
  public int getExpiresIn()
  {
    return this.expiresIn;
  }
  
  public void setExpiresIn(int expiresIn)
  {
    this.expiresIn = expiresIn;
  }
  
  public String getRefreshToken()
  {
    return this.refreshToken;
  }
  
  public void setRefreshToken(String refreshToken)
  {
    this.refreshToken = refreshToken;
  }
  
  public String getOpenId()
  {
    return this.openId;
  }
  
  public void setOpenId(String openId)
  {
    this.openId = openId;
  }
  
  public String getScope()
  {
    return this.scope;
  }
  
  public void setScope(String scope)
  {
    this.scope = scope;
  }
}
