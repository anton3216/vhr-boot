package org.cskj.vhr.service;

import java.util.Date;
import java.util.List;

import org.cskj.vhr.bean.Position;
import org.cskj.vhr.mapper.PositionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PositionService {

	@Autowired
	private PositionMapper positionMapper;
	
	public List<Position> getAllPositions(){
		return positionMapper.getAllPositions();
	}
	
	public int insertPosition(Position position) {
		position.setEnabled(true);
		position.setCreateDate(new Date());
		return positionMapper.insertSelective(position);
	}

	public int deletePosition(Integer id) {
		return positionMapper.deleteByPrimaryKey(id);
	}

	public int updatePosition(Position position) {
		return positionMapper.updateByPrimaryKeySelective(position);
	}

	public int deleteMany(Integer[] ids) {
		return positionMapper.deleteMany(ids);
	}
}
