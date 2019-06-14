package cn.edu.hpu.autoweb.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DaoResultBuilder {

    private HashMap<String, Object> resultMap;
    
    public DaoResultBuilder() {
        resultMap = new HashMap<String, Object>();
        resultMap.put("error", "");
        resultMap.put("pages", 0);
        resultMap.put("successful", 0);
        resultMap.put("total", 0);
    }
    
    public DaoResultBuilder setRows(List<Map> rows) {       
        resultMap.put("rows", rows);
        return this;        
    }
    
    public DaoResultBuilder setAttribute(String attrName, Object attrVal) {
        resultMap.put(attrName, attrVal);
        return this;        
    }
    
    public DaoResultBuilder setError(String error) {
        resultMap.put("error", error);
        return this;
    }
    
    public DaoResultBuilder setSuccessful() {
        resultMap.put("successful", 1);
        return this;
    }
    
    public DaoResultBuilder setCount(int i) {
        resultMap.put("pages", i);
        return this;
    }
    
    public DaoResultBuilder setTotal(int i) {
        resultMap.put("total", i);
        return this;
    }
    
    public HashMap retValue() {
        return resultMap;
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
