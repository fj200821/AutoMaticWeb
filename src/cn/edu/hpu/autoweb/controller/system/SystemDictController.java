package cn.edu.hpu.autoweb.controller.system;

import cn.edu.hpu.autoweb.controller.base.BaseController;
import cn.edu.hpu.autoweb.service.system.FilePathService;
import cn.edu.hpu.autoweb.service.system.systemlogin.SystemDictService;
import cn.edu.hpu.autoweb.util.PageData;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Map;

/**
 * Created by Aries on 2017/4/16.
 */
@Controller
@RequestMapping("/Basic/SystemDict")
public class SystemDictController extends BaseController{
    private Logger logger = LoggerFactory.getLogger(SystemDictController.class);
    @Autowired
    private SystemDictService systemDictService;
    @Autowired
    private FilePathService filePathService;

    @RequestMapping(value = "/queryDictCodeByPCode",
            method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String queryDictCodeByPCode(){
        JSONObject json = new JSONObject();
        try{
            PageData pd = getPageData();
            return json.toJSONString(systemDictService.queryDictCodeByPCode(pd));
        }catch (Exception e){
            logger.error("SystemDictController::queryDictCodeByPCode catch exception:", e);
            return wrapperException(e,json);
        }
    }

    @RequestMapping(value = "/queryIconAll",
            method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String queryIconAll(){
        JSONObject json = new JSONObject();
        try{
            return json.toJSONString(systemDictService.findIconAll());
        }catch (Exception e){
            logger.error("SystemDictController::findIconAll catch exception:", e);
            return wrapperException(e,json);
        }
    }

    @RequestMapping(value="/onLineViewCourse",
            method = {RequestMethod.POST, RequestMethod.GET},
            produces = "application/json; charset=utf-8")
    @ResponseBody
    public void onLineViewCourse(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PageData pd =getPageData();
        String floder = "WorkDir"  + File.separator + pd.get("floder").toString();
        Map file = filePathService.findFileById(pd.get("fileid").toString());
        String name = file.get("filepath").toString();
        name = name.substring(name.lastIndexOf(".")+1).toLowerCase();
        if(name.equals("pdf")){
            viewFileOnline(request,response,floder,file.get("filepath").toString(),"application/pdf");
        }else {
            viewFileOnline(request,response,floder,file.get("filepath").toString());
        }
    }
}
