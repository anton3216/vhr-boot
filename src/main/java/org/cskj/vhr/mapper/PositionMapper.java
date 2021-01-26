package org.cskj.vhr.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cskj.vhr.bean.Position;

public interface PositionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Position record);

    int insertSelective(Position record);

    Position selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Position record);

    int updateByPrimaryKey(Position record);
    
    List<Position> getAllPositions();

	int deleteMany(@Param("ids") Integer[] ids);
}