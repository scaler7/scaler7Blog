package com.scaler7.common;

import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.scaler7.admin.domain.Categories;

/**
 * 
* @ClassName: scaler7
* @Description: 封装了Admin模块前端页面需要用到的一些方法 
* @author Zzz  
* @date 2019年12月4日  
*
 */
@Component(value = "adminCommon")
public final class AdminCommon {
	
	/**
	 * 
	* @Title: existCategories  
	* @Description: 判断article的category属性是否在categories中有相等的，以便前端实现默认选中 
	* @param @param category
	* @param @param categories
	* @param @return  
	* @return boolean
	* @throws
	 */
	public static boolean existCategories(Categories category,String categories) {
		String[] catArr = StringUtils.split(categories, ",");
		if(ArrayUtils.isNotEmpty(catArr)) {
			for (String c : catArr) {
				if(c.trim().equals(category.getName())) {
					return true;
				}
			}
		}
		return false;
	}
    /**
     * 定义颜色样式数组
     */
    private static final String[] COLORS = {"default", "primary", "success", "info", "warning", "danger", "inverse", "purple", "pink"};

    /**
     * 随机样式
     * @return
     */
    public static String rand_color() {
        //int r = Tools.rand(0, COLORS.length - 1);
        return COLORS[0];
    }
	
}
