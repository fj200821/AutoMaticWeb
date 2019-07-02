package cn.edu.hpu.autoweb.service.system.systemlogin;

import cn.edu.hpu.autoweb.dao.DaoSupport;
import cn.edu.hpu.autoweb.entity.SystemRole;
import cn.edu.hpu.autoweb.entity.SystemRoleUser;
import cn.edu.hpu.autoweb.entity.SystemUser;
import cn.edu.hpu.autoweb.service.BaseService;
import cn.edu.hpu.autoweb.service.CommonService;
import cn.edu.hpu.autoweb.util.MD5;
import cn.edu.hpu.autoweb.util.PageData;
import cn.edu.hpu.autoweb.util.UuidUtil;
import cn.edu.hpu.autoweb.util.mes.DateTimeUtils;
import cn.edu.hpu.autoweb.util.mes.DateUtils;
import cn.edu.hpu.autoweb.util.mes.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * Created by Aries on 2017/4/20.
 */
@Transactional
@Service
public class SystemUserService extends BaseService{

    @Autowired
    private DaoSupport daoSupport;

    @Autowired
    private CommonService commonService;

    private final String idField = "userId";
    private final String table_systemuser = "systemuser";

    public Map querySystemUser(PageData pd) throws Exception {
      return queryDaoDataT("SystemUserMapper","querySystemUser",pd);
    }

    public Map querySystemUserWithRole(PageData pd) throws Exception {
        return queryDaoDataT("SystemUserMapper","querySystemUserWithRole",pd);
    }

    public Map querySystemUserByRoleCode(PageData pd) throws Exception {
        return queryDaoDataT("SystemUserMapper","querySystemUserByRoleCode",pd);
    }

    public Map saveSystemUser(PageData pd) throws Exception {
        SystemUser systemUser = (SystemUser)convertFromMap(SystemUser.class, StringUtils.toLowerMap(pd));
        if(null != systemUser.getPassword()){
            systemUser.setPassword(MD5.md5(systemUser.getPassword()));
        }
        systemUser.setStatus("Used");
        if(null != systemUser.getUserId()){
            SystemUser oldObj = (SystemUser)convertFromMap(SystemUser.class,commonService.findById(systemUser.getUserId(),idField,table_systemuser));
            return commonService.update(SystemUser.class,systemUser,oldObj,table_systemuser,systemUser.getUserId(),idField);
        }else{
            systemUser.setUserId(UuidUtil.get32UUID());
            if(null == systemUser.getPassword()){
                systemUser.setPassword(MD5.md5("123456"));
            }
            return commonService.insert(SystemUser.class,systemUser,table_systemuser);
        }
    }

    public Map findUserByLongidId(String loginId) throws Exception {
        return commonService.findById(loginId,"loginId",table_systemuser);
    }

    public Map createNewCount(PageData pd) throws Exception {
        SystemUser systemUser = new SystemUser();
        systemUser.setUserId(UuidUtil.get32UUID());
        systemUser.setLoginId(pd.get("loginId").toString());
        systemUser.setFirstName(systemUser.getLoginId());
        systemUser.setPassword(MD5.md5(pd.get("password").toString()));
        systemUser.setEmail("test@test.com");
        systemUser.setStatus("Used");
        Date currentDateTime = new Date();
        systemUser.setEffectStartDate(currentDateTime);
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(currentDateTime);
        aCalendar.add(Calendar.DATE, 7);
        systemUser.setEffectDate(aCalendar.getTime());
        commonService.insert(SystemUser.class,systemUser,table_systemuser);

        Map systemRole = StringUtils.toLowerMap(commonService.findById("TestAccount","RoleCode","systemrole"));
        SystemRoleUser systemRoleUser = new SystemRoleUser();
        systemRoleUser.setRoleUserId(UuidUtil.get32UUID());
        systemRoleUser.setRoleId(systemRole.get("roleid").toString());
        systemRoleUser.setUserId(systemUser.getUserId());
        systemRoleUser.setCreatedDate(DateUtils.formatDateTime(new Date()));
        return commonService.insert(SystemRoleUser.class,systemRoleUser,"systemroleuser");
    }

    public Map deleteSystemUser(PageData pd) throws Exception {
        return commonService.delete(pd.get("id").toString(),table_systemuser,idField);
    }
}
