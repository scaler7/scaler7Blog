package com.scaler7.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.scaler7.admin.domain.Article;
import com.scaler7.admin.vo.TagsVO;

/**
 * 
* @ClassName: scaler7
* @Description: 封装了一些常用的工具类 
* @author Zzz  
* @date 2019年12月4日  
*
 */
public class CommonUtil {
	
	/**
	 * 
	* @Title: isImage  
	* @Description: 判断上传的文件是否是图片类型 
	* @param @param inputStream
	* @param @return  
	* @return boolean
	* @throws
	 */
	public static boolean isImage(InputStream inputStream) {
		try {
			BufferedImage image = ImageIO.read(inputStream);
			return image!=null?true:false;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 
	* @Title: getTimeFormat  
	* @Description: 获得指定时间的当前格式 
	* @param @param format 传入日期格式 入：yyyy-MM-dd
	* @param @return  
	* @return String
	* @throws
	 */
	public static String getTimeFormat(String format) {
		return DateFormatUtils.format(new Date(), format);
	}
	
	/**
	 * 
	* @Title: calcTagsCount  
	* @Description: 获取传入的article集合中的所有tag，去重并计算每个tag的数量， 
	* @param @param articles
	* @param @return  
	* @return List<TagsVO>
	* @throws
	 */
	public static List<TagsVO> calcTagsCount(List<Article> articles){
		StringBuilder sb = new StringBuilder();
		//将所有article的tag拼接到StringBuilder中
		for (int i = 0; i < articles.size(); i++) {
			String tagsStr = articles.get(i).getTags();
			sb.append(tagsStr);
			if(i<articles.size()-1)
				sb.append(',');
		}
		String tagsStr = sb.toString();
		//将tagsStr根据','分割，存入数组tagsArr
		String[] tagsArr = StringUtils.split(tagsStr, ",");
		
		//这一步是去重
		Set<String> tagsSet = new HashSet<String>();
		for (String tag : tagsArr) {
			tagsSet.add(tag);
		}
		//tagsList是要最终传给前端的
		List<TagsVO> tagsList = new ArrayList<TagsVO>();
		Iterator<String> iterator = tagsSet.iterator();
		//使用迭代器遍历set
		while(iterator.hasNext()) {
			String tag = iterator.next();
			TagsVO tagsVO = new TagsVO(tag,0);
			//遍历tagsArr数组，如果与set中的tag相等，则count++
			for (String str : tagsArr) {
				if(tag.equals(str)) {
					tagsVO.setCount(tagsVO.getCount()+1);
				}
			}
			tagsList.add(tagsVO);
		}
		return tagsList;
	}
	
}	
