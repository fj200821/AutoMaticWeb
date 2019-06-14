package cn.edu.hpu.autoweb.service;

import cn.edu.hpu.autoweb.dao.DaoSupport;
import cn.edu.hpu.autoweb.util.UuidUtil;
import cn.edu.hpu.autoweb.util.mes.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service
@Transactional
public class CommonService extends BaseService {
	
    @Resource(name = "daoSupport")
    private DaoSupport daoSupport;

    public Map insert(Class cls, Object beanObj, String tablename) throws Exception
    {
        Map para = new HashMap();
        buildBeanAttrPara(cls, beanObj, para);
        para.put("table", tablename);        
        Map resultMap = wrapAffectedResult((int)daoSupport.save("CommonMapper.insert",para));
        Map fieldMap = (Map) para.get("fields");
        resultMap.put("id", fieldMap.get("id"));
        return resultMap;
    }
	

    public int simpleInsert(Class cls, Object beanObj, String tablename) throws Exception
    {
        Map para = new HashMap();
        buildBeanAttrPara(cls, beanObj, para);
        para.put("table", tablename);
        return (int)daoSupport.save("CommonMapper.insert",para);
    }
	
    public Map update(Class cls, Object fromObj, Object toObj, String tablename, Object id,String idField) throws Exception
    {
        assignBeanAttrPara(cls, fromObj, toObj);
        Map para = new HashMap();
        para.put("id", id);
        para.put("idFiled",idField);
        buildBeanAttrParaWithOutIdFiled(cls, toObj, para,idField);
        para.put("table", tablename);
        Map resultMap = wrapAffectedResult((int)daoSupport.update("CommonMapper.update",para));
        resultMap.put("id", id);
        return resultMap;
    }
    
    public int simpleUpdate(Class cls, Object fromObj, Object toObj, String tablename, Object id,String idField) throws Exception
    {
        assignBeanAttrPara(cls, fromObj, toObj);
        Map para = new HashMap();
        para.put("id", id);
        para.put("idFiled",idField);
        buildBeanAttrParaWithOutIdFiled(cls, toObj, para,idField);
        para.put("table", tablename);
        return (int)daoSupport.update("CommonMapper.update",para);
    }
	
    @SuppressWarnings("unchecked")
    public Map findById(String id,String idField,String tablename) throws Exception {
        Map para = new HashMap();
        para.put("id", id);
        para.put("idFiled",idField);
        para.put("table", tablename);
        return (Map)daoSupport.findForObject("CommonMapper.findById",para);
    }

    @SuppressWarnings("unchecked")
    public Map delete(Object id, String tablename,String idField) throws Exception
    {
        Map para = new HashMap();
        para.put("id", id);
        para.put("idField",idField);
        para.put("table", tablename);
        return wrapAffectedResult((int)daoSupport.delete("CommonMapper.del",para));
    }

}
