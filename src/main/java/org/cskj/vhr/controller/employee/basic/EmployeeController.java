package org.cskj.vhr.controller.employee.basic;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.cskj.vhr.bean.Employee;
import org.cskj.vhr.bean.JobLevel;
import org.cskj.vhr.bean.Nation;
import org.cskj.vhr.bean.Politicsstatus;
import org.cskj.vhr.bean.Position;
import org.cskj.vhr.mapper.NationMapper;
import org.cskj.vhr.mapper.PoliticsstatusMapper;
import org.cskj.vhr.service.DepartmentService;
import org.cskj.vhr.service.EmployeeService;
import org.cskj.vhr.service.JobLevelService;
import org.cskj.vhr.service.PositionService;
import org.cskj.vhr.utils.PoiUtil;
import org.cskj.vhr.utils.ResponseBean;
import org.cskj.vhr.utils.ResponsePageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("employee/basic")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private JobLevelService jobLevelService;
	@Autowired
	private PositionService positionService;
	@Autowired
	private DepartmentService departmentService;

	@GetMapping("/getAllEmps")
	/*
	 * 1. 当前页 2.分页单位
	 */
	public ResponsePageBean getAllEmps(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer size, Employee emp, Date[] beginDateScope) {
		if (emp != null && beginDateScope != null) {
			System.out.println(emp);
			System.out.println(beginDateScope[0]);
			System.out.println(beginDateScope[1]);
		}
		return employeeService.getAllEmps(page, size, emp, beginDateScope);
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
		if (employeeService.addEmp(emp) == 1) {
			return ResponseBean.ok("添加成功!");
		}
		return ResponseBean.error("添加失败!");
	}

	@PutMapping("/updateEmp")
	public ResponseBean updateEmp(@RequestBody Employee emp) {
		if (employeeService.updateEmp(emp) == 1) {
			return ResponseBean.ok("更新成功!");
		}
		return ResponseBean.error("更新失败!");
	}

	@DeleteMapping("/deleteEmp/{id}")
	public ResponseBean deleteEmp(@PathVariable Integer id) {
		if (employeeService.deleteEmp(id) == 1) {
			return ResponseBean.ok("更新成功!");
		}
		return ResponseBean.error("更新失败!");
	}

	@GetMapping("/export")
	public ResponseEntity<byte[]> exportData() {
		List<Employee> list = (List<Employee>) employeeService.getAllEmps(null, null, null, null).getData();
		return PoiUtil.employeeToExcel(list);
	}

	@PostMapping("/import")
	public ResponseBean inportData(MultipartFile file) throws IllegalStateException, IOException {
		List<Employee> list = PoiUtil.excelToEmployee(file, departmentService.getAllDepts(),
				employeeService.getAllNations(), positionService.getAllPositions(), jobLevelService.getAllJobLevels(),
				employeeService.getAllPol());
		if (employeeService.addEmps(list) == list.size()) {
			return ResponseBean.ok("上传成功!");
		}
		return ResponseBean.error("上传失败!");
	}
}
