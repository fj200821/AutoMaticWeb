package cn.edu.hpu.autoweb.service.system.automatic;

import cn.edu.hpu.autoweb.dao.DaoSupport;
import cn.edu.hpu.autoweb.entity.ExecRecord;
import cn.edu.hpu.autoweb.entity.SystemDict;
import cn.edu.hpu.autoweb.service.BaseService;
import cn.edu.hpu.autoweb.service.CommonService;
import cn.edu.hpu.autoweb.task.CycleCountTask;
import cn.edu.hpu.autoweb.util.Const;
import cn.edu.hpu.autoweb.util.PageData;
import cn.edu.hpu.autoweb.util.RedisUtil;
import cn.edu.hpu.autoweb.util.UuidUtil;
import cn.edu.hpu.autoweb.util.mes.ConfigReader;
import cn.edu.hpu.autoweb.util.mes.DateTimeUtils;
import cn.edu.hpu.autoweb.util.mes.DateUtils;
import cn.edu.hpu.autoweb.util.mes.StringUtils;
import com.fr.third.org.apache.poi.hssf.record.formula.functions.False;
import org.apache.struts2.convention.annotation.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Transactional
@Service
@EnableScheduling
public class AutoExecService extends BaseService {

    @Autowired
    private CommonService commonService;
    @Autowired
    private DaoSupport daoSupport;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private AsyncExecService asyncExecService;

    private Logger logger = LoggerFactory.getLogger(AutoExecService.class);


    /**
     * 定时搜寻店铺
     *
     * @throws Exception
     */
//    @Scheduled(cron = "0 0 3,6,9,12,15,18,22 * * ?")
    public void execCMD() throws Exception {
        //上次没结束的，直接结束
        killPython();

        String datetime = DateTimeUtils.dateTimeFormattedEN(new Date());
        //第一步，销量大于0且在售的。(Type =1 )
        execTimeCMD(datetime, "1");
        //第二步，销量大于0且不在售的(Type =2 )
        execTimeCMD(datetime, "2");
        //第三步，按照店铺搜寻
        ExecRecord execRecord = new ExecRecord();
        execRecord.setIs_Success(true);
        execRecord.setIs_Confirm(false);
        execRecord.setCreate_Time(new Date());
        execRecord.setType("ByShop");
        String pythonPath = ConfigReader.getAttr("pythonPath");
        String pythonFilePath = ConfigReader.getAttr("pyFilePathByShop");
        //按照店铺爬取
        try {
            execCMD(pythonPath, pythonFilePath);
        } catch (Exception e) {
            execRecord.setIs_Success(false);
        }
        execRecord.setEnd_Time(new Date());
        commonService.insert(ExecRecord.class, execRecord, "exec_record");
        //第四步，销量小于0的（优先级最低 Type =3）
        execTimeCMD(datetime, "3");
    }

    /**
     * 执行 销量大于0且在售的。(Type =1 )
     * 执行 销量大于0且不在售的(Type =2 )
     * 执行 销量小于0的（优先级最低 Type =3）
     */
    public void execTimeCMD(String datetime, String type) throws Exception {
        ExecRecord execRecord = new ExecRecord();
        execRecord.setIs_Success(true);
        execRecord.setIs_Confirm(false);
        execRecord.setCreate_Time(new Date());
        execRecord.setType("execTimeCMDAndType=" + type);
        String pythonPath = ConfigReader.getAttr("pythonPath");
        String pythonFilePath = ConfigReader.getAttr("pyFilePathByTime");
        try {
            execCMD(pythonPath, pythonFilePath, datetime, type);
        } catch (Exception e) {
            execRecord.setIs_Success(false);
        }
        execRecord.setEnd_Time(new Date());
        commonService.insert(ExecRecord.class, execRecord, "exec_record");
    }

