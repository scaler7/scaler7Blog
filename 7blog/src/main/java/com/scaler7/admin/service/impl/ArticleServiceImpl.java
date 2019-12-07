package com.scaler7.admin.service.impl;

import com.scaler7.admin.domain.Article;
import com.scaler7.admin.mapper.ArticleMapper;
import com.scaler7.admin.service.IArticleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author scaler7
 * @since 2019-11-27
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {
	
	@Autowired
	ArticleMapper articleMapper;
	
	@Override
	public List<Article> getArticleList(Article article) {
		QueryWrapper<Article> wrapper = new QueryWrapper<Article>();
		if(null != article) {
			wrapper.eq(StringUtils.isNotEmpty(article.getCategories()), "categories", article.getCategories());
			wrapper.like(StringUtils.isNotEmpty(article.getTags()), "tags", article.getTags());
		}
		wrapper.orderByDesc("created"); // 根据时间降序
		List<Article> articles = articleMapper.selectList(wrapper);
		return articles;
	}

	@Override
	public PageInfo<Article> getArticleOnePage(int pageNum, int pageSize) {
		QueryWrapper<Article> wrapper = new QueryWrapper<Article>();
		wrapper.orderByDesc("created"); // 根据时间降序
		PageHelper.startPage(pageNum, pageSize); // 开启分页
		List<Article> list = articleMapper.selectList(wrapper);
		PageInfo<Article> pageInfo = new PageInfo<Article>(list);
		return pageInfo;
	}

	@Override
	@Cacheable(cacheNames = "com.scaler7.admin.service.impl.ArticleServiceImpl",key = "#id")
	public Article getOneArticle(Integer id) {
		Article article = articleMapper.selectById(id);
		return article;
	}

	@Override
	@CachePut(cacheNames = "com.scaler7.admin.service.impl.ArticleServiceImpl",key="#article.id")
	public Article updateArticle(Article article) {
		articleMapper.updateById(article);
		return article;
	}

	@Override
	@CachePut(cacheNames = "com.scaler7.admin.service.impl.ArticleServiceImpl",key="#article.id")
	public Article saveArticle(Article article) {
		articleMapper.insert(article);
		return article;
	}

	@Override
	@CacheEvict(cacheNames = "com.scaler7.admin.service.impl.ArticleServiceImpl",key="#id")
	public Integer delArticle(Integer id) {
		int row = articleMapper.deleteById(id);
		return row;
	}


	
	
	
}
