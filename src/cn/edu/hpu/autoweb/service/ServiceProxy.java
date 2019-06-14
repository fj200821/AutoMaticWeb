package cn.edu.hpu.autoweb.service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ServiceProxy implements MethodInterceptor 
{
	private boolean isFirst = true;
	
	public Object intercept(Object arg0, Method arg1, Object[] arg2,
			MethodProxy arg3) throws Throwable {
		if(isFirst && !"finalize".equals(arg1.getName()))
		{
			isFirst = false;
			BaseService baseService = (BaseService) arg0;
//			Session session = baseService.getCurrentSession();
			Object object;
			try {
//				session.getTransaction().begin();
				object = arg3.invokeSuper(arg0, arg2);
//				session.getTransaction().commit();
			} catch (Exception e) {// 异常处理
				e.printStackTrace();
//				session.getTransaction().rollback();
				if(arg1.getReturnType() != null)
				{
					String name = arg1.getReturnType().getName();
					if(name.endsWith("void"))
					{
						object = null;
					}
					else if(name.endsWith("boolean"))
					{
						object = false;
					}
					else if(name.endsWith("String"))
					{
						object = "error";
					}
					else if(name.endsWith("List")){
						object = new ArrayList();
					}
					else if(name.endsWith("Map")){
						Map<String, Object> returnmap = new HashMap<String, Object>();
						returnmap.put("error", e.getCause().getMessage());
						returnmap.put("count", 0);
						returnmap.put("total", 0);
						returnmap.put("flag", "false");
						returnmap.put("successful", 0);
						object = returnmap;
					}
					else {
						object = null;
					}
				}
				else {
					object = null;
				}
			} finally {
				isFirst = true;
//				if (session != null && session.isOpen()) {
//					session.close();
//				}
			}

			isFirst = true;
			return object;
		}
		else {
			return arg3.invokeSuper(arg0, arg2);
		}
	}
	
	private String getSimpleName(String name)
	{
		int nSS = name.indexOf("$$");
		if(nSS > 0){
			return name.substring(0,nSS);
		}
		else {
			return name;
		}
	}	
	
	private Logger logger = LoggerFactory.getLogger(ServiceProxy.class);
}
