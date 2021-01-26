package org.cskj.vhr.service;

import java.util.List;

import org.cskj.vhr.bean.Department;
import org.cskj.vhr.bean.Dept;
import org.cskj.vhr.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentMapper departmentMapper;
	
	public List<Department> getDeptWithParentId(){
		return departmentMapper.getDeptWithParentId(-1);
	}

	@Transactional
	public boolean addDept(Dept dept) {
		try {
			Department childrenDept = dept.getChildrenDept();
			Department parentDept = dept.getParentDept();
			// 1.添加children
			childrenDept.setIsParent(false);
			departmentMapper.insertSelective(childrenDept);
			// 2.修改children
			childrenDept.setDepPath(parentDept.getDepPath() +"."+childrenDept.getId());
			departmentMapper.updateByPrimaryKeySelective(childrenDept);
			// 3.修改parent
			if(!parentDept.getIsParent()) {
				parentDept.setIsParent(true);
				departmentMapper.updateByPrimaryKeySelective(parentDept);
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	@Transactional
	public boolean deleteDept(Integer id, Integer parentId) {
		try {
			// 1. 删除children
			departmentMapper.deleteByPrimaryKey(id);
			// 2.查询parent
			Department dept = departmentMapper.getDeptById(parentId);
			// 3.判断parent.children.size()
			if(dept.getChildren().size() == 0) {
				//4. 修改
				dept.setIsParent(false);
				departmentMapper.updateByPrimaryKeySelective(dept);
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
}
