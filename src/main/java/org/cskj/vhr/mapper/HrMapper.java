package org.cskj.vhr.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cskj.vhr.bean.Hr;
import org.cskj.vhr.bean.Role;

public interface HrMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Hr record);

    int insertSelective(Hr record);

    Hr selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Hr record);

    int updateByPrimaryKey(Hr record);

	Hr loadUserByUsername(String username);

	List<Role> getHrRoleById(Integer id);

	List<Hr> getAllHrs(@Param("id")Integer id,@Param("keywords") String keywords);

}