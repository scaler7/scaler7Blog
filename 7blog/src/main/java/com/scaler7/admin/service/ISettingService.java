package com.scaler7.admin.service;

import com.scaler7.admin.domain.Setting;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author scaler7
 * @since 2019-12-12
 */
public interface ISettingService extends IService<Setting> {
	
	/**
	 * 
	* @Title: getSettingList  
	* @Description: 获取所有设置信息 
	* @param @return  
	* @return List<Setting>
	* @throws
	 */
	List<Setting> getSettingList();
	
	/**
	 * 
	* @Title: batchUpdateSetting  
	* @Description: 批量更新setting
	* @param @param settings  
	* @return void
	* @throws
	 */
	void batchUpdateSetting(List<Setting> settings);
	/**
	 * 
	* @Title: updateSetting  
	* @Description: 更新setting 
	* @param @param setting
	* @param @return  
	* @return Setting
	* @throws
	 */
	Setting updateSetting(Setting setting);
	
}
