package org.cskj.vhr.controller.system.basic;

import java.util.List;

import org.cskj.vhr.bean.Position;
import org.cskj.vhr.service.PositionService;
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
@RequestMapping("/system/basic/pos")
public class PositionController {

	@Autowired
	private PositionService positionService;
	
	// 查询
	@GetMapping("/")
	public List<Position> getAllPositions(){
		return positionService.getAllPositions();
	}
	// 添加
	@PostMapping("/")
	public ResponseBean addPosition(@RequestBody Position position) {
		if(positionService.insertPosition(position) > 0) {
			return ResponseBean.ok("添加成功!");
		}
		return ResponseBean.error("添加失败");
	}
	// 修改
	@PutMapping("/")
	public ResponseBean updatePosition(@RequestBody Position position) {
		if(positionService.updatePosition(position) > 0) {
			return ResponseBean.ok("修改成功!");
		}
		return ResponseBean.error("修改失败");
	}
	// 删除
	@DeleteMapping("/{id}")
	public ResponseBean deletePosition(@PathVariable Integer id) {
		if(positionService.deletePosition(id) > 0) {
			return ResponseBean.ok("删除成功!");
		}
		return ResponseBean.error("删除失败");
	}
	// 批量删除
	@DeleteMapping("/")
	public ResponseBean deleteMany(Integer[] ids) {
		if(positionService.deleteMany(ids) == ids.length) {
			return ResponseBean.ok("删除成功!");
		}
		return ResponseBean.error("删除失败");
	}
}
