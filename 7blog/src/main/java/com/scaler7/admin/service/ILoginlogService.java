package com.scaler7.admin.service;

import com.scaler7.admin.domain.Loginlog;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author scaler7
 * @since 2019-12-06
 */
public interface ILoginlogService extends IService<Loginlog> {
	
	List<Loginlog> getAllLoginlogs();
	
	PageInfo<Loginlog> getLoginlogOnePage(int pageNum, int pageSize);
	
	Loginlog saveLoginlog(Loginlog loginlog);
}
