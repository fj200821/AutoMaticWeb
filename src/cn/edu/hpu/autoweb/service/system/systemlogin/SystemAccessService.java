package cn.edu.hpu.autoweb.service.system.systemlogin;

import cn.edu.hpu.autoweb.dao.DaoSupport;
import cn.edu.hpu.autoweb.entity.RoleAccess;
import cn.edu.hpu.autoweb.entity.SystemMenu;
import cn.edu.hpu.autoweb.entity.UserAccess;
import cn.edu.hpu.autoweb.service.BaseService;
import cn.edu.hpu.autoweb.service.CommonService;
import cn.edu.hpu.autoweb.util.PageData;
import cn.edu.hpu.autoweb.util.UuidUtil;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Aries on 2017/4/20.
 */
@Transactional
@Service
public class SystemAccessService extends BaseService{

    @Autowired
    private DaoSupport daoSupport;

    @Autowired
    private CommonService commonService;

    private final String table_useraccess = "useraccess";
    private final String table_roleaccess = "roleaccess";


    public SystemMenu getAccessMenuList(PageData pd) throws Exception {
        List<UserAccess> userAccesses = (List<UserAccess>) daoSupport.findForList("SystemAccessMapper.queryUserAccess",pd);
        pd.put("Pid",null);
        SystemMenu rootMenu = new SystemMenu();
        rootMenu = (SystemMenu) daoSupport.findForObject("SystemMenuMapper.queryMenuList",pd);
        rootMenu.setChkDisabled(true);
        pd.put("Pid","-1");
        List<SystemMenu> parentMenus = (List<SystemMenu>) daoSupport.findForList("SystemMenuMapper.queryMenuList",pd);
        List<SystemMenu> childenMenus = new ArrayList<SystemMenu>();
        for(SystemMenu menu : parentMenus){
            pd.put("Pid",menu.getMenuId());
            childenMenus = (List<SystemMenu>) daoSupport.findForList("SystemMenuMapper.queryMenuList",pd);
            for(UserAccess userAccess : userAccesses){
                if(userAccess.getAccessId().equalsIgnoreCase(menu.getMenuId()))
                    menu.setChecked(true);
                for(SystemMenu systemMenu : childenMenus){
                    if(userAccess.getAccessId().equalsIgnoreCase(systemMenu.getMenuId()))
                        systemMenu.setChecked(true);
                }
            }
            menu.setChildrenMenus(childenMenus);
        }
        rootMenu.setChildrenMenus(parentMenus);
        return rootMenu;
    }

    public Map saveAccess(PageData pd) throws Exception {
        JSONArray jsonArray = JSONArray.fromObject(pd.get("ids").toString());
        List<String> list = JSONArray.toList(jsonArray,String.class);
        pd.put("ids",list);
        int count = 0;
        count += (int)daoSupport.delete("SystemAccessMapper.deleteUserAccessByUserId",pd);
        UserAccess userAccess = new UserAccess();
        userAccess.setUserId(pd.get("userid").toString());
        for (String accessid : list){
            userAccess.setUserAccessId(UuidUtil.get32UUID());
            userAccess.setAccessId(accessid);
            count += (int)commonService.simpleInsert(UserAccess.class,userAccess,table_useraccess);
        }
        return wrapAffectedResult(count);
    }


    public SystemMenu getAccessMenuListForRole(PageData pd) throws Exception {
        List<RoleAccess> roleAccesses = (List<RoleAccess>) daoSupport.findForList("SystemAccessMapper.queryRoleAccess",pd);
        pd.put("Pid",null);
        SystemMenu rootMenu = new SystemMenu();
        rootMenu = (SystemMenu) daoSupport.findForObject("SystemMenuMapper.queryMenuList",pd);
        rootMenu.setChkDisabled(true);
        pd.put("Pid","-1");
        List<SystemMenu> parentMenus = (List<SystemMenu>) daoSupport.findForList("SystemMenuMapper.queryMenuList",pd);
        List<SystemMenu> childenMenus = new ArrayList<SystemMenu>();
        for(SystemMenu menu : parentMenus){
            pd.put("Pid",menu.getMenuId());
            childenMenus = (List<SystemMenu>) daoSupport.findForList("SystemMenuMapper.queryMenuList",pd);
            for(RoleAccess roleAccess : roleAccesses){
                if(roleAccess.getAccessId().equalsIgnoreCase(menu.getMenuId()))
                    menu.setChecked(true);
                for(SystemMenu systemMenu : childenMenus){
                    if(roleAccess.getAccessId().equalsIgnoreCase(systemMenu.getMenuId()))
                        systemMenu.setChecked(true);
                }
            }
            menu.setChildrenMenus(childenMenus);
        }
        rootMenu.setChildrenMenus(parentMenus);
        return rootMenu;
    }

    public Map saveRoleAccess(PageData pd) throws Exception {
        JSONArray jsonArray = JSONArray.fromObject(pd.get("ids").toString());
        List<String> list = JSONArray.toList(jsonArray,String.class);
        pd.put("ids",list);
        int count = 0;
        count += (int)daoSupport.delete("SystemAccessMapper.deleteRoleAccessByRoleId",pd);
        RoleAccess roleAccess = new RoleAccess();
        roleAccess.setRoleId(pd.get("roleid").toString());
        for (String accessid : list){
            roleAccess.setRoleAccessId(UuidUtil.get32UUID());
            roleAccess.setAccessId(accessid);
            count += (int)commonService.simpleInsert(RoleAccess.class,roleAccess,table_roleaccess);
        }
        return wrapAffectedResult(count);
    }
}
