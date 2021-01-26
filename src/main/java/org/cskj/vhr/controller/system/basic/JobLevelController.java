package org.cskj.vhr.controller.system.basic;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.cskj.vhr.bean.JobLevel;
import org.cskj.vhr.service.JobLevelService;
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
@RequestMapping("system/basic/jl")
public class JobLevelController {

	@Autowired
	private JobLevelService jobLevelService;
	
	@GetMapping("/")
	public List<JobLevel> getAllJobLevels(){
		return jobLevelService.getAllJobLevels();
	}
	
	@PostMapping("/")
	public ResponseBean addJobLevel(@RequestBody JobLevel jobLevel) {
		if(jobLevelService.insertSelective(jobLevel) == 1) {
			return ResponseBean.ok("添加成功!");
		}
		return ResponseBean.error("添加失败!");
	}
	
	@DeleteMapping("/{id}")
	public ResponseBean deleteJobLevel(@PathVariable Integer id) {
		if(jobLevelService.deleteJobLevel(id) == 1) {
			return ResponseBean.ok("删除成功!");
		}
		return ResponseBean.error("删除失败!");
	}
	
	@PutMapping("/")
	public ResponseBean updateJobLevel(@RequestBody JobLevel jobLevel) {
		if(jobLevelService.updateJobLevel(jobLevel) == 1) {
			return ResponseBean.ok("修改成功!");
		}
		return ResponseBean.error("修改失败!");
	}
	
	@DeleteMapping("/")
	public ResponseBean deleteMany(Integer[] ids) {
		if(jobLevelService.deleteMany(ids) == ids.length) {
			return ResponseBean.ok("删除成功!");
		}
		return ResponseBean.error("删除失败!");
	}
}