    /**
     * 每小时执行一次，包括top100,活动，好货，秒杀，值点精选，临时表（这些数据量较少，且意义较大，所以更新频率加大）
     *
     * @throws Exception
     */
//    @Scheduled(cron = "0 1 */1 * * ?")
    public void execTmpCMD() throws Exception {
        ExecRecord execRecord = new ExecRecord();
        execRecord.setIs_Success(true);
        execRecord.setIs_Confirm(false);
        execRecord.setCreate_Time(new Date());
        execRecord.setType("ByTmps");
        String pythonPath = ConfigReader.getAttr("pythonPath");
        String pythonFilePath = ConfigReader.getAttr("pyFilePathByTop100");
        //按照top100
        try {
            execCMD(pythonPath, pythonFilePath);
        } catch (Exception e) {
            execRecord.setIs_Success(false);
        }
        //按照活动
        pythonFilePath = ConfigReader.getAttr("pyFilePathByActivity");
        try {
            execCMD(pythonPath, pythonFilePath);
        } catch (Exception e) {
            execRecord.setIs_Success(false);
        }
        //按照以MertialID发起的活动
        pythonFilePath = ConfigReader.getAttr("pyFilePathByMertial");
        try {
            execCMD(pythonPath, pythonFilePath);
        } catch (Exception e) {
            execRecord.setIs_Success(false);
        }
        //按照秒杀
//        pythonFilePath = ConfigReader.getAttr("pyFilePathByKill");
//        try {
//            execCMD(pythonPath, pythonFilePath);
//        } catch (Exception e) {
//            execRecord.setIs_Success(false);
//        }
        //按照好货
        pythonFilePath = ConfigReader.getAttr("pyFilePathByGoodThing");
        try {
            execCMD(pythonPath, pythonFilePath);
        } catch (Exception e) {
            execRecord.setIs_Success(false);
        }
        //按照值点精选
        pythonFilePath = ConfigReader.getAttr("pyFilePathByZhiDian");
        try {
            execCMD(pythonPath, pythonFilePath);
        } catch (Exception e) {
            execRecord.setIs_Success(false);
        }
        //按照临时表
        pythonFilePath = ConfigReader.getAttr("pyFilePathByTmp");
        try {
            execCMD(pythonPath, pythonFilePath);
        } catch (Exception e) {
            execRecord.setIs_Success(false);
        }
        execRecord.setEnd_Time(new Date());
        commonService.insert(ExecRecord.class, execRecord, "exec_record");
    }


    /**
     * 每天凌晨寻找新的店铺（先看下平均执行时间，如果没问题，可以加大频率）
     *
     * @throws Exception
     */
//    @Scheduled(cron = "0 0 0 * * ?")
    public void execCMDUpdateCategory() throws Exception {
        //凌晨创建新的分表
        PageData pd = new PageData();
        pd.put("dataBaseName", "automatic");
        pd.put("tableName", StringUtils.getTempTableName());
        daoSupport.findForObject("CommonMapper.createSchema", pd);

        Map parentDict = StringUtils.toLowerMap(commonService.findById("TmpTableName", "Code", "systemdict"));
        SystemDict systemDict = new SystemDict();
        systemDict.setDictId(UuidUtil.get32UUID());
        systemDict.setPId(parentDict.get("dictid").toString());
        systemDict.setName(DateTimeUtils.getCurrentSingleDate());
        systemDict.setCode(StringUtils.getTempTableName());
        systemDict.setPCode(parentDict.get("code").toString());
        systemDict.setSeq(0);
        commonService.simpleInsert(SystemDict.class, systemDict, "systemdict");

        ExecRecord execRecord = new ExecRecord();
        execRecord.setIs_Success(true);
        execRecord.setIs_Confirm(false);
        execRecord.setType("ByCategory");
        execRecord.setCreate_Time(new Date());
        String datetime = DateTimeUtils.dateTimeFormattedEN(new Date());
        String pythonPath = ConfigReader.getAttr("pythonPath");
//        String pythonFilePath = ConfigReader.getAttr("pyFilePathGetCategory");
//        //先更新分类
//        try{
//            execCMD(pythonPath,pythonFilePath);
//        }catch (Exception e){
//            execRecord.setIs_Success(false);
//        }
        //执行一遍按照分类爬去，（找到新的商品&新的店铺）
        String pythonFilePath = ConfigReader.getAttr("pyFilePathByCategory");
        try {
            execCMD(pythonPath, pythonFilePath);
        } catch (Exception e) {
            execRecord.setIs_Success(false);
        }
        //执行一遍按照首页精选爬去，（找到新的商品&新的店铺）
        pythonFilePath = ConfigReader.getAttr("pyFilePathByRecommend");
        try {
            execCMD(pythonPath, pythonFilePath);
        } catch (Exception e) {
            execRecord.setIs_Success(false);
        }
        execRecord.setEnd_Time(new Date());
        commonService.insert(ExecRecord.class, execRecord, "exec_record");

        //第一步，销量大于0且在售的。(Type =1 )
        execTimeCMD(datetime, "1");
        //第二步，销量大于0且不在售的(Type =2 )
        execTimeCMD(datetime, "2");

        //按照店铺再横向查询更多的新增商品
        pythonFilePath = ConfigReader.getAttr("pyFilePathByShop");
        try {
            execCMD(pythonPath, pythonFilePath);
        } catch (Exception e) {
            execRecord.setIs_Success(false);
        }
        //第四步，销量小于0的（优先级最低 Type =3）
        execTimeCMD(datetime, "3");
    }


