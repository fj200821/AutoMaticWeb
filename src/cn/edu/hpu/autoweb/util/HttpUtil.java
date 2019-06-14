package cn.edu.hpu.autoweb.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.commons.io.IOUtils;
import net.sf.json.JSONObject;

public class HttpUtil {
	/**
	 * post远程请求
	 * @param xmlInfo
	 * @param URL
	 * @return
	 */
    public static String doHttpPost(String xmlInfo,String URL)
    {         
         System.out.println("发起的数据:"+xmlInfo);       
        byte[] xmlData = xmlInfo.getBytes();            
         InputStream instr  = null; 
          try
          {                          
                 URL url = new URL(URL);                
                 URLConnection urlCon = url.openConnection();               
                 urlCon.setDoOutput(true);              
                 urlCon.setDoInput(true);               
                 urlCon.setUseCaches(false);                            
                 urlCon.setRequestProperty("Content-Type", "text/xml");       
                 urlCon.setRequestProperty("Content-length",String.valueOf(xmlData.length)); 
                 System.out.println(String.valueOf(xmlData.length));
                 DataOutputStream printout = new DataOutputStream(urlCon.getOutputStream());            
                 printout.write(xmlData);               
                 printout.flush();              
                 printout.close();              
                 instr = urlCon.getInputStream();   
                 byte[] bis = IOUtils.toByteArray(instr);
                 String ResponseString = new String(bis, "UTF-8");  
                if ((ResponseString == null) || ("".equals(ResponseString.trim()))) 
                {
                     System.out.println("返回空");
                }
               System.out.println("返回数据为:" + ResponseString);
               return ResponseString;    
          }
          catch(Exception e)
          {              
                e.printStackTrace();
                return "0";
          }             
          finally 
          {             
                 try 
                 {          
                        instr.close();  
                 }
                 catch (Exception ex)
                 {      
                        return "0";
                 }                  
         }                  
    }
    /**
     * 发送https请求
     * @param requestUrl 请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr 提交的数据
     * @return 返回微信服务器响应的信息
     */
	public static JSONObject httpRequst(String requestUrl, String requetMethod, String outputStr) {
		JSONObject jsonobject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的新人管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslcontext = SSLContext.getInstance("SSL", "SunJSSE");
			sslcontext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocktFactory对象
			SSLSocketFactory ssf = sslcontext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requetMethod);

			if ("GET".equalsIgnoreCase(requetMethod))
				httpUrlConn.connect();

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonobject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
		} catch (Exception e) {
		}
		return jsonobject;
	}
}
class MyX509TrustManager implements X509TrustManager {

	public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		// TODO Auto-generated method stub

	}

	public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		// TODO Auto-generated method stub

	}

	public X509Certificate[] getAcceptedIssuers() {
		// TODO Auto-generated method stub
		return null;
	}
}
