package cn.edu.hpu.autoweb.service.system.systemlogin;

import cn.edu.hpu.autoweb.dao.DaoSupport;
import cn.edu.hpu.autoweb.service.BaseService;
import cn.edu.hpu.autoweb.service.CommonService;
import cn.edu.hpu.autoweb.util.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by Aries on 2017/3/19.
 */
@Transactional
@Service
public class SystemDictService extends BaseService{
    @Autowired
    private DaoSupport daoSupport;
    @Autowired
    private CommonService commonService;

    public List<Map> queryDictCodeByPCode(PageData pd) throws Exception {
        return ToLowerCaseForList((List<Map>) daoSupport.findForList("SystemDictMapper.queryDictCodeByPCode",pd));
    }

    public List<Map> findIconAll() throws Exception{
        return ToLowerCaseForList((List<Map>)daoSupport.findForList("SystemDictMapper.queryIconAll",null));
    }
}
