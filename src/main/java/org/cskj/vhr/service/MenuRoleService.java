package org.cskj.vhr.service;


import org.cskj.vhr.mapper.MenuRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MenuRoleService {

	@Autowired
	private MenuRoleMapper menuRoleMapper;
	
	@Transactional // 开启事务
	public boolean updateMenuRole(Integer rid,Integer[] mids) {
		// 1.根据rid删除
		menuRoleMapper.deleteByRid(rid);
		if(mids == null || mids.length == 0) {
			return true;
		}
		// 2.根据mid和rid添加
		Integer result = menuRoleMapper.insertMenuRole(rid,mids);
		return result == mids.length;
	}
}
