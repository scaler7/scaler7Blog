package com.scaler7.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.apache.commons.lang3.StringUtils;
import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.node.Node;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.stereotype.Component;

import com.github.pagehelper.PageInfo;
import com.vdurmont.emoji.EmojiParser;

/**
 * 
* @ClassName: scaler7
* @Description: 封装了前端页面需要用到的一些方法 
* @author Zzz  
* @date 2019年12月4日  
*
 */
@Component(value = "commons")
public final class Commons {

	/**
	 * 
	* @Title: isEmpty  
	* @Description: 判断后端返回的pageInfo是否为空 
	* @param @param <E>
	* @param @param pageInfo
	* @param @return  
	* @return boolean
	* @throws
	 */
	public static <E> boolean isEmpty(PageInfo<E> pageInfo) {
		return pageInfo == null || (pageInfo.getList() == null) || (pageInfo.getList().size() == 0);
	}

	/**
	 * 
	* @Title: subStr  
	* @Description: 切割字符串 
	* @param @param str
	* @param @param len
	* @param @return  
	* @return String
	* @throws
	 */
	public static String subStr(String str, Integer len) {
		if (null == len) {
			len = 100;
		}
		String tempStr = null;
		if (str.length() > len) {
			tempStr = str.substring(0, len);
			return tempStr + "...";
		}
		return str;
	}
	/**
	 * 
	* @Title: article  
	* @Description: 供前端调用，实现markDown转h5 
	* @param @param value
	* @param @return  
	* @return String
	* @throws
	 */
    public static String article(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.replace("<!--more-->", "\r\n");
            value = value.replace("<!-- more -->", "\r\n");
            return mdToHtml(value);
        }
        return "";
    }
    
    /**
     * 
    * @Title: mdToHtml  
    * @Description: markDown转html具体实现方法 
    * @param @param markdown
    * @param @return  
    * @return String
    * @throws
     */
    public static String mdToHtml(String markdown) {
        if (StringUtils.isBlank(markdown)) {
            return "";
        }
        java.util.List<Extension> extensions = Arrays.asList(TablesExtension.create());
        org.commonmark.parser.Parser parser = org.commonmark.parser.Parser.builder().extensions(extensions).build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder().extensions(extensions).build();
        String content = renderer.render(document);
        content = Commons.emoji(content);
        return content;

    }
    
    /**
     * 
    * @Title: emoji  
    * @Description: 转换emoji表情 
    * @param @param value
    * @param @return  
    * @return String
    * @throws
     */
    public static String emoji(String value) {
        return EmojiParser.parseToUnicode(value);
    }
    
    /**
     * 
    * @Title: social  
    * @Description: 返回给前端个人的社交信息 
    * @param @return  
    * @return Map<String,String>
    * @throws
     */
    public static Map<String, String> social() {
        Map<String, String> map = new HashMap<>();
        map.put("csdn", WebConstant.CSDN.getSocialUrl());
        map.put("github", WebConstant.GITHUB.getSocialUrl());
        map.put("gitee", WebConstant.GITEE.getSocialUrl());
        return map;
    }
    /**
     * 
    * @Title: getMonthAndDay  
    * @Description: 根据传入的时间格式字符串，转换为指定格式的时间格式字符串 
    * @param @param timeStr
    * @param @return  
    * @return String
    * @throws
     */
	public static String getMonthAndDay(String timeStr) {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date date = null;
    	try {
			date = sdf.parse(timeStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	SimpleDateFormat sdf1 = new SimpleDateFormat("MM");
    	SimpleDateFormat sdf2= new SimpleDateFormat("dd");
    	
    	String month = sdf1.format(date);
    	String day = sdf2.format(date);
    	
    	return month+"-"+day;
    }
	
	/**
	 * 
	* @Title: randomInt  
	* @Description: 生成指定范围的随机数 
	* @param @param member
	* @param @param suffix
	* @param @return  
	* @return String
	* @throws
	 */
    public static String randomInt(int member,String suffix) {
        Random random = new Random();
        return random.nextInt(member) + member + suffix;
    }
    
    public static String site_url(String prefix) {
    	return prefix;
    }
}
