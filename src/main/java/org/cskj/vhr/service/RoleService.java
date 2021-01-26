package org.cskj.vhr.service;

import java.util.List;

import org.cskj.vhr.bean.Role;
import org.cskj.vhr.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

	@Autowired
	private RoleMapper roleMapper;
	
	public List<Role> getAllRoles(){ 
		return roleMapper.getAllRoles();
	}

	public int insertRole(Role role) {
		// 拼接ROLE_ springscurity
		if(!role.getName().startsWith("ROLE_")) {
			role.setName("ROLE_" + role.getName());
		}
		return roleMapper.insertSelective(role);
	}

	public int deleteRole(Integer rid) {
		return roleMapper.deleteByPrimaryKey(rid);
	}
}
