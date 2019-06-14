package cn.edu.hpu.autoweb.service.system.automatic;

import cn.edu.hpu.autoweb.dao.DaoSupport;
import cn.edu.hpu.autoweb.entity.Categorys;
import cn.edu.hpu.autoweb.service.BaseService;
import cn.edu.hpu.autoweb.service.CommonService;
import cn.edu.hpu.autoweb.util.PageData;
import cn.edu.hpu.autoweb.util.mes.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Aries on 2017/5/27.
 */
@Transactional
@Service
public class CategoryService extends BaseService{
    @Autowired
    private DaoSupport daoSupport;

    @Autowired
    private CommonService commonService;

    @Autowired
    private AutoExecService autoExecService;


    public Map queryCategoryByParentID(PageData pd) throws Exception {
        return wrapClientResultLowerCase((List<Map>) daoSupport.findForList("CategoryManagerMapper.queryCategoryByParentID",pd));
    }

}
