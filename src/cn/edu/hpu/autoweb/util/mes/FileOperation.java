package cn.edu.hpu.autoweb.util.mes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;


public class FileOperation {


	public void exportExcel(HttpServletResponse response, String title, Object[] headers, 
	        String[] fields, List data, String fileName) throws Exception 
	{
		    DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			fileName = "MESExport_" + fileName + "_" + df.format(new Date()) + ".xls";
			response.setContentType("octets/stream");
			response.addHeader("Content-Disposition", "attachment;filename="
					+ new String(fileName.getBytes("gb2312"), "ISO8859-1"));

			ExportExcelUtils exportExcel = new ExportExcelUtils();
			exportExcel.exportExcel(title, headers, data, fields,
					response.getOutputStream());
	}

	public void exportExcel(HttpServletResponse response, String title, Object[] headers, String proPath,
			List data, String[] fields, String fileName,String path) throws Exception {
		exportExcel(response, title, getHeaders(headers, proPath,path), fields, data, fileName);
	}

	private Object[] getHeaders(Object[] headers, String proPath,String path) throws Exception 
	{
		Object[] newHeaders = new Object[headers.length];
//			String path = getServletContext().getRealPath("/" + proPath);
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
		return newHeaders;
	}
	public List getFileType(HttpServletRequest request) throws Exception{
		List fileList = new ArrayList();
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
		if(multipartResolver.isMultipart(request))
        {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
            Iterator iter = multiRequest.getFileNames();
            Map para = multiRequest.getFileMap();
            while(iter.hasNext())
            {
                //一次遍历所有文件
                String fileNames = iter.next().toString();              
                List<MultipartFile> files = multiRequest.getFiles(fileNames);
                for (MultipartFile file : files) {
                    if(file!=null && !"".equals(file.getOriginalFilename()))
                    {
                    	String fileName = file.getOriginalFilename();
                    	fileName = fileName.substring(fileName.lastIndexOf(".")+1);
                        fileList.add(fileName);
                    }
                }
            }
        }
		return fileList;
	}
	
	//文件上传
	public List uploadFile(HttpServletRequest request,String modular) throws Exception 
	{
        List fileList = new ArrayList();
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
		if(multipartResolver.isMultipart(request))
        {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
            Iterator iter = multiRequest.getFileNames();
            Map para = multiRequest.getFileMap();
            StringBuffer strBuf = new StringBuffer(request.getRealPath("")).append("/../../")
                    .append(ConfigReader.getAttr("uploadfolder")).append("/").append(modular);
//            java.net.URL r = this.getClass().getClassLoader().getResource(request.getContextPath());
//            String classRootPath = this.getClass().getClassLoader().getResource("").getPath();
//            strBuf.delete(0, strBuf.length());
//            strBuf.append(r.getFile()).append(File.separator).append(ConfigReader.getAttr("uploadfolder"));
//            String uploadPath = ConfigReader.getAttr("uploadfolder");
            //java.net.URL r = this.getClass().getClassLoader().getResource(strBuf.toString());
            File uploadFolder = new File(strBuf.toString());
			if (!uploadFolder.exists()) {
				uploadFolder.mkdirs();
			}
			
            while(iter.hasNext())
            {
                //一次遍历所有文件
                String fileName = iter.next().toString();              
                List<MultipartFile> files = multiRequest.getFiles(fileName);
                for (MultipartFile file : files) {
                    if(file!=null && !"".equals(file.getOriginalFilename()))
                    {
                    	String originalName = file.getOriginalFilename();
                    	String name = UUID.randomUUID().toString().replace("-", "")+"_"+originalName;
                        String path = uploadFolder.getAbsolutePath() + File.separator + name;
                        fileList.add(name);
                        //上传
                        file.transferTo(new File(path));
                    }
                }
            }
        }
		return fileList;
	}
	
		//文件上传(添加文件名)
		public List uploadFileWithUUID(HttpServletRequest request,String modular) throws Exception 
		{
	        List fileList = new ArrayList();
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
	                request.getSession().getServletContext());
			if(multipartResolver.isMultipart(request))
	        {
	            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
	            Iterator iter = multiRequest.getFileNames();
	            Map para = multiRequest.getFileMap();
	            StringBuffer strBuf = new StringBuffer(request.getRealPath("")).append("/../../")
	                    .append(ConfigReader.getAttr("uploadfolder")).append("/").append(modular);
	            File uploadFolder = new File(strBuf.toString());
				if (!uploadFolder.exists()) {
					uploadFolder.mkdirs();
				}
				
	            while(iter.hasNext())
	            {
	                //一次遍历所有文件
	                String fileName = iter.next().toString();              
	                List<MultipartFile> files = multiRequest.getFiles(fileName);
	                for (MultipartFile file : files) {
	                    if(file!=null && !"".equals(file.getOriginalFilename()))
	                    {
	                        String path = uploadFolder.getAbsolutePath() + File.separator + file.getOriginalFilename();
	                        fileList.add(file.getOriginalFilename());
	                        //上传
	                        file.transferTo(new File(path));
	                    }
	                }
	            }
	        }
			return fileList;
		}
	
	//文件下载
	public void downloadFile(HttpServletRequest request, HttpServletResponse response, String modular,String fileName) throws IOException {
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
		if(fileName.contains("_")){
			fileName = fileName.substring(fileName.indexOf("_")+1);
		}
		response.setHeader("Content-Disposition", "attachment;filename=\""
				+ fileName + "\"");
		in = new FileInputStream(new File(path));
		int n = 0;// 每次读取的字节长度
		byte[] bb = new byte[1024];// 存储每次读取的内容
		while ((n = in.read(bb)) != -1) {
			response.getOutputStream().write(bb, 0, n);// 将读取的内容，写入到输出流当中
		}
	}
	
	
	//文件删除
    public void deleteFileInMesUserRoot(HttpServletRequest request, String modular,String fileName) throws IOException {
        StringBuffer strBuf = new StringBuffer(request.getRealPath("")).append("/../../")
                .append(ConfigReader.getAttr("uploadfolder")).append("/").append(modular);
        strBuf.append(File.separator).append(fileName);
        File file = new File(strBuf.toString());
        if (file.exists())
            file.delete();
    }
    
  //文件拷贝
    public void copyFile(HttpServletRequest request,String modular, String fileName, String topath) throws IOException {
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
        if (fileFrom.exists()){
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
    public void viewFileOnline(HttpServletRequest request,HttpServletResponse response,String floder,String fileName) {
    	FileInputStream in = null;
		OutputStream ops = null;
		try {
			StringBuffer strBuf = new StringBuffer(request.getRealPath("")).append("/../../")
                    .append(ConfigReader.getAttr("uploadfolder"));
			String path = strBuf + File.separator + floder + File.separator + fileName;
			response.setHeader("Content-type", "application/pdf");
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
				if(in != null)
				{
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    }
       
}
