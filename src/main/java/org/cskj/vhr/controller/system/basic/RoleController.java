package org.cskj.vhr.controller.system.basic;

import java.util.List;

import org.cskj.vhr.bean.Menu;
import org.cskj.vhr.bean.Role;
import org.cskj.vhr.service.MenuRoleService;
import org.cskj.vhr.service.MenuService;
import org.cskj.vhr.service.RoleService;
import org.cskj.vhr.utils.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("system/basic/premission")
public class RoleController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private MenuService menuService;
	@Autowired
	private MenuRoleService menuRoleService;
	
	@GetMapping("/")
	public List<Role> getAllRoles(){
		return roleService.getAllRoles();
	}
	
	@GetMapping("/menus")
	public List<Menu> getAllMenus(){
		return menuService.getAllMenus();
	}
	
	@GetMapping("/mids/{rid}")
	public List<Integer> getMidsByRid(@PathVariable Integer rid){
		return menuService.getMidByRid(rid);
	}
	
	@PutMapping("/")
	public ResponseBean updateMenuRole(Integer rid,Integer[] mids) {
		if(menuRoleService.updateMenuRole(rid,mids)) {
			return ResponseBean.ok("修改成功!");
		}
		return ResponseBean.error("修改失败!");
	}
	
	@PostMapping("/")
	public ResponseBean insertRole(@RequestBody Role role) {
		if(roleService.insertRole(role) == 1) {
			return ResponseBean.ok("添加成功!");
		}
		return ResponseBean.error("添加失败!");
	}
	
	@DeleteMapping("/{rid}")
	public ResponseBean deleteRole(@PathVariable Integer rid) {
		if(roleService.deleteRole(rid) == 1) {
			return ResponseBean.ok("删除成功!");
		}
		return ResponseBean.error("删除失败!");
	}
}
