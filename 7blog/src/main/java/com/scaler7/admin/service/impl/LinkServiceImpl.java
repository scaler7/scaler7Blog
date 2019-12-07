package com.scaler7.admin.service.impl;

import com.scaler7.admin.domain.Link;
import com.scaler7.admin.mapper.LinkMapper;
import com.scaler7.admin.service.ILinkService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

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
 * @since 2019-12-06
 */
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements ILinkService {

	@Autowired
	LinkMapper linkMapper;
	
	@Override
	public List<Link> getLinkList(Link link) {
		QueryWrapper<Link> wrapper = new QueryWrapper<Link>();
		if(null != link) {
			
		}
		List<Link> links = linkMapper.selectList(wrapper);
		return links;
	}

	@Override
	@CachePut(cacheNames = "com.scaler7.admin.service.impl.LinkServiceImpl",key="#link.id")
	public Link saveLink(Link link) {
		linkMapper.insert(link);
		return link;
	}

	@Override
	@CacheEvict(cacheNames = "com.scaler7.admin.service.impl.LinkServiceImpl",key="#id")
	public void removeLink(Integer id) {
		linkMapper.deleteById(id);
	}

	@Override
	@CachePut(cacheNames = "com.scaler7.admin.service.impl.LinkServiceImpl",key="#link.id")
	public Link updateLink(Link link) {
		linkMapper.updateById(link);
		return link;
	}

}
