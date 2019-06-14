package cn.edu.hpu.autoweb.controller.system.basic;


import cn.edu.hpu.autoweb.controller.base.BaseController;
import cn.edu.hpu.autoweb.entity.SystemUser;
import cn.edu.hpu.autoweb.service.system.automatic.GoodsService;
import cn.edu.hpu.autoweb.service.system.automatic.ShopService;
import cn.edu.hpu.autoweb.util.Const;
import cn.edu.hpu.autoweb.util.PageData;
import com.alibaba.fastjson.JSONObject;
import com.fr.third.org.apache.poi.hssf.record.formula.functions.Int;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/Basic/GoodsMgr")
public class GoodsController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(GoodsController.class);

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/GoodsManager")
    public ModelAndView createdProductManager() throws Exception {
        ModelAndView mv = new ModelAndView("system/goodsMgr/goodsManager");
        List<Integer> goodsIDs = new ArrayList<>();
        SystemUser user = (SystemUser) getRequest().getSession().getAttribute(Const.SESSION_USER);
        PageData pd = new PageData();
        pd.put("userId", user.getUserId());
        Map result = goodsService.queryUserGoodsByUserId(pd);
        List<Map> rows = (List<Map>) result.get("rows");
        for (Map row : rows) {
            goodsIDs.add(Integer.parseInt(row.get("goods_id").toString()));
        }
        mv.addObject("hasCollectionGoodsID", goodsIDs);
        return mv;
    }


    @RequestMapping("/GoodsManager/queryGoods")
    @ResponseBody
    public String queryGoods() {
        JSONObject json = new JSONObject();
        try {
            PageData pd = getPageData();
            return wrapperSuccess(goodsService.queryGoods(pd), json);
        } catch (Exception e) {
            logger.error("GoodsController::queryGoods catch exception:", e);
            return wrapperException(e, json);
        }
    }

    @RequestMapping("/ToDayGoodsManager")
    public ModelAndView createdTodayGoodsManager() throws Exception {
        ModelAndView mv = new ModelAndView("system/goodsMgr/todayGoodsManager");
        List<Integer> goodsIDs = new ArrayList<>();
        SystemUser user = (SystemUser) getRequest().getSession().getAttribute(Const.SESSION_USER);
        PageData pd = new PageData();
        pd.put("userId", user.getUserId());
        Map result = goodsService.queryUserGoodsByUserId(pd);
        List<Map> rows = (List<Map>) result.get("rows");
        for (Map row : rows) {
            goodsIDs.add(Integer.parseInt(row.get("goods_id").toString()));
        }
        mv.addObject("hasCollectionGoodsID", goodsIDs);
        return mv;
    }

    @RequestMapping("/GoodsManager/queryToDayGoods")
    @ResponseBody
    public String queryToDayGoods() {
        JSONObject json = new JSONObject();
        try {
            PageData pd = getPageData();
            return wrapperSuccess(goodsService.queryToDayGoods(pd), json);
        } catch (Exception e) {
            logger.error("GoodsController::queryToDayGoods catch exception:", e);
            return wrapperException(e, json);
        }
    }

    @RequestMapping("/GoodsManager/queryGoodsForMy")
    @ResponseBody
    public String queryGoodsForMy(@RequestParam(value = "goodsIds[]") String[] goodsIds) {
        JSONObject json = new JSONObject();
        try {
            PageData pd = getPageData();
            pd.put("goodsIds", goodsIds);
            if (goodsIds.length <= 0) {
                return wrapperSuccess(new HashMap(), json);
            }
            return wrapperSuccess(goodsService.getGoodsForMy(pd), json);
        } catch (Exception e) {
            logger.error("GoodsController::queryGoods catch exception:", e);
            return wrapperException(e, json);
        }
    }


    @RequestMapping("/GoodsManager/updateGoodsById")
    @ResponseBody
    public String updateGoodsById() {
        JSONObject json = new JSONObject();
        try {
            PageData pd = getPageData();
            return wrapperSuccess(goodsService.updateGoodsById(pd), json);
        } catch (Exception e) {
            logger.error("GoodsController::updateGoodsById catch exception:", e);
            return wrapperException(e, json);
        }
    }


    @RequestMapping("/GoodsDetailManager")
    public ModelAndView createdGoodsDetailManager() throws Exception {
        ModelAndView mv = new ModelAndView("system/goodsMgr/goodsDetailManager");
        PageData pd = getPageData();
        Map detail = goodsService.queryGoodsById(pd.get("goodsId").toString());
        mv.addObject("detail", detail);
        BigDecimal sell_unm = new BigDecimal(detail.get("sell_num").toString());
        BigDecimal add_unm = new BigDecimal(detail.get("add_num").toString());
        BigDecimal rate = BigDecimal.ZERO;
        if (!sell_unm.equals(BigDecimal.ZERO)) {
            rate = add_unm.divide(sell_unm, 3, BigDecimal.ROUND_HALF_UP);
        }
        mv.addObject("rate", (rate.multiply(new BigDecimal(100))).stripTrailingZeros().toPlainString());
        mv.addObject("goodsId", pd.get("goodsId"));
        return mv;
    }


    @RequestMapping("/ToDayGoodsDetailManager")
    public ModelAndView createdToDayGoodsDetailManager() throws Exception {
        ModelAndView mv = new ModelAndView("system/goodsMgr/todayGoodsDetailManager");
        PageData pd = getPageData();
        Map detail = goodsService.queryGoodsById(pd.get("goodsId").toString());
        mv.addObject("detail", detail);
        BigDecimal sell_unm = new BigDecimal(detail.get("sell_num").toString());
        BigDecimal add_unm = new BigDecimal(detail.get("add_num").toString());
        BigDecimal rate = BigDecimal.ZERO;
        if (!sell_unm.equals(BigDecimal.ZERO)) {
            rate = add_unm.divide(sell_unm, 3, BigDecimal.ROUND_HALF_UP);
        }
        mv.addObject("rate", (rate.multiply(new BigDecimal(100))).stripTrailingZeros().toPlainString());
        mv.addObject("goodsId", pd.get("goodsId"));
        return mv;
    }

    @RequestMapping("/GoodsManager/queryGoodsItems")
    @ResponseBody
    public String queryGoodsItems() {
        JSONObject json = new JSONObject();
        try {
            PageData pd = getPageData();
            return wrapperSuccess(goodsService.queryGoodsItems(pd), json);
        } catch (Exception e) {
            logger.error("GoodsController::queryGoodsItems catch exception:", e);
            return wrapperException(e, json);
        }
    }

    @RequestMapping("/GoodsManager/exportData")
    @ResponseBody
    public void exportData(HttpServletResponse response) {
        JSONObject json = new JSONObject();
        try {
            PageData pd = getPageData();
            String[] field = {"goods_name", "goods_price", "sell_num", "query_add_num", "edit_time", "create_time"};
            String[] heads = {"商品名称", "价格", "总销量", "增量", "更新时间", "上架时间"};
            pd.put("limit", "50000");
            List<Map> rows = goodsService.queryGoodsWithOutPage(pd);
            exportExcel(response, "商品信息", heads, field, rows, "商品信息");
        } catch (Exception e) {
            logger.error("GoodsController::exportData catch exception:", e);
        }
    }


    @RequestMapping("/GoodsManager/exportData2")
    @ResponseBody
    public void exportData2(HttpServletResponse response) {
        JSONObject json = new JSONObject();
        try {
            PageData pd = getPageData();
            String[] field = {"goods_name", "goods_price", "sell_num", "add_num", "edit_time", "create_time"};
            String[] heads = {"商品名称", "价格", "总销量", "今日增量", "更新时间", "上架时间"};
            List<Map> rows = goodsService.queryToDayGoodsWithOutPage(pd);
            exportExcel(response, "商品信息", heads, field, rows, "商品信息");
        } catch (Exception e) {
            logger.error("GoodsController::exportData catch exception:", e);
        }
    }

    @RequestMapping("/GoodsManager/exportData3")
    @ResponseBody
    public void exportData3(HttpServletResponse response) {
        JSONObject json = new JSONObject();
        try {
            PageData pd = getPageData();
            String[] field = {"goods_name", "goods_price", "sell_num", "add_num", "edit_time", "create_time"};
            String[] heads = {"商品名称", "价格", "当期销量", "当期增量", "最后一次更新时间", "上架时间"};
            List<Map> rows = goodsService.queryHistoryGoodsWithOutPage(pd);
            exportExcel(response, "商品信息", heads, field, rows, "商品信息");
        } catch (Exception e) {
            logger.error("GoodsController::exportData catch exception:", e);
        }
    }

    @RequestMapping("/GoodsManager/saveUserGoods")
    @ResponseBody
    public String saveUserGoods() {
        JSONObject json = new JSONObject();
        try {
            PageData pd = getPageData();
            SystemUser user = (SystemUser) getRequest().getSession().getAttribute(Const.SESSION_USER);
            pd.put("userID", user.getUserId());
            return wrapperSuccess(goodsService.saveUserGoods(pd), json);
        } catch (Exception e) {
            logger.error("GoodsController::saveUserGoods catch exception:", e);
            return wrapperException(e, json);
        }
    }

    @RequestMapping("/GoodsManager/deleteUserGoods")
    @ResponseBody
    public String deleteUserGoods() {
        JSONObject json = new JSONObject();
        try {
            PageData pd = getPageData();
            SystemUser user = (SystemUser) getRequest().getSession().getAttribute(Const.SESSION_USER);
            pd.put("userID", user.getUserId());
            return wrapperSuccess(goodsService.deleteUserGoods(pd), json);
        } catch (Exception e) {
            logger.error("GoodsController::deleteUserGoods catch exception:", e);
            return wrapperException(e, json);
        }
    }


    @RequestMapping("/GoodsManager/queryUserGoodsByUserId")
    @ResponseBody
    public String queryUserGoodsByUserId() {
        JSONObject json = new JSONObject();
        try {
            PageData pd = getPageData();
            SystemUser user = (SystemUser) getRequest().getSession().getAttribute(Const.SESSION_USER);
            pd.put("userId", user.getUserId());
            return wrapperSuccess(goodsService.queryUserGoodsByUserId(pd), json);
        } catch (Exception e) {
            logger.error("GoodsController::queryGoodsItems catch exception:", e);
            return wrapperException(e, json);
        }
    }

    @RequestMapping("/HistoryGoodsManager")
    public ModelAndView createdHistoryGoodsManager() throws Exception {
        ModelAndView mv = new ModelAndView("system/goodsMgr/historyGoodsManager");
        List<Integer> goodsIDs = new ArrayList<>();
        SystemUser user = (SystemUser) getRequest().getSession().getAttribute(Const.SESSION_USER);
        PageData pd = new PageData();
        pd.put("userId", user.getUserId());
        Map result = goodsService.queryUserGoodsByUserId(pd);
        List<Map> rows = (List<Map>) result.get("rows");
        for (Map row : rows) {
            goodsIDs.add(Integer.parseInt(row.get("goods_id").toString()));
        }
        mv.addObject("hasCollectionGoodsID", goodsIDs);
        return mv;
    }

    @RequestMapping("/GoodsManager/queryHistoryGoods")
    @ResponseBody
    public String queryHistoryGoods() {
        JSONObject json = new JSONObject();
        try {
            PageData pd = getPageData();
            return wrapperSuccess(goodsService.queryHistoryGoods(pd), json);
        } catch (Exception e) {
            logger.error("GoodsController::queryHistoryGoods catch exception:", e);
            return wrapperException(e, json);
        }
    }

    @RequestMapping("/HistoryGoodsDetailManager")
    public ModelAndView createdHistoryGoodsDetailManager() throws Exception {
        ModelAndView mv = new ModelAndView("system/goodsMgr/historyGoodsDetailManager");
        PageData pd = getPageData();
        Map detail = goodsService.queryTmpGoods(pd);
        mv.addObject("detail", detail);
        BigDecimal sell_unm = new BigDecimal(detail.get("sell_num").toString());
        BigDecimal add_unm = new BigDecimal(detail.get("add_num").toString());
        BigDecimal rate = BigDecimal.ZERO;
        if (!sell_unm.equals(BigDecimal.ZERO)) {
            rate = add_unm.divide(sell_unm, 3, BigDecimal.ROUND_HALF_UP);
        }
        mv.addObject("rate", (rate.multiply(new BigDecimal(100))).stripTrailingZeros().toPlainString());
        mv.addObject("goodsId", pd.get("goodsId"));
        return mv;
    }

}
