package cn.edu.hpu.autoweb.service.system.automatic;

import cn.edu.hpu.autoweb.dao.DaoSupport;
import cn.edu.hpu.autoweb.service.BaseService;
import cn.edu.hpu.autoweb.service.CommonService;
import cn.edu.hpu.autoweb.util.PageData;
import cn.edu.hpu.autoweb.util.UuidUtil;
import cn.edu.hpu.autoweb.util.mes.DateTimeUtils;
import cn.edu.hpu.autoweb.util.mes.DateUtils;
import cn.edu.hpu.autoweb.util.mes.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Aries on 2017/5/27.
 */
@Transactional
@Service
public class ShopService extends BaseService{
    @Autowired
    private DaoSupport daoSupport;

    @Autowired
    private CommonService commonService;


    public Map queryShop(PageData pd) throws Exception {
        pd.put("today",DateTimeUtils.getCurrentSingleDate());
        return queryDaoDataT("ShopManagerMapper","queryShop",pd);
    }

}
