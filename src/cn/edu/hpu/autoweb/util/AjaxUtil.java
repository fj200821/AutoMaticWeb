/**
 * FileName:     	AjaxUtil.java
 * All rights Reserved
 * Copyright:    	Copyright 2011-2012 中国移动版权所有
 * Company       	广东省广州市电信规划设计院.
 * @author:    		fuxl
 * @version    		V1.0 
 * Createdate:      2013-3-4 下午4:03:48
 *
 * Modification  History:
 * Date         Author        Version        Discription
 * -----------------------------------------------------------------------------------
 * 2013-3-4       fuxl          1.0             1.0
 * Why & What is modified: <修改原因描述>
 */
package cn.edu.hpu.autoweb.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.util.PropertyFilter;

public class AjaxUtil {
	private static JsonConfig publicConfig = null;
	/**
	 * 把json字符串送到页面
	 * @param jsonStr
	 * @param response
	 */
	public static void writeToResponse(String jsonStr, HttpServletResponse response){
        response.setContentType("text/Xml;charset=utf-8");
        PrintWriter out = null;
        try {
        	out = response.getWriter();
        	if(jsonStr!=null){
                out.println(jsonStr);
        	}
        }
        catch (IOException ex1) {
            ex1.printStackTrace();
        }
        finally {
            out.close();
        }
	}
	/**
	 * 把字符串转为utf-8
	 * @param deString
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String decodeUTF8(String deString){
		if(deString != null){
			try {
				deString = URLDecoder.decode(deString,"UTF-8");
			} catch (UnsupportedEncodingException e) {				
				e.printStackTrace();
			}
		}
		return deString;
	}

	public static String enCode(String val){
		if(val != null){
			try {
				val = val.trim();		
				if(val.length()>0){
					val= URLEncoder.encode(URLEncoder.encode(val, "UTF-8"), "UTF-8");
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return val;
	}
	
	public static String deCode(String val){
		if(val != null){
			try {
				val = val.trim();		
				if(val.length()>0){
					val= URLDecoder.decode(URLDecoder.decode(val,"UTF-8"),"UTF-8");
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return val;
	}
	public static JsonConfig publicConfig() {
		if(publicConfig==null){
			JsonValueProcessor publicProcessor = new JsonValueProcessor(){
				@Override
				public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
					if(value != null){
						return value.toString();
					}
					return "";
				}
				@Override
				public Object processArrayValue(Object value, JsonConfig jsonConfig) {
					if(value != null)
						return value.toString();
					return "";
				}
			};
			publicConfig = new JsonConfig();
			publicConfig.registerJsonValueProcessor(java.sql.Date.class,publicProcessor);
			publicConfig.registerJsonValueProcessor(java.util.Date.class,publicProcessor);
		}
		return publicConfig;
	}
	/**
	 * 把json字符串送到页面
	 * 
	 * @param jsonStr
	 * @param response
	 */
	@SuppressWarnings("rawtypes")
	public static void writeToAjax(Map resultMap, HttpServletResponse response) {
		JSONObject jsonObject = JSONObject.fromObject(resultMap,publicConfig());
		AjaxUtil.writeToResponse(jsonObject.toString(), response);
	}
	public static JsonConfig getAddrSearchJsonConfig() {
		JsonConfig config = publicConfig();
		config.setJsonPropertyFilter(new PropertyFilter() {
			public boolean apply(Object source, String name, Object value) {
				if (name.equals("parentAddr") || name.equals("parentList")) {
					return true;
				} else {
					return false;
				}
			}
		});
		return config;
	}
}
