package com.scaler7.admin.service.impl;

import com.scaler7.admin.domain.Comments;
import com.scaler7.admin.mapper.CommentsMapper;
import com.scaler7.admin.service.ICommentsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author scaler7
 * @since 2019-12-01
 */
@Service
public class CommentsServiceImpl extends ServiceImpl<CommentsMapper, Comments> implements ICommentsService {

	@Autowired
	CommentsMapper commentsMapper;
	
	@Override
	public PageInfo<Comments> getCommentsOnePage(int pageNum, int pageSize) {
		QueryWrapper<Comments> wrapper = new QueryWrapper<Comments>();
		wrapper.orderByDesc("created"); // 根据时间降序
		PageHelper.startPage(pageNum, pageSize);
		List<Comments> list = commentsMapper.selectList(wrapper);
		PageInfo<Comments> pageInfo = new PageInfo<Comments>(list);
		return pageInfo;
	}

	@Override
	public List<Comments> getCommentsList(Comments comments) {
		QueryWrapper<Comments> wrapper = new QueryWrapper<Comments>();
		if(null != comments) {
			wrapper.eq(comments.getArticleId()!=null, "article_id", comments.getArticleId());
		}
		List<Comments> commentsList = commentsMapper.selectList(wrapper);
		return commentsList;
	}

	@Override
	@CacheEvict(cacheNames = "com.scaler7.admin.service.impl.CommentsServiceImpl",key="#id")
	public void removeComments(Integer id) {
		commentsMapper.deleteById(id);
	}

	@Override
	@CachePut(cacheNames = "com.scaler7.admin.service.impl.CommentsServiceImpl",key="#comments.id")
	public Comments passComment(Comments comments) {
		commentsMapper.updateById(comments);
		return comments;
	}

}
