package com.scaler7.admin.service;

import com.scaler7.admin.domain.Link;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author scaler7
 * @since 2019-12-06
 */
public interface ILinkService extends IService<Link> {
	
	List<Link> getLinkList(Link link);
	
	Link saveLink(Link link);
	
	void removeLink(Integer id);
	
	Link updateLink(Link link);
}
