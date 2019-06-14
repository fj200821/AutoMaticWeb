package cn.edu.hpu.autoweb.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.util.EntityUtils;

public class text {
	
	public static void main(String[] args) {
	    String host = "http://dm-51.data.aliyun.com";
	    String path = "/rest/160601/ocr/ocr_idcard.json";
	    String method = "POST";
	    String appcode = "9e5f91d9c0a74345888687f9164df41b";
	    Map<String, String> headers = new HashMap<String, String>();
	    headers.put("Authorization", "APPCODE "+ appcode);
	    headers.put("Content-Type", "application/json;charset=UTF-8");
	    Map<String, String> querys = new HashMap<String, String>();

		String imgFile = "C://Users//Aries//Pictures//Saved Pictures//1111.jpg";
		String imgBase64 = "";
		try {
			File file = new File(imgFile);
			byte[] content = new byte[(int) file.length()];
			FileInputStream finputstream = new FileInputStream(file);
			finputstream.read(content);
			finputstream.close();
			imgBase64 = new String(Base64.encodeBase64(content));
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

	    Map bodysMap = new HashMap();
	    List<Map> inputs = new ArrayList<Map>();
	    Map map = new HashMap();
	    Map image = new HashMap();
	    image.put("dataType", 50);
	    image.put("dataValue", imgBase64);
		Map configure = new HashMap();
		configure.put("dataType", 50);
		Map sid = new HashMap();
		sid.put("side","face");
		configure.put("dataValue", sid);

		map.put("image",image);
		map.put("configure",configure);
		inputs.add(map);
		bodysMap.put("inputs",inputs);
		JSONObject jsonObject = new JSONObject();
		String bodys = jsonObject.toJSONString(bodysMap);
	    try {
	    	HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
			System.out.println(EntityUtils.toString(response.getEntity()));
	    	System.out.println(response.toString());
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}

}
