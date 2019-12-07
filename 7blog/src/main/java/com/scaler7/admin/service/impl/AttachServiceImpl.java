package com.scaler7.admin.service.impl;

import com.scaler7.admin.domain.Attach;
import com.scaler7.admin.mapper.AttachMapper;
import com.scaler7.admin.service.IAttachService;
import com.scaler7.common.CodeMsg;
import com.scaler7.common.Constant;
import com.scaler7.common.Result;
import com.scaler7.utils.CommonUtil;
import com.scaler7.utils.QiNiuCloud;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author scaler7
 * @since 2019-12-02
 */
@Service
public class AttachServiceImpl extends ServiceImpl<AttachMapper, Attach> implements IAttachService {

	@Autowired
	AttachMapper attachMapper;
	
	@Override
	public PageInfo<Attach> getAttachList(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Attach> list = attachMapper.selectList(null);
		PageInfo<Attach> pageInfo = new PageInfo<Attach>(list);
		return pageInfo;
	}

	@Override
	@CachePut(cacheNames = "com.scaler7.admin.service.impl.AttachServiceImpl",key = "#attach.id")
	public Result saveFile(MultipartFile file, Attach attach) {
		
		String filename = file.getOriginalFilename();
		String key = QiNiuCloud.upload(file, filename);
		if(StringUtil.isEmpty(key)) {
			return new Result(CodeMsg.UPLOAD_ERROR);
		}
		String created = CommonUtil.getTimeFormat(Constant.YYYY_MM_DD_HH_MM_SS);
		String type = null;
		try {
			type = CommonUtil.isImage(file.getInputStream())?Constant.IS_IMAGE:Constant.IS_FILE;
		} catch (IOException e) {
			e.printStackTrace();
		}
		attach.setFname(filename);
		attach.setFtype(type);
		attach.setFkey(filename);
		attach.setAuthorId(1);
		attach.setCreated(created);
		
		attachMapper.insert(attach);
		return new Result();
	}

	@Override
	@CacheEvict(cacheNames = "com.scaler7.admin.service.impl.AttachServiceImpl",key="#id")
	public Result removeAttach(Integer id) {
		Attach attach = attachMapper.selectById(id);
		if(null == attach)
			return new Result(CodeMsg.DELETE_ERROR);
		String fkey = attach.getFkey();
		boolean isSuccess = QiNiuCloud.delete(fkey);
		if(!isSuccess)
			return new Result(CodeMsg.DELETE_ERROR);
		attachMapper.deleteById(id);
		return new Result();
	}
}
