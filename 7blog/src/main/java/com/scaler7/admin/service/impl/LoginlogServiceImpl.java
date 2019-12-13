package com.scaler7.admin.service.impl;

import com.scaler7.admin.domain.Loginlog;
import com.scaler7.admin.mapper.LoginlogMapper;
import com.scaler7.admin.service.ILoginlogService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
public class LoginlogServiceImpl extends ServiceImpl<LoginlogMapper, Loginlog> implements ILoginlogService {

	@Autowired
	LoginlogMapper loginlogMapper;
	
	@Override
	public List<Loginlog> getAllLoginlogs() {
		List<Loginlog> loginlogs = loginlogMapper.selectList(null);
		return loginlogs;
	}

	@Override
	public PageInfo<Loginlog> getLoginlogOnePage(int pageNum, int pageSize) {
		QueryWrapper<Loginlog> wrapper = new QueryWrapper<Loginlog>();
		wrapper.orderByDesc("login_time"); // 降序排列
		PageHelper.startPage(pageNum, pageSize);
		List<Loginlog> list = loginlogMapper.selectList(wrapper);
		PageInfo<Loginlog> pageInfo = new PageInfo<Loginlog>(list);
		return pageInfo;
	}

	@Override
	public Loginlog saveLoginlog(Loginlog loginlog) {
		loginlogMapper.insert(loginlog);
		return loginlog;
	}



}
