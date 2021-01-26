package org.cskj.vhr.controller.employee.basic;

import java.util.List;

import org.cskj.vhr.bean.Employee;
import org.cskj.vhr.bean.JobLevel;
import org.cskj.vhr.bean.Nation;
import org.cskj.vhr.bean.Politicsstatus;
import org.cskj.vhr.bean.Position;
import org.cskj.vhr.service.EmployeeService;
import org.cskj.vhr.service.JobLevelService;
import org.cskj.vhr.service.PositionService;
import org.cskj.vhr.utils.ResponseBean;
import org.cskj.vhr.utils.ResponsePageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("employee/basic")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private JobLevelService jobLevelService;
	@Autowired
	private PositionService positionService;

	@GetMapping("/getAllEmps")
	/*
	 * 1. 当前页 2.分页单位
	 */
	public ResponsePageBean getAllEmps(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer size, String keyword) {
		return employeeService.getAllEmps(page, size, keyword);
	}

	@GetMapping("/getworkid")
	public String getMaxWorkID() {
		return String.format("%08d", employeeService.getMaxWorkID() + 1);
	}

	@GetMapping("/getAllPol")
	public List<Politicsstatus> getAllPol() {
		return employeeService.getAllPol();
	}

	@GetMapping("/getAllNations")
	public List<Nation> getAllNations() {
		return employeeService.getAllNations();
	}

	@GetMapping("/getAllPositions")
	public List<Position> getAllPositions() {
		return positionService.getAllPositions();
	}

	@GetMapping("/getAllJobLevels")
	public List<JobLevel> getAllJobLevels() {
		return jobLevelService.getAllJobLevels();
	}
	
	@PostMapping("/addEmp")
	public ResponseBean addEmp(@RequestBody Employee emp) {
		if(employeeService.addEmp(emp) == 1) {
			return ResponseBean.ok("添加成功!");
		}
		return ResponseBean.error("添加失败!");
	}
	
	@PutMapping("/updateEmp")
	public ResponseBean updateEmp(@RequestBody Employee emp) {
		if(employeeService.updateEmp(emp) == 1) {
			return ResponseBean.ok("更新成功!");
		}
		return ResponseBean.error("更新失败!");
	}
	
	@DeleteMapping("/deleteEmp/{id}")
	public ResponseBean deleteEmp(@PathVariable Integer id) {
		if(employeeService.deleteEmp(id) == 1) {
			return ResponseBean.ok("更新成功!");
		}
		return ResponseBean.error("更新失败!");
	} 
}
