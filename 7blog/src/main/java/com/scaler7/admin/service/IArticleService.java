package com.scaler7.admin.service;

import com.scaler7.admin.domain.Article;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

/**
 * 
* @ClassName: scaler7
* @Description: 文章模块接口 
* @author Zzz  
* @date 2019年12月4日  
*
 */
public interface IArticleService extends IService<Article> {
	/**
	 * 
	* @Title: getArticleList  
	* @Description: 根据传入的article组合查询条件，全查询符合条件的article 
	* @param @param article
	* @param @return  
	* @return List<Article>
	* @throws
	 */
	List<Article> getArticleList(Article article);

	/**
	 * 
	* @Title: getArticleOnePage  
	* @Description: 分页查询 
	* @param @param page
	* @param @param limit
	* @param @return  
	* @return PageInfo<Article>
	* @throws
	 */
	PageInfo<Article> getArticleOnePage(int page, int limit);
	/**
	 * 
	* @Title: getOneArticle  
	* @Description: 根据id获取1个article 
	* @param @param id
	* @param @return  
	* @return Article
	* @throws
	 */
	Article getOneArticle(Integer id);
	/**
	 * 
	* @Title: updateArticle  
	* @Description: 更新article(根据id) 
	* @param @param article
	* @param @return  
	* @return Article
	* @throws
	 */
	Article updateArticle(Article article);
	/**
	 * 
	* @Title: saveArticle  
	* @Description: 添加article 
	* @param @param article
	* @param @return  
	* @return Article
	* @throws
	 */
	Article saveArticle(Article article);
	/**
	 * 
	* @Title: delArticle  
	* @Description: 删除article 
	* @param @param id
	* @param @return  
	* @return Integer
	* @throws
	 */
	Integer delArticle(Integer id);
}
