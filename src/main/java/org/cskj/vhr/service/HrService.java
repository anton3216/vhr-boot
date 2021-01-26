package org.cskj.vhr.service;

import java.util.List;

import org.cskj.vhr.bean.Hr;
import org.cskj.vhr.mapper.HrMapper;
import org.cskj.vhr.mapper.HrRoleMapper;
import org.cskj.vhr.utils.HrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HrService implements UserDetailsService {

	@Autowired
	private HrMapper hrMapper;
	@Autowired
	private HrRoleMapper hrRoleMapper;

	// 按照hr名查询HR对象
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Hr hr = hrMapper.loadUserByUsername(username);
		// 判断 处理异常
		if (hr == null) {
			throw new UsernameNotFoundException("用户名不存在");
		}
		hr.setRoles(hrMapper.getHrRoleById(hr.getId()));
		return hr;
	}

	public List<Hr> getAllHrs(String keywords) {
		return hrMapper.getAllHrs(HrUtil.getCurrentHr().getId(),keywords);
	}

	@Transactional
	public boolean deleteHr(Integer id) {
		try {
			// 1. 删除本身
			hrMapper.deleteByPrimaryKey(id);
			// 2. 删除中间表
			hrRoleMapper.deleteByHrId(id);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	public int updateHr(Hr hr) {
		return hrMapper.updateByPrimaryKeySelective(hr);
	}

	@Transactional
	public boolean updateHrRole(Integer hrid, Integer[] rids) {
		try {
			// 1. 删hrid
			hrRoleMapper.deleteByHrId(hrid);
			if (rids == null || rids.length == 0) {
				return true;
			}
			// 2. 添加
			hrRoleMapper.insertHrRole(hrid, rids);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

}
