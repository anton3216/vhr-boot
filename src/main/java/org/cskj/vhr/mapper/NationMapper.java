package org.cskj.vhr.mapper;

import java.util.List;

import org.cskj.vhr.bean.Nation;

public interface NationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Nation record);

    int insertSelective(Nation record);

    Nation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Nation record);

    int updateByPrimaryKey(Nation record);

	List<Nation> getAllNations();
}