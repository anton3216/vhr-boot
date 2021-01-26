package org.cskj.vhr.controller.config;

import java.util.List;

import org.cskj.vhr.bean.Menu;
import org.cskj.vhr.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system/config")
public class SystemConfigController {

	@Autowired
	private MenuService menuService;
	
	@GetMapping("/menu")
	public List<Menu> getMenusByHrId(){
		return menuService.getMenusByHrId();
	}
}
