package com.qianjg.pojorobot;

import java.util.List;
/**
 * 图灵机器人接口通用参数
 * @author ThinkQJG
 *
 */
public class Robot
{
  private int code;
  private String text;
  private List<RobotNews> list;
  private String url;
  
  public String getUrl()
  {
    return this.url;
  }
  
  public void setUrl(String url)
  {
    this.url = url;
  }
  
  public List<RobotNews> getList()
  {
    return this.list;
  }
  
  public void setList(List<RobotNews> list)
  {
    this.list = list;
  }
  
  public int getCode()
  {
    return this.code;
  }
  
  public void setCode(int code)
  {
    this.code = code;
  }
  
  public String getText()
  {
    return this.text;
  }
  
  public void setText(String text)
  {
    this.text = text;
  }
}
