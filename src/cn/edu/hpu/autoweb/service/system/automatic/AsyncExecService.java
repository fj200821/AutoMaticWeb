package cn.edu.hpu.autoweb.service.system.automatic;

import cn.edu.hpu.autoweb.dao.DaoSupport;
import cn.edu.hpu.autoweb.entity.ExecRecord;
import cn.edu.hpu.autoweb.service.BaseService;
import cn.edu.hpu.autoweb.service.CommonService;
import cn.edu.hpu.autoweb.util.Const;
import cn.edu.hpu.autoweb.util.PageData;
import cn.edu.hpu.autoweb.util.RedisUtil;
import cn.edu.hpu.autoweb.util.mes.ConfigReader;
import cn.edu.hpu.autoweb.util.mes.DateTimeUtils;
import cn.edu.hpu.autoweb.util.mes.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

@Transactional
@Service
@EnableAsync
public class AsyncExecService extends BaseService {

    @Autowired
    private CommonService commonService;
    @Autowired
    private DaoSupport daoSupport;
    @Autowired
    private RedisUtil redisUtil;

    private Logger logger = LoggerFactory.getLogger(AsyncExecService.class);

    private String[] sorts = {""};//"sell_num","add_num","query_add_num","edit_time"
    private String[] orders = {"asc"};//,"desc"

    @Async
    public void refreshRedis() throws Exception {
        Calendar canlendar = Calendar.getInstance();
        canlendar.add(Calendar.DATE, -1);
        Date date = canlendar.getTime();

        PageData ordersPage1 = new PageData();
        ordersPage1.put("firstCategory_id","");
        ordersPage1.put("secondCategory_id","");
        ordersPage1.put("rows",10);
        ordersPage1.put("order","asc");
        ordersPage1.put("createStartDateTime","");
        ordersPage1.put("page",0);
        setGoodsRedis(ordersPage1);

        for(String sort : sorts){
            for(String order : orders){
                for(int i=0;i<=2;i++){
                    PageData ordersPage = new PageData();
                    ordersPage.put("firstCategory_id","");
                    ordersPage.put("secondCategory_id","");
                    ordersPage.put("rows",10);
                    if(!sort.equals("")){
                        ordersPage.put("sort",sort);
                    }
                    ordersPage.put("order",order);
                    ordersPage.put("startdatetime",DateTimeUtils.dateTimeFormattedEN(DateTimeUtils.getStartOfDay(date)) );
                    ordersPage.put("enddatetime",DateTimeUtils.dateTimeFormattedEN(DateTimeUtils.getEndOfDay(date)));
                    ordersPage.put("page",i);
                    setGoodsRedis(ordersPage);
                }
                if(sort.equals("")){
                    break;
                }
            }
        }
        //商品页面
        PageData pd = new PageData();
        pd.put("parentId",0);
        List<Map> parentCategorys = (List<Map>) daoSupport.findForList("CategoryManagerMapper.queryCategoryByParentID",pd);
        for(Map parnent : parentCategorys){
            for(String sort : sorts){
                for(String order : orders){
                    for(int i=0;i<=0;i++){
                        PageData ordersPage = new PageData();
                        ordersPage.put("firstCategory_id",parnent.get("id"));
                        ordersPage.put("secondCategory_id","");
                        ordersPage.put("rows",10);
                        if(!sort.equals("")){
                            ordersPage.put("sort",sort);
                        }
                        ordersPage.put("order",order);
                        ordersPage.put("startdatetime",DateTimeUtils.dateTimeFormattedEN(DateTimeUtils.getStartOfDay(date)) );
                        ordersPage.put("enddatetime",DateTimeUtils.dateTimeFormattedEN(DateTimeUtils.getEndOfDay(date)));
                        ordersPage.put("page",i);
                        setGoodsRedis(ordersPage);
                    }
                    if(sort.equals("")){
                        break;
                    }
                }
            }
            pd.put("parentId",parnent.get("id"));
            List<Map> categorys = (List<Map>) daoSupport.findForList("CategoryManagerMapper.queryCategoryByParentID",pd);
            for(Map category : categorys){
                for(String sort : sorts) {
                    for (String order : orders) {
                        for(int i=0;i<=0;i++){
                            PageData ordersCategoryPage = new PageData();
                            ordersCategoryPage.put("firstCategory_id",parnent.get("id"));
                            ordersCategoryPage.put("secondCategory_id",category.get("id"));
                            ordersCategoryPage.put("rows",10);
                            if(!sort.equals("")){
                                ordersCategoryPage.put("sort",sort);
                            }
                            ordersCategoryPage.put("order",order);
                            ordersCategoryPage.put("startdatetime",DateTimeUtils.dateTimeFormattedEN(DateTimeUtils.getStartOfDay(date)));
                            ordersCategoryPage.put("enddatetime",DateTimeUtils.dateTimeFormattedEN(DateTimeUtils.getEndOfDay(date)));
                            ordersCategoryPage.put("page",i);
                            setGoodsRedis(ordersCategoryPage);
                        }
                        if(sort.equals("")){
                            break;
                        }
                    }
                }
            }
        }
    }

