package cn.edu.hpu.autoweb.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogUtil {
	public final static String weixin_data_dir = "C:/ApplicationData";
	public final static String log_path = "C:/ApplicationData/log.txt";

	static FileWriter fw;
	static BufferedWriter bf;

	public static void write(String str) {
		boolean append = false;
		File weixin_dir = new File(weixin_data_dir);
		if (!weixin_dir.exists()) {
			weixin_dir.mkdirs();
		}
		File file = new File(log_path);
		try {
			if (file.exists())
				append = true;
			fw = new FileWriter(log_path, append);// 同时创建新文件
			// 创建字符输出流对象
			bf = new BufferedWriter(fw);
			// 创建缓冲字符输出流对象
			bf.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+": "+str+"\r\n");
			bf.flush();
			bf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		write("awsdas");
		write("awsdas");
		write("awsdas");
	}
}
