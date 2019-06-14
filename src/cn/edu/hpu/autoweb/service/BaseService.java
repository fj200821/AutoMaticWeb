package cn.edu.hpu.autoweb.service;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

import cn.edu.hpu.autoweb.dao.DaoSupport;
import cn.edu.hpu.autoweb.util.mes.DateUtils;
import net.sf.cglib.proxy.Enhancer;
import cn.edu.hpu.autoweb.dao.DaoResultBuilder;
import cn.edu.hpu.autoweb.util.mes.StringUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseService {
    @Autowired
    private DaoSupport daoSupport;


    // 通过反射调用数据查询通用接口，自动判断是否需要分页
    @SuppressWarnings({"unchecked", "rawtypes"})
    public <T> Map queryDaoDataT(String mapperName, String methodName, Map para) throws Exception {

        if (para.containsKey("page")) {
            if (para.containsKey("sort") && para.get("sort") != null) {
                PageHelper.startPage(
                        Integer.parseInt(para.get("page").toString()),
                        Integer.parseInt(para.get("rows").toString()), para.get("sort").toString() + " " + para.get("order").toString());
            } else {
                PageHelper.startPage(
                        Integer.parseInt(para.get("page").toString()),
                        Integer.parseInt(para.get("rows").toString()));
            }

            List list = ToLowerCaseForList((List<Map>) daoSupport.findForList(mapperName + "." + methodName, para));
            Page page = (Page) list;
            return getPageMapForJspShow(list, page);
        } else {
            return wrapClientResultLowerCase((List<Map>) daoSupport.findForList(mapperName + "." + methodName, para));
        }
    }

    protected void initPageHelper(Map para) {
        if (para.containsKey("page")) {
            PageHelper.startPage(
                    Integer.parseInt(para.get("page").toString()),
                    Integer.parseInt(para.get("rows").toString()));
        } else {
            PageHelper.startPage(1, 50);
        }
    }

    /**
     * 将一个 JavaBean 对象转化为一个  Map
     *
     * @param bean 要转化的JavaBean 对象
     * @return 转化出来的  Map 对象
     * @throws IntrospectionException    如果分析类属性失败
     * @throws IllegalAccessException    如果实例化 JavaBean 失败
     * @throws InvocationTargetException 如果调用属性的 setter 方法失败
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static Map convertBean(Object bean)
            throws IntrospectionException, IllegalAccessException, InvocationTargetException {
        Class type = bean.getClass();
        Map returnMap = new HashMap();
        BeanInfo beanInfo = Introspector.getBeanInfo(type);

        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (!propertyName.equals("class")) {
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(bean, new Object[0]);
                if (result != null) {
                    returnMap.put(propertyName, result);
                } else {
                    returnMap.put(propertyName, "");
                }
            }
        }
        return returnMap;
    }

    /**
     * 将一个 Map 对象转化为一个 JavaBean
     *
     * @param type 要转化的类型
     * @param map  包含属性值的 map
     * @return 转化出来的 JavaBean 对象
     * @throws IntrospectionException    如果分析类属性失败
     * @throws IllegalAccessException    如果实例化 JavaBean 失败
     * @throws InstantiationException    如果实例化 JavaBean 失败
     * @throws InvocationTargetException 如果调用属性的 setter 方法失败
     */
    @SuppressWarnings("rawtypes")
    public static Object convertMap(Class type, Map map)
            throws IntrospectionException, IllegalAccessException,
            InstantiationException, InvocationTargetException {
        BeanInfo beanInfo = Introspector.getBeanInfo(type);         // 获取类属性   
        Object obj = type.newInstance();                            // 创建 JavaBean 对象   

        // 给 JavaBean 对象的属性赋值   
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();

            if (map.containsKey(propertyName)) {
                // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。   
                Object value = map.get(propertyName);

                Object[] args = new Object[1];
                args[0] = value;

                descriptor.getWriteMethod().invoke(obj, args);
            }
        }
        return obj;
    }

    /**
     * 将一个 Map 对象转化为一个 JavaBean
     *
     * @param type 要转化的类型
     * @param map  包含属性值的 map
     * @return 转化出来的 JavaBean 对象
     * @throws IntrospectionException    如果分析类属性失败
     * @throws IllegalAccessException    如果实例化 JavaBean 失败
     * @throws InstantiationException    如果实例化 JavaBean 失败
     * @throws InvocationTargetException 如果调用属性的 setter 方法失败
     */
    @SuppressWarnings("rawtypes")
    public static Object convertFromMap(Class type, Map map)
            throws Exception {
        BeanInfo beanInfo = Introspector.getBeanInfo(type);         // 获取类属性   
        Object obj = type.newInstance();                            // 创建 JavaBean 对象   

        // 给 JavaBean 对象的属性赋值   
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            Class propType = descriptor.getPropertyType();
            if (map.containsKey(propertyName.toLowerCase())) {
                // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。   
                Object value = map.get(propertyName.toLowerCase());

                if (null == value || value.equals(""))
                    continue;

                Class valType = value.getClass();
                if (valType.equals(BigDecimal.class) && (propType.equals(Integer.class) || propType.equals(int.class))) {
                    value = Integer.parseInt(value.toString());
                }
                if (valType.equals(String.class) && (propType.equals(Integer.class) || propType.equals(int.class))) {
                    value = Integer.parseInt(value.toString());
                }
                if (valType.equals(String.class) && (propType.equals(Date.class))) {
                    value = DateUtils.fromDateTimeString(value.toString());
                }
                Object[] args = new Object[1];
                args[0] = value;

                descriptor.getWriteMethod().invoke(obj, args);
            }
        }
        return obj;
    }

    protected HashMap getPageMapForJspShow(List list, Page page) {
        DaoResultBuilder resultBuilder = new DaoResultBuilder();

        int total = (int) page.getTotal();
        int pages = page.getPages();

        resultBuilder.setSuccessful().setCount(pages).setTotal(total);
        resultBuilder.setRows(list);

        return resultBuilder.retValue();
    }

    public Map wrapClientResult(List list) {
        DaoResultBuilder resultBuilder = new DaoResultBuilder();
        resultBuilder.setSuccessful().setCount(list.size()).setTotal(list.size());
        resultBuilder.setRows(list);
        return resultBuilder.retValue();
    }

    public Map wrapClientResultLowerCase(List<Map> list) {
        if(list == null){
            list = new ArrayList<>();
        }
        DaoResultBuilder resultBuilder = new DaoResultBuilder();
        resultBuilder.setSuccessful().setCount(list.size()).setTotal(list.size());
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Map map = (Map) list.get(i);
                map = StringUtils.toLowerMap(map);
                list.set(i, map);
            }
        }
        resultBuilder.setRows(list);
        return resultBuilder.retValue();
    }

    public List<Map> ToLowerCaseForList(List<Map> list) {
        if(list == null){
            return new ArrayList<>();
        }
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Map myhash = (Map) list.get(i);
                myhash = StringUtils.toLowerMap(myhash);
                list.set(i, myhash);
            }
        }

        return list;
    }

