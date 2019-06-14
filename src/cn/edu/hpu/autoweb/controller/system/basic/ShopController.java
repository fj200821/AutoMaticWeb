package cn.edu.hpu.autoweb.controller.system.basic;


import cn.edu.hpu.autoweb.controller.base.BaseController;
import cn.edu.hpu.autoweb.service.system.automatic.ShopService;
import cn.edu.hpu.autoweb.util.PageData;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/Basic/ShopMgr")
public class ShopController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(ShopController.class);

    @Autowired
    private ShopService shopService;

    @RequestMapping("/ShopManager")
    public ModelAndView createdProductManager(){
        ModelAndView mv = new ModelAndView("system/shopMgr/shopManager");
        return mv;
    }

    @RequestMapping("/ShopManager/queryShop")
    @ResponseBody
    public String queryShop(){
        JSONObject json = new JSONObject();
        try{
            PageData pd = getPageData();
            return wrapperSuccess(shopService.queryShop(pd),json);
        }catch (Exception e){
            logger.error("ShopController::queryShop catch exception:", e);
            return wrapperException(e,json);
        }
    }
}
