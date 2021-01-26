package org.cskj.vhr.mapper;

import java.util.List;

import org.cskj.vhr.bean.Politicsstatus;

public interface PoliticsstatusMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Politicsstatus record);

    int insertSelective(Politicsstatus record);

    Politicsstatus selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Politicsstatus record);

    int updateByPrimaryKey(Politicsstatus record);

	List<Politicsstatus> getAllPol();
}