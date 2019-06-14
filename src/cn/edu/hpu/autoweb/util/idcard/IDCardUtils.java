package cn.edu.hpu.autoweb.util.idcard;

import cn.edu.hpu.autoweb.util.idcard.constant.Constants;
import cn.edu.hpu.autoweb.util.idcard.util.CommonUtil;
import cn.edu.hpu.autoweb.util.idcard.util.MessageDigestUtil;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Aries on 2017/5/26.
 */

//图像识别
public  class IDCardUtils {
    private final static String APP_KEY = "23869367";
    // APP密钥
    private final static String APP_SECRET = "90fd05583ff8f7ded39568ad7fb1e438";
    //测试图片位置
//    private final static String basePath = "C:\\Users\\Aries\\Pictures\\Saved Pictures\\1111.jpg";
    //API域名
    private final static String HOST = "dm-51.data.aliyun.com";
    //url
    private final static String URL = "/rest/160601/ocr/ocr_idcard.json";
    //自定义参与签名Header前缀（可选,默认只有"X-Ca-"开头的参与到Header签名）
    private final static List<String> CUSTOM_HEADERS_TO_SIGN_PREFIX = new ArrayList<String>();

    public static Map getIDMessage(String basePath)throws Exception{
        CUSTOM_HEADERS_TO_SIGN_PREFIX.add("Custom");
        CommonUtil commonUtil = new CommonUtil(HOST,URL,APP_KEY,APP_SECRET,CUSTOM_HEADERS_TO_SIGN_PREFIX,basePath);
        HttpResponse response = commonUtil.sendPostRequestWithBody();
        Map map = new HashMap();
        StringBuilder sb = new StringBuilder();
        sb.append(response.getStatusLine().getStatusCode()).append(Constants.LF);
        for (Header header : response.getAllHeaders()) {
            sb.append(MessageDigestUtil.iso88591ToUtf8(header.getValue())).append(Constants.LF);
        }
        sb.append(CommonUtil.readStreamAsStr(response.getEntity().getContent())).append(Constants.LF);
        System.out.println("The response is:" + sb.toString());
        String s = sb.toString();
        s = s.substring(s.lastIndexOf("*")+1);
        JSONObject resultObj = new JSONObject(s);
        JSONArray outputArray = resultObj.getJSONArray("outputs");
        String output = outputArray.getJSONObject(0).getJSONObject("outputValue").getString("dataValue"); // 取出结果json字符串
        JSONObject out = new JSONObject(output);
        if (out.getBoolean("success")) {
            map.put("result","success");
            map.put("address",out.getString("address"));
            map.put("name",out.getString("name"));
            map.put("num",out.getString("num"));
        } else {
            map.put("result","error");
        }
        return map;
    }
}
