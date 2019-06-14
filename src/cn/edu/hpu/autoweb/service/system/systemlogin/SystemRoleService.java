package cn.edu.hpu.autoweb.service.system.systemlogin;

import cn.edu.hpu.autoweb.dao.DaoSupport;
import cn.edu.hpu.autoweb.entity.SystemRole;
import cn.edu.hpu.autoweb.entity.SystemRoleUser;
import cn.edu.hpu.autoweb.service.BaseService;
import cn.edu.hpu.autoweb.service.CommonService;
import cn.edu.hpu.autoweb.util.PageData;
import cn.edu.hpu.autoweb.util.UuidUtil;
import cn.edu.hpu.autoweb.util.mes.DateUtils;
import cn.edu.hpu.autoweb.util.mes.StringUtils;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Aries on 2017/3/19.
 */
@Transactional
@Service
public class SystemRoleService extends BaseService{
    @Autowired
    private DaoSupport daoSupport;
    @Autowired
    private CommonService commonService;
    private final String idField= "roleId";
    private final String table_systemrole = "systemrole";
    private final String table_systemroleuser = "systemroleuser";

   public Map queryRoleAll(PageData pd) throws Exception {
       return queryDaoDataT("SystemRoleMapper","queryRoleAll",pd);
   }

   public Map findRoleById(PageData pd) throws Exception {
      return StringUtils.toLowerMap(commonService.findById(pd.get("id").toString(),idField,table_systemrole));
   }

   public Map saveRole(PageData pd) throws Exception {
       SystemRole systemRole = (SystemRole)convertFromMap(SystemRole.class,StringUtils.toLowerMap(pd));
       if(null != systemRole.getRoleId()){
           SystemRole oldRole = (SystemRole)convertFromMap(SystemRole.class,StringUtils.toLowerMap(commonService.findById(systemRole.getRoleId(),idField,table_systemrole)));
           return commonService.update(SystemRole.class,systemRole,oldRole,table_systemrole,systemRole.getRoleId(),idField);
       }else {
           systemRole.setRoleId(UuidUtil.get32UUID());
           return commonService.insert(SystemRole.class,systemRole,table_systemrole);
       }
   }

   public Map deleteRole(PageData pd) throws Exception {
       return commonService.delete(pd.get("id").toString(),table_systemrole,idField);
   }

   public Map queryUserByRoleId(PageData pd) throws Exception {
       return queryDaoDataT("SystemRoleMapper","queryUserByRoleId",pd);
   }

   public Map saveRoleUser(PageData pd) throws Exception {
       SystemRoleUser systemRoleUser = (SystemRoleUser)convertFromMap(SystemRoleUser.class,StringUtils.toLowerMap(pd));
       systemRoleUser.setRoleUserId(UuidUtil.get32UUID());
       systemRoleUser.setCreatedDate(DateUtils.formatDateTime(new Date()));
       return commonService.insert(SystemRoleUser.class,systemRoleUser,table_systemroleuser);
   }
    public Map deleteRoleUser(PageData pd) throws Exception {
        JSONArray jsonArray = JSONArray.fromObject(pd.get("ids").toString());
        List<String> list = JSONArray.toList(jsonArray,String.class);
        pd.put("ids",list);
        return wrapAffectedResult((int)daoSupport.delete("SystemRoleMapper.deleteRoleUsers",pd));
    }

}
