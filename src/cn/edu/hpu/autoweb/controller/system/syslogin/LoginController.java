package cn.edu.hpu.autoweb.controller.system.syslogin;

import cn.edu.hpu.autoweb.controller.base.BaseController;
import cn.edu.hpu.autoweb.entity.SystemMenu;
import cn.edu.hpu.autoweb.entity.SystemRole;
import cn.edu.hpu.autoweb.entity.SystemUser;
import cn.edu.hpu.autoweb.listener.SessionListener;
import cn.edu.hpu.autoweb.service.system.automatic.GoodsService;
import cn.edu.hpu.autoweb.service.system.systemlogin.SystemDictService;
import cn.edu.hpu.autoweb.service.system.systemlogin.SystemLoginService;
import cn.edu.hpu.autoweb.service.system.systemlogin.SystemMenuService;
import cn.edu.hpu.autoweb.service.system.systemlogin.SystemUserService;
import cn.edu.hpu.autoweb.util.*;
import cn.edu.hpu.autoweb.util.mes.DateUtils;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by Aries on 2017/3/16.
 */
@Controller
public class LoginController extends BaseController{
    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private SystemLoginService systemLoginService;
    @Autowired
    private SystemMenuService systemMenuService;
    @Autowired
    private SystemDictService systemDictService;
    @Autowired
    private SystemUserService systemUserService;
    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/adminlogin")
    public ModelAndView toLogin(){
        ModelAndView mv = new ModelAndView("system/admin/login");
        return mv;
    }

    @RequestMapping("/regist")
    public ModelAndView toRegist(){
        ModelAndView mv = new ModelAndView("system/admin/regist");
        return mv;
    }

    @RequestMapping("/checkUser")
    @ResponseBody
    public String CheckUser(){
        JSONObject json = new JSONObject();
        try{
            HttpSession session = getRequest().getSession();
//            if(session.getAttribute(Const.SESSION_USER) != null){
//                return wrapperException(new Exception("该账号已登录！。"),json);
//            }
            PageData pd = getPageData();
            if(null != SessionListener.sessionMap.get(pd.get("userName"))){
                HttpSession session2 = SessionListener.sessionMap.get(pd.get("userName"));
                SessionListener.sessionMap.remove(pd.get("userName"));
                Enumeration e = session2.getAttributeNames();
                while(e.hasMoreElements()){
                    String sessionName = (String) e.nextElement();
                    session2.removeAttribute(sessionName);
                }
                session2.invalidate();
            }
            pd.put("password", MD5.md5(pd.get("password").toString()));
            SystemUser user = systemLoginService.checkUser(pd);
            if(user == null){
                return wrapperException(new Exception("登录失败，用户名或密码不存在。"),json);
            }
            SystemRole role = systemLoginService.getRoleByLoginId(pd);
            if(role == null){
                return wrapperException(new Exception("登录失败，该账户尚未初始化完成，请联系客服，微信号：17362125457。"),json);
            }
            if(!role.getRoleCode().equalsIgnoreCase("Admin")){
                if(DateUtils.milliOfTwo_2(DateUtils.getCurrentDate(),user.getEffectDate()) <= 0){
                    return wrapperException(new Exception("登录失败，会员已到期，请于管理员联系。"),json);
                }
            }
            //登录成功，写入日志
            String localIp = this.getClientIp(getRequest());//获取本地ip
            systemLoginService.saveLoginLog(localIp,user.getUserId());
            session.setAttribute(Const.SESSION_USER,user);
            session.setAttribute(Const.SESSION_ROLE,role);
            List<SystemMenu> menus = systemMenuService.getMenuListByRoleIdAndUserId(role.getRoleId(),user.getUserId());
            getRequest().getSession().setAttribute(Const.SESSION_ALL_MENU_LIST,menus);
            SessionListener.sessionMap.put(pd.get("userName").toString(),session);
           return wrapperSuccess(new PageData(),json);
        }
        catch (Exception e){
            logger.error("LoginController::CheckUser catch exception:",e);
            return wrapperException(e,json);
        }
    }

