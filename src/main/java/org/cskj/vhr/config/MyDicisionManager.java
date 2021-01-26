package org.cskj.vhr.config;

import java.util.Collection;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

/*
 * 	这个类主要是判断当前登录的用户是否具有对应的角色
 */
@Component
public class MyDicisionManager implements AccessDecisionManager{

	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		/*
		 * 1.authentication 当前登录的用户
		 * 2.object 请求对象
		 * 3.configAttributes 角色
		 */
		// 遍历数组
		for (ConfigAttribute configAttribute : configAttributes) {
			// 获得需要的角色
			String needRole = configAttribute.getAttribute();
			// 判断用户是否具有相应的角色
			if("ROLE_LOGIN".equals(needRole)) {
				// 如果当前用户是匿名用户的一个实例,说明没有登录
				if(authentication instanceof AnonymousAuthenticationToken) {
					throw new AccessDeniedException("尚未登录,请先登录");
				}else {
					// 如果不抛异常,则表明这个用户合法
					return;
				}
			}
			
			// 获取当前登录用户的角色
			Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
			for (GrantedAuthority authority : authorities) {
				// 任意一个角色匹配,则说明具备对应角色
				if(authority.getAuthority().equals(needRole)) {
					return;
				}
			}
		}
		throw new AccessDeniedException("权限不足,请联系管理员");
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return true;
	}

}
