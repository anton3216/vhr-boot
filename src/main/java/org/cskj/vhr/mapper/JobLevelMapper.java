package org.cskj.vhr.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cskj.vhr.bean.JobLevel;

public interface JobLevelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JobLevel record);

    int insertSelective(JobLevel record);

    JobLevel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JobLevel record);

    int updateByPrimaryKey(JobLevel record);

	List<JobLevel> getAllJobLevels();

	int deleteMany(@Param("ids") Integer[] ids);
}