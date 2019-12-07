package com.scaler7.utils;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

/**
 * 
* @ClassName: scaler7
* @Description: 七牛云相关方法 
* @author Zzz  
* @date 2019年12月4日  
*
 */
public class QiNiuCloud {

	/**
	 * 密钥凭证
	 */
	private static final String ACCESS_KEY = "65m-T8UW90ZXJFNxist2cSo0WsrSmIF6sTL8W4EY";
	private static final String SECRET_KEY = "WKTG1MjMc2OJbwG8dnJGbKU1LD3HmzasWEbBXPqP";
	/**
	 * 仓库
	 */
	private static final String BUCKET = "scaler7";

	/**
	 * 七牛外网访问地址
	 */
	public static final String QINIU_UPLOAD_SITE = "";

	/**
	 * 
	* @Title: upload  
	* @Description: 七牛云上传方法，根据七牛云提供的sdk写成 
	* @param @param file
	* @param @param fileName
	* @param @return  
	* @return String
	* @throws
	 */
	public static String upload(MultipartFile file,String fileName) {
		Configuration cfg = new Configuration(Zone.zone2());
		
		UploadManager uploadManager = new UploadManager(cfg);
		
		@SuppressWarnings("unused")
		String key = fileName;

		Auth auth = Auth.create(ACCESS_KEY,SECRET_KEY);
		String upToken = auth.uploadToken(BUCKET);
		
		Response response = null;
		try {
			response = uploadManager.put(file.getInputStream(), fileName, upToken, null, null);
			DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
			System.out.println(putRet.key);
			System.out.println(putRet.hash);
			return  putRet.key;
		} catch (QiniuException e) {
			Response r = e.response;
	        System.err.println(r.toString());
	        
	        try {
                System.out.println(r.bodyString());
            } catch (QiniuException e2) {

            }
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 
	* @Title: delete  
	* @Description: 七牛云删除方法，根据七牛云sdk写成 
	* @param @param key
	* @param @return  
	* @return boolean
	* @throws
	 */
	public static boolean delete(String key) {
		Configuration cfg = new Configuration(Zone.zone2());
		Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
		BucketManager bucketManager = new BucketManager(auth, cfg);
		try {
			Response response = bucketManager.delete(BUCKET, key);
			return response.isOK();
		} catch (QiniuException e) {
			//如果遇到异常，说明删除失败
		    System.err.println(e.code());
		    System.err.println(e.response.toString());
			e.printStackTrace();
		}
		return false;
	}
}
