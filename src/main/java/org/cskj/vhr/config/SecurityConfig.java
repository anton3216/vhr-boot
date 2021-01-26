package org.cskj.vhr.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cskj.vhr.bean.Hr;
import org.cskj.vhr.service.HrService;
import org.cskj.vhr.utils.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private HrService hrService;
	@Autowired
	private MyFilter myFilter;
	@Autowired
	private MyDicisionManager myDicisionManager;
	
	// 密码加密
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// 调用hrservice中的
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(hrService);
	}
	
	// 为前台响应json数据
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// http相当于登录验证的拦截器
		http.authorizeRequests()
		//.anyRequest().authenticated()// 任何请求都要经过认证之后才能访问
		.withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
			@Override
			public <O extends FilterSecurityInterceptor> O postProcess(O object) {
				object.setAccessDecisionManager(myDicisionManager);
				object.setSecurityMetadataSource(myFilter);
				return object;
			}
		})
		.and()
		.formLogin()// 当遇到表单登录行为时
		.usernameParameter("username")//从登录请求中获取username参数
		.passwordParameter("password")
		.loginProcessingUrl("/doLogin")// 处理登录请求的url
		.loginPage("/login")//登录页面url
		.successHandler(new AuthenticationSuccessHandler() {
			/*
			 * 	登录成功时的回调函数
			 * 	authentication 登陆成功之后的用户信息
			 */
			@Override
			public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
					Authentication authentication) throws IOException, ServletException {
				// 设置响应格式
				response.setContentType("application/json;charset=utf-8");
				PrintWriter out = response.getWriter();
				// 获取用户信息
				Hr hr = (Hr) authentication.getPrincipal();
				ResponseBean ok = ResponseBean.ok("登录成功", hr);
				String string = new ObjectMapper().writeValueAsString(ok);
				/*
				 * {
				 * 	status : "200",
				 * 	msg : "登录成功",
				 * 	obj : {
				 * 		name : "系统管理员",
				 * 		id : 3,
				 * 		username : "admin"
				 * 	}
				 * }
				 */
				out.write(string);
				out.flush();
				out.close();
			}
		})
		.failureHandler(new AuthenticationFailureHandler() {
			/*
			 * 	登录失败时的回调函数
			 */
			@Override
			public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
					AuthenticationException exception) throws IOException, ServletException {
				// 设置响应格式
				response.setContentType("application/json;charset=utf-8");
				PrintWriter out = response.getWriter();
				ResponseBean error = ResponseBean.error(exception.getMessage());
				if(exception instanceof LockedException) {
					error.setMsg("账户被锁定,请联系管理员!");
				}else if(exception instanceof CredentialsExpiredException) {
					error.setMsg("密码过期,请联系管理员!");
				}else if(exception instanceof AccountExpiredException) {
					error.setMsg("账户过期,请联系管理员!");
				}else if(exception instanceof DisabledException) {
					error.setMsg("账户被禁用,请联系管理员!");
				}else if(exception instanceof BadCredentialsException) {
					error.setMsg("用户名或密码错误,请重新输入!");
				}
				String string = new ObjectMapper().writeValueAsString(error);
				out.write(string);
				out.flush();
				out.close();
			}
		})
		.permitAll()//所有接口都直接返回
		.and()
		.logout()//注销登录
		.logoutSuccessHandler(new LogoutSuccessHandler() {
			/*
			 * 	注销成功时的回调函数
			 */
			@Override
			public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
					throws IOException, ServletException {
				response.setContentType("application./json;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.write(new ObjectMapper().writeValueAsString(ResponseBean.ok("注销成功")));
				out.flush();
				out.close();
			}
		})
		.permitAll()
		.and()
		.csrf().disable()
		.exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPoint() {
			// 给SpringSecurity添加一个配置项
			// 告诉springsecurity 不重定向
			// 如果不配置,默认重定向
			@Override
			public void commence(HttpServletRequest request, HttpServletResponse response,
					AuthenticationException authException) throws IOException, ServletException {
				response.setContentType("application./json;charset=utf-8");
				// 设置状态码
				response.setStatus(401);
				PrintWriter out = response.getWriter();
				ResponseBean responseBean = ResponseBean.error("访问失败");
				if(authException instanceof InsufficientAuthenticationException) {
					responseBean.setMsg("请求失败,请联系管理员");
				}
				out.write(new ObjectMapper().writeValueAsString(responseBean));
				out.flush();
				out.close();
			}
		});
	}
}
