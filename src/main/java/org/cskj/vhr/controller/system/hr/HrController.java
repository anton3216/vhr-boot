package org.cskj.vhr.controller.system.hr;

import java.util.List;

import org.cskj.vhr.bean.Hr;
import org.cskj.vhr.bean.Role;
import org.cskj.vhr.service.HrService;
import org.cskj.vhr.service.RoleService;
import org.cskj.vhr.utils.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system/hr")
public class HrController {

	@Autowired
	private HrService hrService;
	@Autowired
	private RoleService roleService;
	
	@GetMapping("/")
	public List<Hr> getAllHrs(String keywords){
		return hrService.getAllHrs(keywords);
	}
	
	@GetMapping("/roles")
	public List<Role> getAllRoles(){
		return roleService.getAllRoles();
	}
	
	@DeleteMapping("/{id}")
	public ResponseBean deleteHr(@PathVariable Integer id) {
		if(hrService.deleteHr(id)) {
			return ResponseBean.ok("删除成功");
		}
		return ResponseBean.error("删除失败!");
	}
	
	@PutMapping("/")
	public ResponseBean updateHr(@RequestBody Hr hr) {
		if(hrService.updateHr(hr) == 1) {
			return ResponseBean.ok("修改成功");
		}
		return ResponseBean.error("修改失败!");
	}
	
	@PutMapping("/updateHrRole")
	public ResponseBean updateHrRole(Integer hrid,Integer[] rids) {
		if(hrService.updateHrRole(hrid,rids)) {
			return ResponseBean.ok("修改成功");
		}
		return ResponseBean.error("修改失败!");
	}
}
