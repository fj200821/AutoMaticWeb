package cn.edu.hpu.autoweb.service.system.systemlogin;

import cn.edu.hpu.autoweb.dao.DaoSupport;
import cn.edu.hpu.autoweb.entity.ExecRecord;
import cn.edu.hpu.autoweb.entity.LoginLog;
import cn.edu.hpu.autoweb.entity.SystemRole;
import cn.edu.hpu.autoweb.entity.SystemUser;
import cn.edu.hpu.autoweb.service.BaseService;
import cn.edu.hpu.autoweb.service.CommonService;
import cn.edu.hpu.autoweb.util.MD5;
import cn.edu.hpu.autoweb.util.PageData;
import cn.edu.hpu.autoweb.util.UuidUtil;
import cn.edu.hpu.autoweb.util.mes.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Aries on 2017/3/19.
 */
@Transactional
@Service
public class SystemLoginService extends BaseService{
    @Autowired
    private DaoSupport daoSupport;
    @Autowired
    private CommonService commonService;
    private final String table_loginLog = "login_log";

    public SystemUser checkUser(PageData param) throws Exception {
        return (SystemUser) daoSupport.findForObject("SystemLoginMapper.queryUserByUserIdAndPassword",param);
    }

    public SystemRole getRoleByLoginId(PageData param) throws Exception {
        Map result = (HashMap)daoSupport.findForObject("SystemRoleMapper.queryRoleByLoginId",param);
        if(result != null){
            return (SystemRole) convertFromMap(SystemRole.class, StringUtils.toLowerMap(result));
        }else{
            return null;
        }
    }

    public void saveLoginLog(String localIp,String userID) throws Exception {
        updateLoginLog(userID);
        LoginLog loginLog = new LoginLog();
        loginLog.setLogIp(localIp);
        loginLog.setUserId(userID);
        loginLog.setLoginDateTime(new Date());
        loginLog.setLogoutDateTime(null);
        commonService.insert(LoginLog.class,loginLog,table_loginLog);
    }

    public void updateLoginLog(String userId) throws Exception {
        daoSupport.update("SystemLoginMapper.updateLogLog",userId);
    }
}
