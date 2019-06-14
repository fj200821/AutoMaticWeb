package cn.edu.hpu.autoweb.controller.system.basic;

import cn.edu.hpu.autoweb.controller.base.BaseController;
import cn.edu.hpu.autoweb.entity.SystemMenu;
import cn.edu.hpu.autoweb.entity.SystemUser;
import cn.edu.hpu.autoweb.service.system.systemlogin.SystemAccessService;
import cn.edu.hpu.autoweb.service.system.systemlogin.SystemMenuService;
import cn.edu.hpu.autoweb.service.system.systemlogin.SystemRoleService;
import cn.edu.hpu.autoweb.service.system.systemlogin.SystemUserService;
import cn.edu.hpu.autoweb.util.Const;
import cn.edu.hpu.autoweb.util.PageData;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Aries on 2017/3/29.
 */
@Controller
@RequestMapping("/Basic/Setting")
public class SettingController extends BaseController{
    private Logger logger = LoggerFactory.getLogger(SettingController.class);

    @Autowired
    private SystemMenuService systemMenuService;

    @Autowired
    private SystemRoleService systemRoleService;

    @Autowired
    private SystemUserService systemUserService;

    @Autowired
    private SystemAccessService systemAccessService;

    @RequestMapping("/MenuMgr")
    public ModelAndView creatMenuManager(){
        ModelAndView mv = new ModelAndView("system/setting/menuManager");
        return mv;
    }

    @RequestMapping("/MenuMgr/queryMenuAll")
    @ResponseBody
    public String queryMenuAll(){
        JSONObject json = new JSONObject();
        try{
            return json.toJSONString(systemMenuService.getMenuList());
        }catch (Exception e){
            logger.error("SettingController::queryMenuAll catch exception:", e);
            return wrapperException(e,json);
        }
    }

