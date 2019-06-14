package cn.edu.hpu.autoweb.listener;

import cn.edu.hpu.autoweb.entity.SystemUser;
import cn.edu.hpu.autoweb.service.system.systemlogin.SystemLoginService;
import cn.edu.hpu.autoweb.util.Const;
import cn.edu.hpu.autoweb.util.mes.SpringUtils;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashMap;

public class SessionListener implements HttpSessionListener {
    /**
     * 该HashMap以用户名-HttpSession对象存储一个账号只能被一个人登陆的信息。
     */
    public static HashMap<String, HttpSession> sessionMap = new HashMap<String, HttpSession>();

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        delSession(session);
    }

    public static synchronized void delSession(HttpSession session) {
        if (session != null) {
            // 删除单一登录中记录的变量
            if (session.getAttribute(Const.SESSION_USER) != null) {
                SystemUser user = (SystemUser) session.getAttribute(Const.SESSION_USER);
                SessionListener.sessionMap.remove(user.getUserId());
                SystemLoginService systemLoginService = (SystemLoginService) SpringUtils.getObject("systemLoginService");
                try {
                    systemLoginService.updateLoginLog(user.getUserId());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