    /**
     * 服务器上有这个，笔记本不能有
     *
     * @throws Exception
     */
    @Scheduled(cron = "0 */5 * * * ?")
    public void check() throws Exception {
        List<Map> notConfirm = (List<Map>) daoSupport.findForList("ExecRecordMapper.queryRecord", null);
        if (null != notConfirm && notConfirm.size() > 0) {
            redisUtil.removePattern(Const.REDISKEY + "*");
            PageData pd = new PageData();
            //首页信息
            goodsService.getIndexInfo(pd);
            redisUtil.removePattern(Const.GOODS_REDISKEY + "*");
            //全部商品
            asyncExecService.refreshRedis();
            daoSupport.update("ExecRecordMapper.updateRecord", null);
        }
    }


    public void killPython() throws Exception {
        try {
            logger.error("尚有未结束的进程，杀死");
            Process process = Runtime.getRuntime().exec("taskkill /im python.exe /f");
            StreamGobbler errorGobbler = new
                    StreamGobbler(process.getErrorStream(), "ERROR");
            StreamGobbler outputGobbler = new
                    StreamGobbler(process.getInputStream(), "OUTPUT");
            errorGobbler.start();
            outputGobbler.start();
            int exitVal = process.waitFor();
            logger.error("ExitValue: " + exitVal);
        } catch (Exception e) {
            logger.error("调用爬取失败！==error on python==");
            throw e;
        }
    }


    /**
     * 调用爬取，不确定参数
     *
     * @param pythonPath
     * @param pythonFilePath
     * @param param
     * @throws Exception
     */
    public void execCMD(String pythonPath, String pythonFilePath, Object... param) throws Exception {
        try {
            logger.info(String.format("调用爬取:%s %s", pythonPath, pythonFilePath));
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(String.format("cmd /c %s %s ", pythonPath, pythonFilePath));
            for (Object obj : param) {
                stringBuilder.append(String.format("%s", obj));
            }
            Process process = Runtime.getRuntime().exec(stringBuilder.toString());
            StreamGobbler errorGobbler = new
                    StreamGobbler(process.getErrorStream(), "ERROR");
            StreamGobbler outputGobbler = new
                    StreamGobbler(process.getInputStream(), "OUTPUT");
            errorGobbler.start();
            outputGobbler.start();
            int exitVal = process.waitFor();
            logger.error("ExitValue: " + exitVal);
        } catch (Exception e) {
            logger.error("调用爬取失败！==error on python==");
            throw e;
        }
    }


    /**
     * 调用CMD
     *
     * @param pythonPath
     * @param pythonFilePath
     * @param param
     */
    public void execCMD(String pythonPath, String pythonFilePath, Object param) throws Exception {
        try {
            logger.info(String.format("调用爬取:%s %s", pythonPath, pythonFilePath));
            Process process = Runtime.getRuntime().exec(String.format("cmd /c %s %s \"%s\"", pythonPath, pythonFilePath, param));
            StreamGobbler errorGobbler = new
                    StreamGobbler(process.getErrorStream(), "ERROR");
            StreamGobbler outputGobbler = new
                    StreamGobbler(process.getInputStream(), "OUTPUT");
            errorGobbler.start();
            outputGobbler.start();
            int exitVal = process.waitFor();
            System.out.println("ExitValue: " + exitVal);
            logger.error("ExitValue: " + exitVal);
        } catch (Exception e) {
            logger.error("调用爬取失败！==error on python==");
            throw e;
        }
    }


