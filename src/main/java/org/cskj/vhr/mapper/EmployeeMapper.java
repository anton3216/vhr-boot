package org.cskj.vhr.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cskj.vhr.bean.Employee;
import org.cskj.vhr.bean.Nation;
import org.cskj.vhr.bean.Politicsstatus;

public interface EmployeeMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Employee record);

	int insertSelective(Employee record);

	Employee selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Employee record);

	int updateByPrimaryKey(Employee record);

	List<Employee> getAllEmps(@Param("page") Integer page, @Param("size") Integer size, @Param("emp") Employee emp,
			@Param("beginDateScope") Date[] beginDateScope);

	Long getTotal(@Param("emp") Employee emp, @Param("beginDateScope") Date[] beginDateScope);

	int getMaxWorkID();

	int addEmps(@Param("list") List<Employee> list);

}