    @RequestMapping("/main/index")
    public ModelAndView toMainIndex(){
        ModelAndView mv = new ModelAndView();
        SystemUser user = (SystemUser) getRequest().getSession().getAttribute(Const.SESSION_USER);
        SystemRole role = (SystemRole) getRequest().getSession().getAttribute(Const.SESSION_ROLE);
        try {
//            List<String> iconList = readTxtFile("F:\\MySVN\\Broadband\\branches\\icon.txt");
//            systemMenuService.insertIcon(iconList);
//            List<Map> iconList = systemDictService.findIconAll();
            mv.addObject("User",user);
//            mv.addObject("iconList",iconList);
            PageData pd = new PageData();
//            if(role.getRoleName().equals("Shoper")){
//                pd.put("loginid",((SystemUser)getRequest().getSession().getAttribute(Const.SESSION_USER)).getLoginId());
//            }
            Map result = goodsService.getIndexInfo(pd);
            mv.addObject("result",result);
            mv.setViewName("system/admin/home");
        } catch (Exception e) {
            logger.error("LoginController::toMainIndex catch exception:",e);
            mv.setViewName("system/admin/login");
        }
        return mv;
    }

    @RequestMapping("/loginOut")
    public String loginOut() throws Exception{
        HttpSession session  = getRequest().getSession();
//        SystemUser user = (SystemUser)session.getAttribute(Const.SESSION_USER);
//        systemLoginService.updateLoginLog(user.getUserId());
//        session.removeAttribute(Const.SESSION_ROLE);
//        session.removeAttribute(Const.SESSION_USER);
        session.invalidate();
//        SessionListener.sessionMap.remove(user.getLoginId());
        return "forward:adminlogin";
    }

    public List<String> readTxtFile(String filePath){
        List<String> list = new ArrayList<String>();
        try {
            String encoding="GBK";
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                    if(!lineTxt.trim().equals("")){
                        list.add(lineTxt.trim());
                    }
                }
                read.close();
            }else{
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return list;
    }

    @RequestMapping("/createNewCount")
    @ResponseBody
    public String createNewCount(){
        JSONObject json = new JSONObject();
        try{
            PageData pd = getPageData();
            HttpSession session  = getRequest().getSession();
            int validateNmber = (int) session.getAttribute(Const.SESSION_VALIDATENUMBER);
            if(validateNmber != Integer.valueOf(pd.get("validateNumber").toString())){
                return wrapperException(new Exception("注册失败，验证码错误。"),json);
            }
            Map user = systemUserService.findUserByLongidId(pd.get("loginId").toString());
            if(user != null){
                return wrapperException(new Exception("注册失败，该账号已被注册。如需继续开通会员，请与客服联系，微信号：17362125457。"),json);
            }
            session.removeAttribute(Const.SESSION_VALIDATENUMBER);
            return wrapperSuccess(systemUserService.createNewCount(pd),json);
        }catch (Exception e){
            logger.error("LoginController::createNewCount catch exception:", e);
            return wrapperException(e,json);
        }
    }

    @RequestMapping("/sendPhontValidateNumber")
    @ResponseBody
    public String sendPhontValidateNumber(){
        JSONObject json = new JSONObject();
        try{
            PageData pd = getPageData();
            String phoneNumber = pd.getString("phone");
            int newCode = AliPhoneMessageUtil.getNewCode();
            HttpSession session  = getRequest().getSession();
            session.setAttribute(Const.SESSION_VALIDATENUMBER,newCode);
            SendSmsResponse response = AliPhoneMessageUtil.sendSms(phoneNumber,newCode);
            if(!response.getCode().equals("OK")){
                return wrapperException(new Exception(response.getMessage()),json);
            }
            return wrapperSuccess(new HashMap(),json);
        }catch (Exception e){
            logger.error("LoginController::sendPhontValidateNumber catch exception:", e);
            return wrapperException(e,json);
        }
    }
}
