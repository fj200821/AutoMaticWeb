package cn.edu.hpu.autoweb.interceptor;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.edu.hpu.autoweb.entity.SystemMenu;
import cn.edu.hpu.autoweb.entity.SystemUser;
import cn.edu.hpu.autoweb.util.Const;


public class LoginHandlerInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String path = request.getServletPath();
	
		if(path.matches(Const.NO_INTERCEPTOR_PATH)){
			return true;
		}else{
			HttpSession session = request.getSession();
			SystemUser user = (SystemUser)session.getAttribute(Const.SESSION_USER);
			List<SystemMenu> allmenuList  = (List<SystemMenu>)session.getAttribute(Const.SESSION_ALL_MENU_LIST);
			if(user != null && allmenuList != null){
				path = path.substring(1, path.length());
				return true;
			}else{
				//登陆过滤
				response.sendRedirect(request.getContextPath() + Const.LOGIN);
				PrintWriter printWriter = response.getWriter();
				String relogin = "登录过期，请重新登录！";
				printWriter.write(relogin);
				printWriter.flush();
				printWriter.close();
				return false;
				//return true;
			}
		}
	}
	
}
