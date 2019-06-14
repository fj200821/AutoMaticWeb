package cn.edu.hpu.autoweb.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.ClassUtils;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 捕捉异常显示页面上
 * 
 * @author 赵宝旗
 */
public class ExceptionHandler implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception e) {
		// 将异常信息放到map集合里面
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("e", e);
		// 这里可根据不同异常引起类做不同处理方式，本例做不同返回页面。
		String viewName = ClassUtils.getShortName(e.getClass());
		e.printStackTrace();
		//StackTraceElement[] ee = e.getStackTrace();
		return new ModelAndView(viewName,model);
	}

}
