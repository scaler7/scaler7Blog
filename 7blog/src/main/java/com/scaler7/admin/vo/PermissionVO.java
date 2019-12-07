package com.scaler7.admin.vo;


import com.scaler7.admin.domain.Permission;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PermissionVO extends Permission {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6215912550078286660L;
	
	private Integer limit=10;
	private Integer page=1;

}