//    public List<Map>  ToLowerCaseForListMap(List<Map> list)
//    {
//    	  if (list.size() > 0) 
//          {
//    		  for(int i=0;i<list.size();i++)
//              {
//            	  Map myhash= (Map) list.get(i);
//            	  myhash=StringUtils.toLowerMap(myhash);
//            	  list.set(i, myhash);        	  
//              }
//          }
//    	
//    	return list;
//    }

    public Map wrapAffectedError(String error) {
        DaoResultBuilder resultBuilder = new DaoResultBuilder();
        resultBuilder.setError(error);
        return resultBuilder.retValue();
    }

    public Map wrapAffectedResult(String data) {
        DaoResultBuilder resultBuilder = new DaoResultBuilder();
        resultBuilder.setSuccessful().setAttribute("data", data);
        return resultBuilder.retValue();
    }

    public Map wrapAffectedResult(int affectedCount) {
        DaoResultBuilder resultBuilder = new DaoResultBuilder();
        resultBuilder.setSuccessful().setCount(affectedCount).setTotal(affectedCount);
        return resultBuilder.retValue();
    }

    public Map wrapAffectedResult(int affectedCount, int idFieldVal) {
        DaoResultBuilder resultBuilder = new DaoResultBuilder();
        resultBuilder.setSuccessful().setCount(affectedCount).setTotal(affectedCount)
                .setAttribute("id", idFieldVal);
        return resultBuilder.retValue();
    }

    public Map wrapAffectedResult(int affectedCount, String idFieldName, int idFieldVal) {
        DaoResultBuilder resultBuilder = new DaoResultBuilder();
        resultBuilder.setSuccessful().setCount(affectedCount).setTotal(affectedCount)
                .setAttribute(idFieldName, idFieldVal);
        return resultBuilder.retValue();
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public void buildBeanAttrPara(Class cls, Object obj, Map para) throws Exception {
        Field[] fields = cls.getDeclaredFields();
        Field.setAccessible(fields, true);
        Map keyVal = new HashMap();
        for (Field field : fields) {
            if (field.getType().getName().equals("boolean"))
                continue;
            keyVal.put(field.getName().toLowerCase(), field.get(obj));
        }
        para.put("fields", keyVal);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public void buildBeanAttrParaWithOutIdFiled(Class cls, Object obj, Map para, String idFiled) throws Exception {
        Field[] fields = cls.getDeclaredFields();
        Field.setAccessible(fields, true);
        Map keyVal = new HashMap();
        for (Field field : fields) {
            if (field.getName().toLowerCase().equals(idFiled.toLowerCase()) || field.getType().getName().equals("boolean"))
                continue;
            keyVal.put(field.getName().toLowerCase(), field.get(obj));
        }
        para.put("fields", keyVal);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public void assignBeanAttrPara(Class cls, Object from, Object to) throws Exception {
        Field[] fields = cls.getDeclaredFields();
        Field.setAccessible(fields, true);
        Map keyVal = new HashMap();
        for (Field field : fields) {
            Object val = field.get(from);
            if (val != null) {
                field.set(to, val);
            }
        }
    }

    public static <T> T createObject(Class pojoclassz) {
        Enhancer en = new Enhancer();
        //进行代理  
        en.setSuperclass(pojoclassz);
        //设置织入逻辑
        en.setCallback(new ServiceProxy());
        //生成代理实例  
        return (T) en.create();
    }

    public static List<HashMap> getResultMap(ResultSet rs)
            throws SQLException {
        List<HashMap> rows = new ArrayList();

        while (rs != null && rs.next()) {

            HashMap<String, String> hm = new HashMap<String, String>();
            ResultSetMetaData rsmd = rs.getMetaData();
            int count = rsmd.getColumnCount();
            for (int i = 1; i <= count; i++) {
                String key = rsmd.getColumnLabel(i).toLowerCase();
                String value = rs.getString(i);
                hm.put(key, value);
            }
            rows.add(hm);

        }
        return rows;
    }

    protected String getCondition(String col, String val) {
        return getCondition(col, val, 0);
    }

    protected String getCondition(String col, String val, int type) {
        if (val.length() == 0) {
            return "";
        } else {
            switch (type) {
                case 0:
                    return " AND " + col + " = '" + val + "'";
                case 1:
                    return " AND " + col + " LIKE '%" + val + "%'";
                default:
                    return " AND " + col + "'" + val + "'";
            }
        }
    }


}
