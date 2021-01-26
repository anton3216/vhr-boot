package org.cskj.vhr.controller.system.basic;

import java.util.List;

import org.cskj.vhr.bean.Department;
import org.cskj.vhr.bean.Dept;
import org.cskj.vhr.service.DepartmentService;
import org.cskj.vhr.utils.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system/basic/dept")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;
	
	@GetMapping("/")
	public List<Department> getDeptWithParentId(){
		return departmentService.getDeptWithParentId();
	}
	
	@PostMapping("/")
	public ResponseBean addDept(@RequestBody Dept dept) {
		if(departmentService.addDept(dept)) {
			return ResponseBean.ok("添加成功!");
		}
		return ResponseBean.error("添加失败!");
	}
	
	@DeleteMapping("/{id}/{parentId}")
	public ResponseBean deleteDept(@PathVariable Integer id,@PathVariable Integer parentId) {
		if(departmentService.deleteDept(id,parentId)) {
			return ResponseBean.ok("删除成功!");
		}
		return ResponseBean.error("删除失败!");
	}
}