    public void setGoodsRedis(PageData pd) throws Exception {
        String redisKey = getGoodsRedisKey(pd);
        System.out.println("执行:"+redisKey);
        getGoods(redisKey,pd);
    }


    @Async
    public void loadGoodsRedis(PageData pd) throws Exception {
        String redisKey = getGoodsRedisKey(pd);
        System.out.println("执行:"+redisKey);
        getGoods(redisKey,pd);
    }

    public String getGoodsRedisKey(PageData pd){
        String rediskey = Const.GOODS_REDISKEY;
        if (pd.containsKey("rows")) {
            rediskey += pd.get("rows").toString();
        }
        if (pd.containsKey("page")) {
            rediskey += pd.get("page").toString();
        }
        if (pd.containsKey("sort")) {
            rediskey += pd.get("sort").toString();
        }
        if (pd.containsKey("order")) {
            rediskey += pd.get("order").toString();
        }
        if (pd.containsKey("firstCategory_id")) {
            rediskey += pd.get("firstCategory_id").toString();
        }
        if (pd.containsKey("secondCategory_id")) {
            rediskey += pd.get("secondCategory_id").toString();
        }
        if (pd.containsKey("startdatetime")) {
            rediskey += pd.get("startdatetime").toString();
        }
        if (pd.containsKey("enddatetime")) {
            rediskey += pd.get("enddatetime").toString();
        }
        if (pd.containsKey("goods_name")) {
            rediskey += pd.get("goods_name").toString();
        }
        if (pd.containsKey("createStartDateTime")) {
            rediskey += pd.get("createStartDateTime").toString();
        }
        return rediskey;
    }

    public Map getGoods(String rediskey,PageData pd) throws Exception {
        pd.put("today", DateTimeUtils.getCurrentSingleDate());
        if (!pd.containsKey("secondCategory_id") || "".equals(pd.get("secondCategory_id").toString())) {
            if (pd.containsKey("firstCategory_id") && !"".equals(pd.get("firstCategory_id").toString())) {
                pd.put("parentId", pd.get("firstCategory_id"));
                List<Map> secondCategorys = ToLowerCaseForList((List<Map>) daoSupport.findForList("CategoryManagerMapper.queryCategoryByParentID", pd));
                List<String> secondCategory_ids = new ArrayList<>();
                for (Map map : secondCategorys) {
                    String secondCategory_id = map.get("id").toString();
                    if (!secondCategory_ids.contains(secondCategory_id)) {
                        secondCategory_ids.add(secondCategory_id);
                    }
                }
                pd.put("secondCategory_ids", secondCategory_ids);
            }
        }
        if (pd.containsKey("page")) {
            if (pd.containsKey("sort") && pd.get("sort") != null) {
                PageHelper.startPage(
                        Integer.parseInt(pd.get("page").toString()),
                        Integer.parseInt(pd.get("rows").toString()), pd.get("sort").toString() + " " + pd.get("order").toString());
            } else {
                PageHelper.startPage(
                        Integer.parseInt(pd.get("page").toString()),
                        Integer.parseInt(pd.get("rows").toString()));
            }
            List<Map> list = ToLowerCaseForList((List<Map>) daoSupport.findForList("GoodsManagerMapper.queryGoods2", pd));
            List<String> ids = new ArrayList<>();
            for (Map map : list) {
                ids.add(map.get("id").toString());
            }
            pd.put("goodsIds", ids);
            List<Map> items = ToLowerCaseForList((List<Map>) daoSupport.findForList("GoodsManagerMapper.queryGoodsItem2", pd));
            Map<String, Map<String, Object>> results = new HashMap<>();
            for (Map obj : items) {
                String key = obj.get("goods_id").toString();
                results.put(key, obj);
            }
            for (Map current : list) {
                String key = current.get("id").toString();
                if (results.containsKey(key)) {
                    current.put("query_add_num", results.get(key).get("query_add_num").toString());
                } else {
                    current.put("query_add_num", BigDecimal.ZERO);
                }
            }
            Page page = (Page) list;
            HashMap result = getPageMapForJspShow(list, page);
            if (redisUtil.exists(rediskey)){
                redisUtil.remove(rediskey);
            }
            redisUtil.set(rediskey, result);
            return result;
        } else {
            Map result = queryDaoDataT("GoodsManagerMapper", "queryGoods", pd);
            if (redisUtil.exists(rediskey)){
                redisUtil.remove(rediskey);
            }
            redisUtil.set(rediskey, result);
            return result;
        }
    }

}
