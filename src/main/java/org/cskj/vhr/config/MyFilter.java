package org.cskj.vhr.config;

import java.util.Collection;
import java.util.List;

import org.cskj.vhr.bean.Menu;
import org.cskj.vhr.bean.Role;
import org.cskj.vhr.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

/*
 * 	这个过滤器主要是
 * 	通过用户传来的请求,分析出所需要的角色
 */
@Component
public class MyFilter implements FilterInvocationSecurityMetadataSource{
	
	@Autowired
	private MenuService menuService;
	private AntPathMatcher antPathMatcher = new AntPathMatcher();

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		// object就是FilterInvocation对象
		// 从filterinvocation对象中能获取当前访问的url
		String url = ((FilterInvocation) object).getRequestUrl();
		// 调用方法
		List<Menu> menus = menuService.getAllMenusWithRole();
		// 进行比对
		for (Menu menu : menus) {
			// 第一个参数从数据库查询的url
			// 第二个参数是当前访问的url
			if(antPathMatcher.match(menu.getUrl(), url)) {
				List<Role> roles = menu.getRoles();
				String[] roleNames = new String[roles.size()];
				for (int i = 0; i < roleNames.length; i++) {
					roleNames[i] = roles.get(i).getName();
				}
				return SecurityConfig.createList(roleNames);
			}
		}
		// 没有匹配的url 统一登陆之后才能访问
		// 只是一个标记,在第二步时进行判断处理
		return SecurityConfig.createList("ROLE_LOGIN");
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}
