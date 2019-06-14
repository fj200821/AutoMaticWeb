package cn.edu.hpu.autoweb.controller.system.basic;


import cn.edu.hpu.autoweb.controller.base.BaseController;
import cn.edu.hpu.autoweb.entity.SystemUser;
import cn.edu.hpu.autoweb.service.system.automatic.GoodsService;
import cn.edu.hpu.autoweb.service.system.automatic.ShopService;
import cn.edu.hpu.autoweb.service.system.systemlogin.SystemUserService;
import cn.edu.hpu.autoweb.util.Const;
import cn.edu.hpu.autoweb.util.PageData;
import cn.edu.hpu.autoweb.util.mes.DateUtils;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/Basic/UserMgr")
public class UserController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private SystemUserService systemUserService;
    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/UserManager")
    public ModelAndView createdProductManager(){
        ModelAndView mv = new ModelAndView("system/userMgr/userManager");
        SystemUser user = (SystemUser) getRequest().getSession().getAttribute(Const.SESSION_USER);
        mv.addObject("user",user);
        long hasDays = DateUtils.daysOfTwo_2(DateUtils.getCurrentDate(),user.getEffectDate());
        mv.addObject("effectDay",DateUtils.formatDateTime(user.getEffectDate()));
        mv.addObject("hasDays",hasDays);
        long totalDays = DateUtils.daysOfTwo_2(user.getEffectStartDate(),user.getEffectDate());
        BigDecimal one = new BigDecimal(totalDays-hasDays);
        BigDecimal two = new BigDecimal(totalDays);
        BigDecimal three = new BigDecimal(100);
        BigDecimal result1 = one.divide(two,2,BigDecimal.ROUND_HALF_UP);
        BigDecimal result = result1.multiply(three);
        mv.addObject("rate",result);
        return mv;
    }

    @RequestMapping("/UserGoodsManager")
    public ModelAndView createdUserGoodsManager() throws Exception {
        ModelAndView mv = new ModelAndView("system/userMgr/goodsManager");
        List<Integer> goodsIDs = new ArrayList<>();
        SystemUser user = (SystemUser)getRequest().getSession().getAttribute(Const.SESSION_USER);
        PageData pd = new PageData();
        pd.put("userId",user.getUserId());
        Map result = goodsService.queryUserGoodsByUserId(pd);
        List<Map> rows = (List<Map>) result.get("rows");
        for(Map row : rows){
            goodsIDs.add(Integer.parseInt(row.get("goods_id").toString()));
        }
        mv.addObject("hasCollectionGoodsID",goodsIDs);
        return mv;
    }

    @RequestMapping("/GoodsDetailManager")
    public ModelAndView createdGoodsDetailManager() throws Exception {
        ModelAndView mv = new ModelAndView("system/userMgr/goodsDetailManager");
        PageData pd = getPageData();
        Map detail = goodsService.queryGoodsById(pd.get("goodsId").toString());
        mv.addObject("detail",detail);
        BigDecimal sell_unm = new BigDecimal(detail.get("sell_num").toString());
        BigDecimal add_unm = new BigDecimal(detail.get("add_num").toString());
        BigDecimal rate = BigDecimal.ZERO;
        if(!sell_unm.equals(BigDecimal.ZERO)){
            rate = add_unm.divide(sell_unm,0,BigDecimal.ROUND_HALF_UP);
        }
        mv.addObject("rate",rate);
        mv.addObject("goodsId",pd.get("goodsId"));
        return mv;
    }

    @RequestMapping("/UserManager/save")
    @ResponseBody
    public String saveUser(){
        JSONObject json = new JSONObject();
        try{
            PageData pd = getPageData();
            return wrapperSuccess(systemUserService.saveSystemUser(pd),json);
        }catch (Exception e){
            logger.error("UserController::saveUser catch exception:", e);
            return wrapperException(e,json);
        }
    }


}
