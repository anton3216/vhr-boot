package org.cskj.vhr.mapper;

import org.apache.ibatis.annotations.Param;
import org.cskj.vhr.bean.HrRole;

public interface HrRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HrRole record);

    int insertSelective(HrRole record);

    HrRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HrRole record);

    int updateByPrimaryKey(HrRole record);

	void deleteByHrId(@Param("hrid") Integer id);

	void insertHrRole(@Param("hrid") Integer hrid,@Param("rids")  Integer[] rids);
}