package com.scaler7.admin.service;

import com.scaler7.admin.domain.Comments;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

/**
 * 
* @ClassName: scaler7
* @Description: 评论模块接口 
* @author Zzz  
* @date 2019年12月4日  
*
 */
public interface ICommentsService extends IService<Comments> {
	/**
	 * 
	* @Title: getCommentsOnePage  
	* @Description: 分页查询 
	* @param @param pageNum
	* @param @param pageSize
	* @param @return  
	* @return PageInfo<Comments>
	* @throws
	 */
	PageInfo<Comments> getCommentsOnePage(int pageNum, int pageSize);
	/**
	 * 
	* @Title: getCommentsList  
	* @Description: 根据传入的comments，组合查询条件，全查询所有符合条件的comments 
	* @param @param comments
	* @param @return  
	* @return List<Comments>
	* @throws
	 */
	List<Comments> getCommentsList(Comments comments);
	
	void removeComments(Integer id);
	
	Comments passComment(Comments comments);
	
}
