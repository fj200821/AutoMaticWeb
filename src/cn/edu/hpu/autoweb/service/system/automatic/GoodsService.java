package cn.edu.hpu.autoweb.service.system.automatic;

import cn.edu.hpu.autoweb.dao.DaoSupport;
import cn.edu.hpu.autoweb.entity.UserGoods;
import cn.edu.hpu.autoweb.service.BaseService;
import cn.edu.hpu.autoweb.service.CommonService;
import cn.edu.hpu.autoweb.util.Const;
import cn.edu.hpu.autoweb.util.PageData;
import cn.edu.hpu.autoweb.util.RedisUtil;
import cn.edu.hpu.autoweb.util.StringUtil;
import cn.edu.hpu.autoweb.util.mes.ConfigReader;
import cn.edu.hpu.autoweb.util.mes.DateTimeUtils;
import cn.edu.hpu.autoweb.util.mes.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Aries on 2017/5/27.
 */
@Transactional
@Service
public class GoodsService extends BaseService {
    @Autowired
    private DaoSupport daoSupport;

    @Autowired
    private CommonService commonService;

    @Autowired
    private RedisUtil redisUtil;  //记得注入

    @Autowired
    private AsyncExecService asyncExecService;

    private final String table_userGoods = "user_goods";

    public Map queryGoods(PageData pd) throws Exception {
        String rediskey = asyncExecService.getGoodsRedisKey(pd);
        //加载下三页
        String page = pd.get("page").toString();
        if (pd.containsKey("page")) {
            for (int i = 1; i <= 1; i++) {
                PageData pd2 = new PageData();
                pd2 = pd;
                pd2.put("page", Integer.valueOf(page) + i);
                asyncExecService.loadGoodsRedis(pd2);
            }
        }
        if (redisUtil.exists(rediskey)) {
            return (Map) redisUtil.get(rediskey);
        } else {
            pd.put("page", page);
            return asyncExecService.getGoods(rediskey, pd);
        }
    }


    public List<Map> queryGoodsWithOutPage(PageData pd) throws Exception {
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
        return list;
    }


    public Map queryToDayGoods(PageData pd) throws Exception {
        pd.put("tmp_table", StringUtils.getTempTableName());
        String rediskey = Const.REDISKEY;
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
        if (pd.containsKey("goods_name")) {
            rediskey += pd.get("goods_name").toString();
        }
        if (pd.containsKey("biz_type")) {
            rediskey += pd.get("biz_type").toString();
        }
        if (redisUtil.exists(rediskey)) {
            return (Map) redisUtil.get(rediskey);
        } else {
            Map result = queryDaoDataT("GoodsManagerMapper", "queryToDayGoods", pd);
            redisUtil.set(rediskey, result);
            return result;
        }
    }


    public Map queryHistoryGoods(PageData pd) throws Exception {
        String rediskey = Const.REDISKEY;
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
        if (pd.containsKey("goods_name")) {
            rediskey += pd.get("goods_name").toString();
        }
        if (pd.containsKey("tmp_table")) {
            rediskey += pd.get("tmp_table").toString();
        }
        if (redisUtil.exists(rediskey)) {
            return (Map) redisUtil.get(rediskey);
        } else {
            Map result = queryDaoDataT("GoodsManagerMapper", "queryHistoryGoods", pd);
            redisUtil.set(rediskey, result);
            return result;
        }
    }

    public List<Map> queryToDayGoodsWithOutPage(PageData pd) throws Exception {
        pd.put("tmp_table", StringUtils.getTempTableName());
        return ToLowerCaseForList((List<Map>) daoSupport.findForList("GoodsManagerMapper.queryToDayGoods", pd));
    }

    public List<Map> queryHistoryGoodsWithOutPage(PageData pd) throws Exception {
        return ToLowerCaseForList((List<Map>) daoSupport.findForList("GoodsManagerMapper.queryHistoryGoods", pd));
    }

    public Map updateGoodsById(PageData pd) throws Exception {
        execUpdateGoodsById(pd.get("goods_id").toString());
        return new HashMap();
    }

    /**
     * 页面手动触发
     * @param goods_id
     * @throws IOException
     */
    public void execUpdateGoodsById(String goods_id) throws Exception {
        String pythonPath = ConfigReader.getAttr("pythonPath");
        String pythonFilePath = ConfigReader.getAttr("autoUpdateGoodsFilePath");
        execCMD(pythonPath,pythonFilePath,goods_id);
        redisUtil.removePattern(Const.REDISKEY+"*");
        asyncExecService.refreshRedis();
    }

    public void execCMD(String pythonPath,String pythonFilePath,Object param) throws IOException {
        try {
            Process process = Runtime.getRuntime().exec(String.format("%s %s %s",pythonPath,pythonFilePath,param));
            doWaitFor(process);
        } catch (IOException e) {
            throw e;
        }
    }

