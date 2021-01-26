package org.cskj.vhr.service;

import java.util.Date;
import java.util.List;

import org.cskj.vhr.bean.Employee;
import org.cskj.vhr.bean.JobLevel;
import org.cskj.vhr.bean.Nation;
import org.cskj.vhr.bean.Politicsstatus;
import org.cskj.vhr.bean.Position;
import org.cskj.vhr.mapper.EmployeeMapper;
import org.cskj.vhr.mapper.NationMapper;
import org.cskj.vhr.mapper.PoliticsstatusMapper;
import org.cskj.vhr.utils.ResponsePageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeMapper employeeMapper;
	@Autowired
	private PoliticsstatusMapper politicsstatusMapper;
	@Autowired
	private NationMapper nationMapper;

	public ResponsePageBean getAllEmps(Integer page, Integer size, Employee emp, Date[] beginDateScope) {
		if(page != null) {
			page = (page - 1) * size;
		}
		// data 数据
		List<Employee> list = employeeMapper.getAllEmps(page, size, emp , beginDateScope);
		// total 总条数 count
		Long total = employeeMapper.getTotal(emp,beginDateScope);
		return new ResponsePageBean(total, list);
	}

	public int getMaxWorkID() {
		return employeeMapper.getMaxWorkID();
	}

	public List<Politicsstatus> getAllPol() {
		return politicsstatusMapper.getAllPol();
	}

	public List<Nation> getAllNations() {
		// TODO Auto-generated method stub
		return nationMapper.getAllNations();
	}

	public int addEmp(Employee emp) {
		return employeeMapper.insertSelective(emp);
	}

	public int updateEmp(Employee emp) {
		return employeeMapper.updateByPrimaryKeySelective(emp);
	}

	public int deleteEmp(Integer id) {
		return employeeMapper.deleteByPrimaryKey(id);
	}

	public int addEmps(List<Employee> list) {
		return employeeMapper.addEmps(list);
	}

}
