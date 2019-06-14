package cn.edu.hpu.autoweb.task;

import cn.edu.hpu.autoweb.entity.ExecRecord;
import cn.edu.hpu.autoweb.service.system.automatic.AutoExecService;
import cn.edu.hpu.autoweb.util.mes.SpringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;

public class CycleCountTask extends TimerTask {

    public void run() {
        try {
//            System.out.println("执行爬取" + new Date());
//            AutoExecService autoExecService = (AutoExecService) SpringUtils.getObject("autoExecService");
//            autoExecService.execCMD();
        } catch (Exception e) {
            logger.error("CycleCountTask::run catch exception:", e);
        }
    }

    private Logger logger = LoggerFactory.getLogger(CycleCountTask.class);
}
