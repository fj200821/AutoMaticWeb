package cn.edu.hpu.autoweb.controller.system.basic;


import cn.edu.hpu.autoweb.controller.base.BaseController;
import cn.edu.hpu.autoweb.service.system.automatic.CategoryService;
import cn.edu.hpu.autoweb.service.system.automatic.GoodsService;
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
@RequestMapping("/Basic/CategoryMgr")
public class CategoryController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/CategoryManager/queryCategoryByParentID")
    @ResponseBody
    public String queryCategoryByParentID(){
        JSONObject json = new JSONObject();
        try{
            PageData pd = getPageData();
            return wrapperSuccess(categoryService.queryCategoryByParentID(pd),json);
        }catch (Exception e){
            logger.error("CategoryController::queryCategoryByParentID catch exception:", e);
            return wrapperException(e,json);
        }
    }
}
