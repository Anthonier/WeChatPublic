package com.qianjg.test;

import com.google.gson.Gson;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import net.sf.json.JSONObject;

public class JsonTest
{
  public static void main(String[] args)
    throws UnsupportedEncodingException
  {}
  
  public static JSONObject toJsonObject()
  {
    String test = "{\"name\":\"qjg\",\"age\":\"20\",\"hobbies\":[{\"first\":\"sing\"},{\"second\":\"dangce\"},{\"third\":\"jump\"}]}";
    
    JSONObject jsonObject = JSONObject.fromObject(test);
    
    return jsonObject;
  }
  
  public static void testGson()
  {
    String[] string = { "key:KEY", "info:INFO", "userid:USERID" };
    Gson gson = new Gson();
    System.out.println(gson.toJson(string));
  }
  
  public static void testToJson()
  {
    JSONObject object = new JSONObject();
    object.put("key", "你的APIKEY");
    object.put("userid", "USERID");
    object.put("info", URLEncoder.encode("你好"));
    System.out.println(object.toString());
  }
  
  public static void testString()
    throws UnsupportedEncodingException
  {
    String data = "{\"key\":\"APIKey\",\"info\":\"INFO\"}";
    data = data.replace("APIKey", "你的APIKEY");
    data = data.replace("INFO", URLEncoder.encode("hello", "utf-8"));
    System.out.println(data);
  }
}
