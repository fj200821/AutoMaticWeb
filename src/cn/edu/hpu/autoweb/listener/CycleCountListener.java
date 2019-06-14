package cn.edu.hpu.autoweb.listener;

import cn.edu.hpu.autoweb.task.CycleCountTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Timer;


public class CycleCountListener implements ServletContextListener {
    Timer t = null;

    private static final long TIME = 1000 * 60;

    public void contextDestroyed(ServletContextEvent event) {
        if (t != null) {
            t.cancel();
        }
    }

    /**
     * TIME = 1分钟
     * TIME * 60 * 2 = 2小时 执行一次
     * @param event
     */
    public void contextInitialized(ServletContextEvent event) {
        try {
            t = new Timer();
            logger.info("start task[CycleCountTask] ...");
            CycleCountTask task = new CycleCountTask();
            t.schedule(task, 0, TIME * 60 * 3);
        } catch (Exception e) {
            logger.error("CycleCountListener::contextInitialized catch exception:", e);
        }

    }

    private Logger logger = LoggerFactory.getLogger(CycleCountListener.class);
}