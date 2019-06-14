package cn.edu.hpu.autoweb.controller.base;


import java.io.*;
import java.nio.channels.FileChannel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.hpu.autoweb.util.mes.ConfigReader;
import cn.edu.hpu.autoweb.util.mes.ExportExcelUtils;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import cn.edu.hpu.autoweb.util.Logger;
import cn.edu.hpu.autoweb.util.PageData;
import cn.edu.hpu.autoweb.util.UuidUtil;


public class BaseController extends CustomDateConverter {

    protected Logger logger = Logger.getLogger(this.getClass());

    private static final long serialVersionUID = 6357869213649815390L;

    /**
     * 得到PageData
     */
    public PageData getPageData() {
        return new PageData(this.getRequest());
    }

    /**
     * 得到ModelAndView
     */
    public ModelAndView getModelAndView() {
        return new ModelAndView();
    }

    /**
     * 得到request对象
     */
    public HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }


    /**
     * 得到32位的uuid
     *
     * @return
     */
    public String get32UUID() {
        return UuidUtil.get32UUID();
    }

    /**
     * 得到分页列表的信息
     */
//	public Page getPage(){
//		return new Page();
//	}
    public static void logBefore(Logger logger, String interfaceName) {
        logger.info("");
        logger.info("start");
        logger.info(interfaceName);
    }

    public static void logAfter(Logger logger) {
        logger.info("end");
        logger.info("");
    }

    protected String wrapperSuccess(Map retMap, JSONObject json) throws Exception {
        retMap.put("resultCode", "200");
        return json.toJSONString(retMap);
    }

    protected String wrapperException(Exception e, JSONObject json) {
        json.put("resultCode", "404");
        json.put("error", e.getMessage());
        return json.toJSONString();
    }

    public void exportExcel(HttpServletResponse response, String title, Object[] headers,
                            String[] fields, List data, String fileName) {
        try {
            DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            fileName = "Export_" + fileName + "_" + df.format(new Date()) + ".xls";
            response.setContentType("octets/stream");
            response.addHeader("Content-Disposition", "attachment;filename="
                    + new String(fileName.getBytes("gb2312"), "ISO8859-1"));

            ExportExcelUtils exportExcel = new ExportExcelUtils();
            exportExcel.exportExcel(title, headers, data, fields,
                    response.getOutputStream());
        } catch (Exception e) {
            logger.error("BaseController::exportExcel catch exception:", e);
        }
    }

    public void exportExcel(HttpServletResponse response, String title, Object[] headers, String proPath,
                            List data, String[] fields, String fileName) {
        exportExcel(response, title, getHeaders(headers, proPath), fields, data, fileName);
    }

    public PageData convertStringArray(HashMap<Object, Object> map) {
        PageData pd = new PageData();

        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            pd.put(entry.getKey(), ((String[]) entry.getValue())[0]);
        }
        return pd;
    }

    public List getFileType(MultipartFile[] files, HttpServletRequest request) throws Exception {
        List<String> fileList = new ArrayList<String>();
        if (files != null && files.length > 0) {
            for (MultipartFile file : files) {
                if (file != null && !"".equals(file.getOriginalFilename())) {
                    fileList.add(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1));
                }
            }
        }
        return fileList;
    }


    //文件上传
    public List<String> uploadFile(MultipartFile[] files, HttpServletRequest request, String modular) throws Exception {
        List<String> fileList = new ArrayList<String>();
        StringBuffer strBuf = new StringBuffer(request.getRealPath("")).append("/../../")
                .append(ConfigReader.getAttr("uploadfolder")).append("/").append(modular);
        File uploadFolder = new File(strBuf.toString());
        if (!uploadFolder.exists()) {
            uploadFolder.mkdirs();
        }
        if (files != null && files.length > 0) {
            for (MultipartFile file : files) {
                if (file != null && !"".equals(file.getOriginalFilename())) {
                    String originalName = file.getOriginalFilename();
                    String name = UUID.randomUUID().toString().replace("-", "") + "_" + originalName;
                    String path = uploadFolder.getAbsolutePath() + File.separator + name;
                    fileList.add(name);
                    //上传
                    file.transferTo(new File(path));
                }
            }
        }
        return fileList;
    }

    //文件上传(添加文件名)
    public List<String> uploadFileWithOutUUID(MultipartFile[] files, HttpServletRequest request, String modular) throws Exception {
        List<String> fileList = new ArrayList<String>();
        StringBuffer strBuf = new StringBuffer(request.getRealPath("")).append("/../../")
                .append(ConfigReader.getAttr("uploadfolder")).append("/").append(modular);
        File uploadFolder = new File(strBuf.toString());
        if (!uploadFolder.exists()) {
            uploadFolder.mkdirs();
        }
        if (files != null && files.length > 0) {
            for (MultipartFile file : files) {
                if (file != null && !"".equals(file.getOriginalFilename())) {
                    String originalName = file.getOriginalFilename();
                    String path = uploadFolder.getAbsolutePath() + File.separator + originalName;
                    fileList.add(originalName);
                    //上传
                    file.transferTo(new File(path));
                }
            }
        }
        return fileList;
    }

    protected void setSession(HttpServletRequest request, String name, Object value) {
        HttpSession session = request.getSession();
        ActionContext actionContext = ActionContext.getContext();
        session.putValue(name, value);
    }

    //文件下载
    public void downloadFile(HttpServletRequest request, HttpServletResponse response, String modular, String fileName) throws IOException {
        FileInputStream in = null;
        StringBuffer strBuf = new StringBuffer(request.getRealPath("")).append("/../../")
                .append(ConfigReader.getAttr("uploadfolder")).append("/").append(modular);
        String path = strBuf + File.separator + fileName;
        response.setContentType("application/octet-stream;charset=UTF-8");
        if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
            fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
        } else {
            fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
        }
        if (fileName.contains("_")) {
            fileName = fileName.substring(fileName.indexOf("_") + 1);
        }
        response.setHeader("Content-Disposition", "attachment;filename=\""
                + fileName + "\"");
        in = new FileInputStream(new File(path));
        int n = 0;// 每次读取的字节长度
        byte[] bb = new byte[1024];// 存储每次读取的内容
        while ((n = in.read(bb)) != -1) {
            response.getOutputStream().write(bb, 0, n);// 将读取的内容，写入到输出流当中
        }
        setSession(request, "printdoc", "100");
    }

    //文件删除
    public void deleteFileInMesUserRoot(HttpServletRequest request, String modular, String fileName) throws IOException {
        StringBuffer strBuf = new StringBuffer(request.getRealPath("")).append("/../../")
                .append(ConfigReader.getAttr("uploadfolder")).append("/").append(modular);
        strBuf.append(File.separator).append(fileName);
        File file = new File(strBuf.toString());
        if (file.exists())
            file.delete();
    }

    //文件拷贝
    public void copyFile(HttpServletRequest request, String modular, String fileName, String topath) throws IOException {
        StringBuffer strBufFrom = new StringBuffer(request.getRealPath("")).append("/../../")
                .append(ConfigReader.getAttr("uploadfolder")).append("/").append(modular);
        strBufFrom.append(File.separator).append(fileName);
        File fileFrom = new File(strBufFrom.toString());
        StringBuffer strBufTo = new StringBuffer(request.getRealPath("")).append(topath);
        File fileToFolder = new File(strBufTo.toString());
        if (!fileToFolder.exists()) {
            fileToFolder.mkdirs();
        }
        File fileTo = new File(strBufTo.append(File.separator).append(fileName).toString());
        if (fileFrom.exists()) {
            FileChannel inputChannel = null;
            FileChannel outputChannel = null;
            try {
                inputChannel = new FileInputStream(fileFrom).getChannel();
                outputChannel = new FileOutputStream(fileTo).getChannel();
                outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
            } finally {
                inputChannel.close();
                outputChannel.close();
            }
        }
    }

    //文件在线查看
    public void viewFileOnline(HttpServletRequest request, HttpServletResponse response, String floder, String fileName) {
        FileInputStream in = null;
        OutputStream ops = null;
        try {
            StringBuffer strBuf = new StringBuffer(request.getRealPath("")).append("/../../")
                    .append(ConfigReader.getAttr("uploadfolder"));
            String path = strBuf + File.separator + floder + File.separator + fileName;
            response.setHeader("Content-type", "image/png");
            response.setHeader("Content-Disposition", "inline;filename="
                    + java.net.URLEncoder.encode(fileName, "UTF-8"));
            ops = response.getOutputStream();
            in = new FileInputStream(new File(path));
            int n = 0;// 每次读取的字节长度
            byte[] bb = new byte[1024];// 存储每次读取的内容
            n = in.read(bb);
            while (n != -1) {
                ops.write(bb, 0, n);// 将读取的内容，写入到输出流当中
                n = in.read(bb);
            }
            ops.close();
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
        }
    }

    public void viewFileOnline(HttpServletRequest request, HttpServletResponse response, String floder, String fileName, String type) {
        FileInputStream in = null;
        OutputStream ops = null;
        try {
            StringBuffer strBuf = new StringBuffer(request.getRealPath("")).append("/../../")
                    .append(ConfigReader.getAttr("uploadfolder"));
            String path = strBuf + File.separator + floder + File.separator + fileName;
            response.setHeader("Content-type", type);
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Content-Disposition", "inline;filename="
                    + java.net.URLEncoder.encode(fileName, "UTF-8"));
            ops = response.getOutputStream();
            in = new FileInputStream(new File(path));
            int n = 0;// 每次读取的字节长度
            byte[] bb = new byte[1024 * 1024 * 4];// 存储每次读取的内容
            n = in.read(bb);
            while (n != -1) {
                ops.write(bb, 0, n);// 将读取的内容，写入到输出流当中
                n = in.read(bb);
            }
            ops.close();

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
        }
    }

    protected ServletContext getServletContext() {
        return ServletActionContext.getServletContext();
    }

    private Object[] getHeaders(Object[] headers, String proPath) {
        Object[] newHeaders = new Object[headers.length];
        try {
            String path = getServletContext().getRealPath("/" + proPath);
            Properties pro = new Properties();
            FileInputStream in = new FileInputStream(path);
            pro.load(in);
            for (int i = 0; i < headers.length; i++) {
                if (headers[i] instanceof Object[]) {
                    Object[] objs = new Object[((Object[]) headers[i]).length];
                    for (int j = 0; j < ((Object[]) headers[i]).length; j++) {
                        objs[j] = pro.getProperty(((Object[]) headers[i])[j]
                                .toString());
                    }
                    newHeaders[i] = objs;
                } else {
                    newHeaders[i] = pro.getProperty(headers[i].toString());
                }
            }
        } catch (Exception e) {
            logger.error("BaseAction::getHeaders catch exception:", e);
        }
        return newHeaders;
    }

    public String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
    }
}
