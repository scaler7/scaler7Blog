package com.scaler7.admin.service.impl;

import com.scaler7.admin.domain.Setting;
import com.scaler7.admin.mapper.SettingMapper;
import com.scaler7.admin.service.ISettingService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author scaler7
 * @since 2019-12-12
 */
@Service
public class SettingServiceImpl extends ServiceImpl<SettingMapper, Setting> implements ISettingService {

	@Autowired
	SettingMapper settingMapper;
	
	@Override
	public List<Setting> getSettingList() {
		List<Setting> settings = settingMapper.selectList(null);
		return settings;
	}
	
	@Override
	public void batchUpdateSetting(List<Setting> settings) {
		if(CollectionUtils.isNotEmpty(settings)) {
			for (Setting setting : settings) {
				this.updateSetting(setting);
			}
		}
	}

	@Override
	public Setting updateSetting(Setting setting) {
		QueryWrapper<Setting> wrapper = new  QueryWrapper<Setting>();
		if(null != setting) {
			wrapper.eq(StringUtils.isNotEmpty(setting.getSkey()), "skey", setting.getSkey());
		}
		settingMapper.update(setting, wrapper);
		return setting;
	}

}
