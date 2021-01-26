package org.cskj.vhr.service;

import java.util.List;

import org.cskj.vhr.bean.Hr;
import org.cskj.vhr.bean.Menu;
import org.cskj.vhr.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class MenuService {
	
	@Autowired
	private MenuMapper menuMapper;

	public List<Menu> getMenusByHrId() {
		// 怎么获得id
		Hr hr = (Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return menuMapper.getMenusByHrId(hr.getId());
	}

	public List<Menu> getAllMenusWithRole() {
		return menuMapper.getAllMenusWithRole();
	}

	public List<Menu> getAllMenus() {
		return menuMapper.getAllMenus();
	}

	public List<Integer> getMidByRid(Integer rid) {
		return menuMapper.getMidByRid(rid);
	}
}