    public static int doWaitFor(Process process) {
        InputStream in = null;
        InputStream err = null;
        int exitValue = -1; // returned to caller when p is finished
        try {
            in = process.getInputStream();
            err = process.getErrorStream();
            boolean finished = false; // Set to true when p is finished
            while (!finished) {
                try {
                    while (in.available() > 0) {
                        // Print the output of our system call
                        Character c = new Character((char) in.read());
                        System.out.print(c);
                    }
                    while (err.available() > 0) {
                        // Print the output of our system call
                        Character c = new Character((char) err.read());
                        System.out.print(c);
                    }
                    // Ask the process for its exitValue. If the process
                    // is not finished, an IllegalThreadStateException
                    // is thrown. If it is finished, we fall through and
                    // the variable finished is set to true.
                    exitValue = process.exitValue();
                    finished = true;
                } catch (IllegalThreadStateException e) {
                    // Process is not finished yet;
                    // Sleep a little to save on CPU cycles
                    Thread.currentThread().sleep(500);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (err != null) {
                try {
                    err.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return exitValue;
    }

    public Map queryGoodsItems(PageData pd) throws Exception {
        pd.put("today", DateTimeUtils.getCurrentSingleDate());
        return queryDaoDataT("GoodsManagerMapper", "queryGoodsItems", pd);
    }

    public Map getIndexInfo(PageData pd) throws Exception {
        Map<String, Object> result = new HashMap<>();
        if (redisUtil.exists(Const.REDISKEY + "shopNum")) {
            result.put("shopNum", redisUtil.get(Const.REDISKEY + "shopNum"));
        } else {
            result.put("shopNum", daoSupport.findForObject("ShopManagerMapper.queryShopCounts", null));
            redisUtil.set(Const.REDISKEY + "shopNum", result.get("shopNum"));
        }
        if (redisUtil.exists(Const.REDISKEY + "goodsNum")) {
            result.put("goodsNum", redisUtil.get(Const.REDISKEY + "goodsNum"));
        } else {
            result.put("goodsNum", daoSupport.findForObject("GoodsManagerMapper.queryGoodsCounts", null));
            redisUtil.set(Const.REDISKEY + "goodsNum", result.get("goodsNum"));
        }
        Map<String, Object> params = new HashMap<>();
        params.put("todayStartTime", DateTimeUtils.getStartOfDay(new Date()));

        if (redisUtil.exists(Const.REDISKEY + "todayNewGoodsCounts")) {
            result.put("todayNewGoodsCounts", redisUtil.get(Const.REDISKEY + "todayNewGoodsCounts"));
        } else {
            result.put("todayNewGoodsCounts", daoSupport.findForObject("GoodsManagerMapper.queryTodayNewGoodsCounts", params));
            redisUtil.set(Const.REDISKEY + "todayNewGoodsCounts", result.get("todayNewGoodsCounts"));
        }
        if (redisUtil.exists("maxNum")) {
            result.put("maxNum", redisUtil.get(Const.REDISKEY + "maxNum"));
        } else {
            params.put("tmp_table", StringUtils.getTempTableName());
            result.put("maxNum", daoSupport.findForObject("GoodsManagerMapper.queryTodayMaxAddNum", params));
            redisUtil.set(Const.REDISKEY + "maxNum", result.get("maxNum"));
        }
        return result;
    }

    public Map saveUserGoods(PageData pd) throws Exception {
        UserGoods userGoods = new UserGoods();
        userGoods.setUserId(pd.getString("userID"));
        userGoods.setGoods_Id(Integer.parseInt(pd.getString("goodsID")));
        userGoods.setCreate_Time(new Date());
        return commonService.insert(UserGoods.class, userGoods, table_userGoods);
    }

    public Map deleteUserGoods(PageData pd) throws Exception {
        return wrapAffectedResult((int) daoSupport.delete("GoodsManagerMapper.deleteUserGoods", pd));
    }

    public Map queryUserGoodsByUserId(PageData pd) throws Exception {
        return wrapClientResultLowerCase((List<Map>) daoSupport.findForList("GoodsManagerMapper.queryUserGoodsByUserId", pd));
    }

    public Map queryGoodsById(String goodsId) throws Exception {
        return StringUtils.toLowerMap((HashMap) daoSupport.findForObject("GoodsManagerMapper.queryGoodsById", goodsId));
    }

    public Map queryTmpGoods(PageData pd) throws Exception {
        return StringUtils.toLowerMap((HashMap) daoSupport.findForObject("GoodsManagerMapper.queryTmpGoods", pd));
    }


    public Map getGoodsForMy(PageData pd) throws Exception {
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
            return result;
        } else {
            Map result = queryDaoDataT("GoodsManagerMapper", "queryGoods", pd);
            return result;
        }
    }
}
