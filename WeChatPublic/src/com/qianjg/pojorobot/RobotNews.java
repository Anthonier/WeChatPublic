package com.qianjg.pojorobot;
/**
 * 图灵机器人新闻接口参数
 * @author ThinkQJG
 *
 */
public class RobotNews
{
  private String article;
  private String source;
  private String icon;
  private String detailurl;
  
  public String getArticle()
  {
    return this.article;
  }
  
  public void setArticle(String article)
  {
    this.article = article;
  }
  
  public String getSource()
  {
    return this.source;
  }
  
  public void setSource(String source)
  {
    this.source = source;
  }
  
  public String getIcon()
  {
    return this.icon;
  }
  
  public void setIcon(String icon)
  {
    this.icon = icon;
  }
  
  public String getDetailurl()
  {
    return this.detailurl;
  }
  
  public void setDetailurl(String detailurl)
  {
    this.detailurl = detailurl;
  }
}