    @RequestMapping(value = "/MenuMgr/findMenuByParentId",
            method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String findMenuByParentId(){
        JSONObject json = new JSONObject();
        try{
            PageData pd = getPageData();
            return wrapperSuccess(systemMenuService.findMenuByParentId(pd),json);
        }catch (Exception e){
            logger.error("SettingController::findMenuByParentId catch exception:", e);
            return wrapperException(e,json);
        }
    }

    @RequestMapping(value = "/MenuMgr/findParentListByMenuId",
            method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String findParentListByMenuId(){
        JSONObject json = new JSONObject();
        try{
            PageData pd = getPageData();
            return json.toJSONString(systemMenuService.findParentListByMenuId(pd));
        }catch (Exception e){
            logger.error("SettingController::findParentListByMenuId catch exception:", e);
            return wrapperException(e,json);
        }
    }

    @RequestMapping(value = "/MenuMgr/save",
            method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String saveMenu(){
        JSONObject json = new JSONObject();
        try{
            PageData pd = getPageData();
            return wrapperSuccess(systemMenuService.saveMenu(pd),json);
        }catch (Exception e){
            logger.error("SettingController::saveMenu catch exception:", e);
            return wrapperException(e,json);
        }
    }

    @RequestMapping(value = "/MenuMgr/delete",
            method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String deleteMenu(){
        JSONObject json = new JSONObject();
        try{
            PageData pd = getPageData();
            return wrapperSuccess(systemMenuService.deleteMenu(pd),json);
        }catch (Exception e){
            logger.error("SettingController::deleteMenu catch exception:", e);
            return wrapperException(e,json);
        }
    }

    @RequestMapping(value = "/MenuMgr/startMenu",
            method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String startMenu(){
        JSONObject json = new JSONObject();
        try{
            PageData pd = getPageData();
            return wrapperSuccess(systemMenuService.startMenu(pd),json);
        }catch (Exception e){
            logger.error("SettingController::startMenu catch exception:", e);
            return wrapperException(e,json);
        }
    }

    @RequestMapping(value = "/MenuMgr/stopMenu",
            method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String stopMenu(){
        JSONObject json = new JSONObject();
        try{
            PageData pd = getPageData();
            return wrapperSuccess(systemMenuService.stopMenu(pd),json);
        }catch (Exception e){
            logger.error("SettingController::stopMenu catch exception:", e);
            return wrapperException(e,json);
        }
    }

    //角色管理
    @RequestMapping("/RoleMgr")
    public ModelAndView creatRoleManager(){
        ModelAndView mv = new ModelAndView("system/setting/roleManager");
        return mv;
    }

    @RequestMapping("/RoleMgr/queryRoleAll")
    @ResponseBody
    public String queryRoleAll(){
        JSONObject json = new JSONObject();
        try{
            return wrapperSuccess(systemRoleService.queryRoleAll(getPageData()),json);
        }catch (Exception e){
            logger.error("SettingController::queryRoleAll catch exception:", e);
            return wrapperException(e,json);
        }
    }

    @RequestMapping("/RoleMgr/queryUserByRoleId")
    @ResponseBody
    public String queryUserByRoleId(){
        JSONObject json = new JSONObject();
        try{
            return wrapperSuccess(systemRoleService.queryUserByRoleId(getPageData()),json);
        }catch (Exception e){
            logger.error("SettingController::queryUserByRoleId catch exception:", e);
            return wrapperException(e,json);
        }
    }

    @RequestMapping("/RoleMgr/save")
    @ResponseBody
    public String saveRole(){
        JSONObject json = new JSONObject();
        try{
            PageData pd = getPageData();
            return wrapperSuccess(systemRoleService.saveRole(pd),json);
        }catch (Exception e){
            logger.error("SettingController::saveRole catch exception:", e);
            return wrapperException(e,json);
        }
    }

    @RequestMapping("/RoleMgr/delete")
    @ResponseBody
    public String deleteRole(){
        JSONObject json = new JSONObject();
        try{
            PageData pd = getPageData();
            return wrapperSuccess(systemRoleService.deleteRole(pd),json);
        }catch (Exception e){
            logger.error("SettingController::deleteRole catch exception:", e);
            return wrapperException(e,json);
        }
    }

    @RequestMapping("/RoleMgr/saveRoleUser")
    @ResponseBody
    public String saveRoleUser(){
        JSONObject json = new JSONObject();
        try{
            PageData pd = getPageData();
            pd.put("creatBy",((SystemUser)this.getRequest().getSession().getAttribute(Const.SESSION_USER)).getFirstName());
            return wrapperSuccess(systemRoleService.saveRoleUser(pd),json);
        }catch (Exception e){
            logger.error("SettingController::saveRoleUser catch exception:", e);
            return wrapperException(e,json);
        }
    }

    @RequestMapping("/RoleMgr/deleteRoleUser")
    @ResponseBody
    public String deleteRoleUser(){
        JSONObject json = new JSONObject();
        try{
            PageData pd = getPageData();
            return wrapperSuccess(systemRoleService.deleteRoleUser(pd),json);
        }catch (Exception e){
            logger.error("SettingController::deleteRoleUser catch exception:", e);
            return wrapperException(e,json);
        }
    }

    @RequestMapping("/UserMgr/querySystemUser")
    @ResponseBody
    public String querySystemUser(){
        JSONObject json = new JSONObject();
        try{
            return wrapperSuccess(systemUserService.querySystemUserWithRole(getPageData()),json);
        }catch (Exception e){
            logger.error("SettingController::querySystemUser catch exception:", e);
            return wrapperException(e,json);
        }
    }

    //授权管理
    @RequestMapping("/AccessMgr/queryAuthorityAccessByUserId")
    public ModelAndView queryAuthorityAccessByUserId(){
        ModelAndView mv = new ModelAndView("system/setting/userAccessManager");
        SystemMenu systemMenu = new SystemMenu();
        JSONObject jsonObject = new JSONObject();
        try{
            systemMenu = systemAccessService.getAccessMenuList(getPageData());
        }catch (Exception e){
            logger.error("SettingController::querySystemUser catch exception:", e);
        }
        mv.addObject("systemMenu",jsonObject.toJSONString(systemMenu));
        mv.addObject("userid",getPageData().get("userid").toString());
        return mv;
    }

    @RequestMapping("/AccessMgr/save")
    @ResponseBody
    public String saveAccess(){
        JSONObject json = new JSONObject();
        try{
            PageData pd = getPageData();
            return wrapperSuccess(systemAccessService.saveAccess(pd),json);
        }catch (Exception e){
            logger.error("SettingController::saveAccess catch exception:", e);
            return wrapperException(e,json);
        }
    }


    @RequestMapping("/AccessMgr/queryAuthorityAccessByRoleId")
    public ModelAndView queryAuthorityAccessByRoleId(){
        ModelAndView mv = new ModelAndView("system/setting/roleAccessManager");
        SystemMenu systemMenu = new SystemMenu();
        JSONObject jsonObject = new JSONObject();
        try{
            systemMenu = systemAccessService.getAccessMenuListForRole(getPageData());
        }catch (Exception e){
            logger.error("SettingController::queryAuthorityAccessByRoleId catch exception:", e);
        }
        mv.addObject("systemMenu",jsonObject.toJSONString(systemMenu));
        mv.addObject("roleid",getPageData().get("roleid").toString());
        return mv;
    }

    @RequestMapping("/AccessMgr/saveRoleAccess")
    @ResponseBody
    public String saveRoleAccess(){
        JSONObject json = new JSONObject();
        try{
            PageData pd = getPageData();
            return wrapperSuccess(systemAccessService.saveRoleAccess(pd),json);
        }catch (Exception e){
            logger.error("SettingController::saveRoleAccess catch exception:", e);
            return wrapperException(e,json);
        }
    }

    //用户管理
    @RequestMapping("/SystemUserMgr")
    public ModelAndView creatSystemUserMgr(){
        ModelAndView mv = new ModelAndView("system/setting/systemUserManager");
        return mv;
    }

    @RequestMapping("/SystemUserMgr/queryUserAll")
    @ResponseBody
    public String queryUserAll(){
        JSONObject json = new JSONObject();
        try{
            PageData pd = getPageData();
            return wrapperSuccess(systemUserService.querySystemUser(pd),json);
        }catch (Exception e){
            logger.error("SettingController::queryUserAll catch exception:", e);
            return wrapperException(e,json);
        }
    }

    @RequestMapping("/SystemUserMgr/queryUserByRoleCode")
    @ResponseBody
    public String queryUserByRoleCode(){
        JSONObject json = new JSONObject();
        try{
            PageData pd = getPageData();
            return wrapperSuccess(systemUserService.querySystemUserByRoleCode(pd),json);
        }catch (Exception e){
            logger.error("SettingController::queryUserByRoleCode catch exception:", e);
            return wrapperException(e,json);
        }
    }

    @RequestMapping("/SystemUserMgr/save")
    @ResponseBody
    public String saveSystemUser(){
        JSONObject json = new JSONObject();
        try{
            PageData pd = getPageData();
            return wrapperSuccess(systemUserService.saveSystemUser(pd),json);
        }catch (Exception e){
            logger.error("SettingController::saveSystemUser catch exception:", e);
            return wrapperException(e,json);
        }
    }

    @RequestMapping("/SystemUserMgr/delete")
    @ResponseBody
    public String deleteSystemUser(){
        JSONObject json = new JSONObject();
        try{
            PageData pd = getPageData();
            return wrapperSuccess(systemUserService.deleteSystemUser(pd),json);
        }catch (Exception e){
            logger.error("SettingController::deleteSystemUser catch exception:", e);
            return wrapperException(e,json);
        }
    }
}