    /**
     * 调用CMD
     *
     * @param pythonPath
     * @param pythonFilePath
     */
    public void execCMD(String pythonPath, String pythonFilePath) throws Exception {
        try {
            logger.info(String.format("调用爬取:%s %s", pythonPath, pythonFilePath));
            Process process = Runtime.getRuntime().exec(String.format("cmd /c %s %s", pythonPath, pythonFilePath));
            StreamGobbler errorGobbler = new
                    StreamGobbler(process.getErrorStream(), "ERROR");
            StreamGobbler outputGobbler = new
                    StreamGobbler(process.getInputStream(), "OUTPUT");
            errorGobbler.start();
            outputGobbler.start();

            int exitVal = process.waitFor();
            System.out.println("ExitValue: " + exitVal);
            logger.error("ExitValue: " + exitVal);
//            doWaitFor(process);
        } catch (Exception e) {
            logger.error("调用爬取失败！==error on python==");
            throw e;
        }
    }

    //https://blog.csdn.net/aerchi/article/details/7653372
    //异步写流
    public int doWaitFor(Process process) {
        InputStream in = null;
        InputStream err = null;
        ByteArrayOutputStream baos = null;
        int exitValue = -1; // returned to caller when p is finished
        try {
            in = process.getInputStream();
            err = process.getErrorStream();
            baos = new ByteArrayOutputStream();   // 在内存中创件可以增长的内存数组
            boolean finished = false; // Set to true when p is finished
            while (!finished) {
                try {
                    while (in.available() > 0) {
                        // Print the output of our system call
//                        Character c = new Character((char) in.read(),"utf-8");
                        int b;
                        while ((b = in.read()) != -1) {                           // 将读取到的数据逐个写入内存中
                            baos.write(b);
                        }
                        //byte[] arr = baos.toByteArray();                       // 将缓冲区的数据全部获取出来，并赋给arr数组
                        //System.out.println(new String(arr));   // 把整个arr数组都转成字符串
                        System.out.println(baos.toString());
                        baos.reset();
//                        System.out.print(c);
                    }
                    while (err.available() > 0) {
                        // Print the output of our system call
//                        Character c = new Character((char) err.read());
//                        System.out.print(c);

                        int b;
                        while ((b = err.read()) != -1) {                           // 将读取到的数据逐个写入内存中
                            baos.write(b);
                        }
                        //byte[] arr = baos.toByteArray();                       // 将缓冲区的数据全部获取出来，并赋给arr数组
                        //System.out.println(new String(arr));   // 把整个arr数组都转成字符串
                        System.out.println(baos.toString());
                        baos.reset();
                    }
                    // Ask the process for its exitValue. If the process
                    // is not finished, an IllegalThreadStateException
                    // is thrown. If it is finished, we fall through and
                    // the variable finished is set to true.
                    exitValue = process.exitValue();
                    finished = true;
                } catch (IllegalThreadStateException e) {
                    // Process is not finished yet;
                    // Sleep a little to save on CPU cycles
                    logger.error("Process Illegal：" + e.getMessage());
                    System.out.print("Process Illegal：" + e.getMessage());
                    Thread.currentThread().sleep(500);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (err != null) {
                try {
                    err.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return exitValue;
    }
}


class StreamGobbler extends Thread {
    private Logger logger = LoggerFactory.getLogger(StreamGobbler.class);
    InputStream is;
    String type;

    StreamGobbler(InputStream is, String type) {
        this.is = is;
        this.type = type;
    }

    public void run() {
        try {
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            while ((line = br.readLine()) != null) {
//                System.out.println(type + ">" + line);
                logger.error(type + ">" + line);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
