package org.cskj.vhr.mapper;

import java.util.List;

import org.cskj.vhr.bean.Department;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);
    
    List<Department> getDeptWithParentId(Integer parentId);
    
    Department getDeptById(Integer parentId);
    
    List<Department> getAllDepts();
}