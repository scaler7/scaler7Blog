package com.scaler7.admin.service;

import com.scaler7.admin.domain.Attach;
import com.scaler7.common.Result;

import org.springframework.web.multipart.MultipartFile;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

/**
 * 
* @ClassName: scaler7
* @Description: 附件接口 
* @author Zzz  
* @date 2019年12月4日  
*
 */
public interface IAttachService extends IService<Attach> {
	/**
	 * 
	* @Title: getAttachList  
	* @Description: 分页查询 
	* @param @param pageNum
	* @param @param pageSize
	* @param @return  
	* @return PageInfo<Attach>
	* @throws
	 */
	PageInfo<Attach> getAttachList(int pageNum, int pageSize);
	/**
	 * 
	* @Title: saveFile  
	* @Description: 上传附件 
	* @param @param file
	* @param @param attach
	* @param @return  
	* @return Result
	* @throws
	 */
	Result saveFile(MultipartFile file,Attach attach);
	/**
	 * 
	* @Title: removeAttach  
	* @Description: 删除附件 
	* @param @param id
	* @param @return  
	* @return Result
	* @throws
	 */
	Result removeAttach(Integer id);
	
}
