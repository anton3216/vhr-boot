package org.cskj.vhr.service;

import java.util.Date;
import java.util.List;

import org.cskj.vhr.bean.JobLevel;
import org.cskj.vhr.mapper.JobLevelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobLevelService {

	@Autowired
	private JobLevelMapper JobLevelMapper;
	
	public List<JobLevel> getAllJobLevels(){
		return JobLevelMapper.getAllJobLevels();
	}
	
	public int insertSelective(JobLevel jobLevel) {
		jobLevel.setCreateDate(new Date());
		jobLevel.setEnabled(true);
		return JobLevelMapper.insertSelective(jobLevel);
	}

	public int deleteJobLevel(Integer id) {
		return JobLevelMapper.deleteByPrimaryKey(id);
	}
	
	public int updateJobLevel(JobLevel jobLevel) {
		return JobLevelMapper.updateByPrimaryKeySelective(jobLevel);
	}

	public int deleteMany(Integer[] ids) {
		return JobLevelMapper.deleteMany(ids);
	}
}
