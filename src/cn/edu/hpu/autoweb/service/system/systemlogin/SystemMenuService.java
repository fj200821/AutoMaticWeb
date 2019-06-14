package cn.edu.hpu.autoweb.service.system.systemlogin;

import cn.edu.hpu.autoweb.dao.DaoSupport;
import cn.edu.hpu.autoweb.entity.SystemMenu;
import cn.edu.hpu.autoweb.service.BaseService;
import cn.edu.hpu.autoweb.service.CommonService;
import cn.edu.hpu.autoweb.util.PageData;
import cn.edu.hpu.autoweb.util.UuidUtil;
import cn.edu.hpu.autoweb.util.mes.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Aries on 2017/3/19.
 */
@Transactional
@Service
public class SystemMenuService extends BaseService{
    @Autowired
    private DaoSupport daoSupport;
    @Autowired
    private CommonService commonService;

    private final String table_systemmenu = "systemmenu";

    public List<SystemMenu> getMenuListByUserId(String userId) throws Exception {
        PageData pd = new PageData();
        pd.put("userId",userId);
        pd.put("Pid",null);
        List<SystemMenu> parentMenus = (List<SystemMenu>) daoSupport.findForList("SystemMenuMapper.queryMenuListByUserId",pd);
        List<SystemMenu> childenMenus = new ArrayList<SystemMenu>();
        for(SystemMenu menu : parentMenus){
            pd.put("Pid",menu.getMenuId());
            childenMenus = (List<SystemMenu>) daoSupport.findForList("SystemMenuMapper.queryMenuListByUserId",pd);
            menu.setChildrenMenus(childenMenus);
        }
        return parentMenus;
    }

    public List<SystemMenu> getMenuListByRoleId(String roleId) throws Exception {
        PageData pd = new PageData();
        pd.put("roleId",roleId);
        pd.put("Pid",null);
        List<SystemMenu> parentMenus = (List<SystemMenu>) daoSupport.findForList("SystemMenuMapper.queryMenuListByRoleId",pd);
        List<SystemMenu> childenMenus = new ArrayList<SystemMenu>();
        for(SystemMenu menu : parentMenus){
            pd.put("Pid",menu.getMenuId());
            childenMenus = (List<SystemMenu>) daoSupport.findForList("SystemMenuMapper.queryMenuListByRoleId",pd);
            menu.setChildrenMenus(childenMenus);
        }
        return parentMenus;
    }


    public List<SystemMenu> getMenuListByRoleIdAndUserId(String roleId,String userId) throws Exception {
        PageData pd = new PageData();
        pd.put("userId",userId);
        pd.put("roleId",roleId);
        pd.put("Pid",null);
        List<SystemMenu> parentMenus = (List<SystemMenu>) daoSupport.findForList("SystemMenuMapper.queryMenuListByRoleIdAndUserId",pd);
        List<SystemMenu> childenMenus = new ArrayList<SystemMenu>();
        for(SystemMenu menu : parentMenus){
            pd.put("Pid",menu.getMenuId());
            childenMenus = (List<SystemMenu>) daoSupport.findForList("SystemMenuMapper.queryMenuListByRoleIdAndUserId",pd);
            menu.setChildrenMenus(childenMenus);
        }
        return parentMenus;
    }

    public SystemMenu getMenuList() throws Exception {
        PageData pd = new PageData();
        pd.put("Pid",null);
        SystemMenu rootMenu = (SystemMenu) daoSupport.findForObject("SystemMenuMapper.queryMenuList",pd);
        pd.put("Pid","-1");
        List<SystemMenu> parentMenus = (List<SystemMenu>) daoSupport.findForList("SystemMenuMapper.queryMenuList",pd);
        List<SystemMenu> childenMenus = new ArrayList<SystemMenu>();
        for(SystemMenu menu : parentMenus){
            pd.put("Pid",menu.getMenuId());
            childenMenus = (List<SystemMenu>) daoSupport.findForList("SystemMenuMapper.queryMenuList",pd);
            menu.setChildrenMenus(childenMenus);
        }
        rootMenu.setChildrenMenus(parentMenus);
        return rootMenu;
    }

    public Map findMenuByParentId(PageData pd) throws Exception {
        return queryDaoDataT("SystemMenuMapper","queryMenuListForMap",pd);
    }

    public List findParentListByMenuId(PageData pd) throws Exception{
        return ToLowerCaseForList((List<Map>)daoSupport.findForList("SystemMenuMapper.queryParentListByMenuId",pd));
    }


    public void insertIcon(List<String> list) throws Exception{
        for (String iconName : list){
            PageData pd = new PageData();
            pd.put("menuName","fa "+iconName);
            daoSupport.save("SystemMenuMapper.insertIcon",pd);
        }
    }

    public Map saveMenu(PageData pd) throws Exception {
        SystemMenu menu = (SystemMenu) convertFromMap(SystemMenu.class,StringUtils.toLowerMap(pd));
        SystemMenu parent = (SystemMenu) convertFromMap(SystemMenu.class,StringUtils.toLowerMap(commonService.findById(menu.getPId(),"menuId",table_systemmenu)));
        menu.setPCode(parent.getMenuCode());
        if(null != menu.getMenuId()){
            SystemMenu oldMenu = (SystemMenu) convertFromMap(SystemMenu.class,StringUtils.toLowerMap(commonService.findById(menu.getMenuId(),"menuId",table_systemmenu)));
            return commonService.update(SystemMenu.class,menu,oldMenu,table_systemmenu,menu.getMenuId(),"menuId");
        }else {
            menu.setMenuId(UuidUtil.get32UUID());
            return commonService.insert(SystemMenu.class,menu,table_systemmenu);
        }
    }

    public Map deleteMenu(PageData pd) throws Exception {
        return commonService.delete(pd.get("id").toString(),table_systemmenu,"menuId");
    }

    public Map startMenu(PageData pd) throws Exception {
        pd.put("status","Used");
        return wrapAffectedResult((int)daoSupport.update("SystemMenuMapper.updateMenuStatus",pd));
    }

    public Map stopMenu(PageData pd) throws Exception {
        pd.put("status","UnUsed");
        return wrapAffectedResult((int)daoSupport.update("SystemMenuMapper.updateMenuStatus",pd));
    }
}
