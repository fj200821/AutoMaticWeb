package cn.edu.hpu.autoweb.util;

import org.springframework.context.ApplicationContext;
/**
 * 项目名称：
 * @author:fh
 * 
*/
public class Const {
	public static final String  SESSION_HOME_LANGUAGE="session_home_language";
	public static final String  PACTRAK_WEBSERVICE_URL="https://api.pactrak.com/manifest/aams";
	public static final String SESSION_WEIXIN_OPENID = "wexinOpenID";
	public static final String EZPOST_LOGINID = "zipx";
	public static final String EZPOST_LOGINKEY = "123456";
	public static final String SESSION_SECURITY_CODE = "sessionSecCode";
	public static final String SESSION_USER = "sessionUser";
	public static final String SESSION_CUSTOMER_USER = "sessionCustomerUser";
	public static final String SESSION_ROLE = "sessionRole";
	public static final String SESSION_ALL_MENU_LIST = "allmenuList";		//全部菜单
    public static final String SESSION_VALIDATENUMBER = "validateNumber";
	public static final String SESSION_USERNAME = "USERNAME";			//用户名
	public static final String LOGIN = "/adminlogin";				//登录地址
	public static final String CUSTOMERLOGIN = "/Home/toLogin";				//登录地址
	public static final String SMS1 = "admin/config/SMS1.txt";			//短信账户配置路径1
	public static final String SMS2 = "admin/config/SMS2.txt";			//短信账户配置路径2
	public static final String FILEPATHIMG = "uploadFiles/uploadImgs/";	//图片上传路径
	public static final String FILEPATHFILE = "uploadFiles/file/";		//文件上传路径
	public static final String SAVEIMGURL="admin/config/SAVEIMGURL.txt";//最新的项目所有上传图片的存储路径

	public static final String REDISKEY="REDISKEY_";//最新的项目所有上传图片的存储路径
	public static final String GOODS_REDISKEY="GOODS_KEY_";
	public static final String NO_INTERCEPTOR_PATH = ".*/((adminlogin)|(checkUser)|(logout)|(regist)|(createNewCount)|(sendPhontValidateNumber)).*";	//不对匹配该值的访问路径拦截（正则）
	public static final String NO_INTERCEPTOR_PATH_USER = ".*/((login)|(register)|(logout)|(customer)|(toLogin)).*";
	
	public static ApplicationContext WEB_APP_CONTEXT = null; //该值会在web容器启动时由WebAppContextListener初始化
	
	/**
	 * APP Constants
	 */
	//app注册接口_请求协议参数)
	public static final String[] APP_REGISTERED_PARAM_ARRAY = new String[]{"countries","uname","passwd","title","full_name","company_name","countries_code","area_code","telephone","mobile"};
	public static final String[] APP_REGISTERED_VALUE_ARRAY = new String[]{"国籍","邮箱帐号","密码","称谓","名称","公司名称","国家编号","区号","电话","手机号"};
	
	//app根据用户名获取会员信息接口_请求协议中的参数
	public static final String[] APP_GETAPPUSER_PARAM_ARRAY = new String[]{"USERNAME"};
	public static final String[] APP_GETAPPUSER_VALUE_ARRAY = new String[]{"用户名"};
	

	//邮箱服务器设置数据
	public static final String MAIL_SERVER = "smtp.hgcbizmail.com";
	//服务器账号
	public static final String REQUEST_EMAIL = "cs@zipx.com.hk";
	//服务器密码
	public static final String REQUEST_PWD = "Zipx2016";
	//服务器端口
	public static final String MAIL_PORT = "25";
	
	//设置邮件密送人
	public static final String MAIL_BCC="cs-group@zipx.com.hk";
	
}
