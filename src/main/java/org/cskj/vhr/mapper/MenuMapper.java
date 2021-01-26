package org.cskj.vhr.mapper;

import java.util.List;

import org.cskj.vhr.bean.Menu;
import org.cskj.vhr.bean.Role;

public interface MenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

	List<Menu> getMenusByHrId(Integer hrid);

	List<Menu> getAllMenusWithRole();

	List<Menu> getAllMenus();

	List<Integer> getMidByRid(Integer rid);
}