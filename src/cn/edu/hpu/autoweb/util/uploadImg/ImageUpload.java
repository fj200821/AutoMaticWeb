package cn.edu.hpu.autoweb.util.uploadImg;

import java.io.File;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import cn.edu.hpu.autoweb.util.Const;
import cn.edu.hpu.autoweb.util.Tools;
/**
 * 基类实现图片的上传的功能
 *
 */
public class ImageUpload {
	
	//返回的是保存在数据库里面的图片的路径
	
	public static String uploadImg(MultipartFile photo){
		
		if (photo.getSize()!=0) {
			// 原始名称
			String originalname = photo.getOriginalFilename();
			// 上传图片
			if (photo != null && originalname != null && originalname.length() > 0) {
				try {
				// 设置保存路径
				String savePath=readImgUrl();
				System.out.println(savePath);
				// 判断文件路径是否存在
				File file = new File(savePath);
				if (!file.exists()) {
					file.mkdir();
				}
				// 新的图片名称
				String newFileName = UUID.randomUUID() + originalname.substring(originalname.lastIndexOf("."));
				// 新的图片
				File newFile = new File(savePath + "/" + newFileName);
				// 将文件写入磁盘
				photo.transferTo(newFile);
				
				  return newFileName;
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}
		

		return "";
	}
	
	
	
	//读取图片的存放的路径
	private static  String readImgUrl(){
		String saveImgUrl = Tools.readTxtFile(Const.SAVEIMGURL);
		return saveImgUrl;
	}
}
