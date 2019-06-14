package cn.edu.hpu.autoweb.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 字符串相关方法
 *
 */
public class StringUtil {

	/**
	 * 将以逗号分隔的字符串转换成字符串数组
	 * @param valStr
	 * @return String[]
	 */
	public static String[] StrList(String valStr){
	    int i = 0;
	    String TempStr = valStr;
	    String[] returnStr = new String[valStr.length() + 1 - TempStr.replace(",", "").length()];
	    valStr = valStr + ",";
	    while (valStr.indexOf(',') > 0)
	    {
	        returnStr[i] = valStr.substring(0, valStr.indexOf(','));
	        valStr = valStr.substring(valStr.indexOf(',')+1 , valStr.length());
	        
	        i++;
	    }
	    return returnStr;
	}
	
	public static  String CutLastChar(String str, char ch)
    {
        if (str.lastIndexOf(ch) == str.length() - 1)
        {
            str = str.substring(0, str.length() - 1);

            str = CutLastChar(str, ch);
        }

        return str;
    }
	static String [] arr={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
	public static Date event_date_timetoDate(String s){
		//String s="May  11 2016  3:58PM";
		int month=1;
		String [] dateArr=s.split(" ");
		List<String> dateList=new ArrayList<String>();
		for (int i = 0; i < dateArr.length; i++) {
			if(!dateArr[i].equals("")){
				dateList.add(dateArr[i]);
			}
		}
		for (int j = 0; j < arr.length; j++) {
			if(dateList.get(0).equals(arr[j])){
				month=j+1;
			}
		}
		String str=dateList.get(3);
		String str2=str.substring(str.length()-2, str.length());
		String time=str.substring(0,str.length()-2);
		if(str2.equals("PM")){
			String [] times=time.split(":");
			time=(Integer.valueOf(times[0])+12)+":"+times[1];
		}
		String date=dateList.get(2)+" "+month+" "+dateList.get(1)+" "+time;
		System.out.println(date);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy MM dd hh:mm");
		try {
			Date d=sdf.parse(date);
			return d;
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		return null;
	}


}
