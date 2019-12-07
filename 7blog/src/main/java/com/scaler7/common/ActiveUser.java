package com.scaler7.common;

import java.util.List;

import com.scaler7.admin.domain.Permission;
import com.scaler7.admin.domain.Role;
import com.scaler7.admin.domain.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActiveUser {
	
	private List<Permission> permission;
	private List<Role> role;
	private User user;
	
}
