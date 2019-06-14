package cn.edu.hpu.autoweb.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 将时间转化为字符串当做订单编号
 * @author Administrator
 *
 */

public class DateToString {
	private static SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmssSSS");
	
	private volatile static DateToString _instance;
	
	private DateToString(){
	}
	
	public static DateToString getInstance(){
		if(_instance == null){
			synchronized (DateToString.class){
				if(_instance == null){
					_instance = new DateToString();
				}
			}
		}
		
		return _instance;
	}
	
	public synchronized String getOrderNumber(){
		//生成订单号精确到毫秒
        Date date = new Date();
        String orderNumber = format.format(date);
        return orderNumber;
	}

}
