package schoolsys.config;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.csrf.CsrfToken;

import com.fasterxml.jackson.databind.ObjectMapper;

import schoolsys.base.bean.CsrfTokenBean;
import schoolsys.user.service.UserService;

@Configuration
/**
 * 认证和鉴权配置
 */
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	// 用户服务类
	private UserService userService;
	
	@Autowired
	private ObjectMapper objectMapper;
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic()
				// 未登录时
				.authenticationEntryPoint((request, response, authException) -> {
					response.setContentType("application/json;charset=utf-8");
					response.setStatus(HttpServletResponse.SC_FORBIDDEN);
					PrintWriter out = response.getWriter();
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("code", 403);
					map.put("message", "未登录");
					out.write(objectMapper.writeValueAsString(map));
					out.flush();
					out.close();
				})
				.and().authorizeRequests().antMatchers("/doLogin.do").permitAll()
				.anyRequest().authenticated() // 必须授权才能范围
				.and().formLogin().loginPage("/login/getToken.do").loginProcessingUrl("/doLogin.do")
				.permitAll()
				// 登录失败，返回json
				.failureHandler((request, response, ex) -> {
					response.setContentType("application/json;charset=utf-8");
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					PrintWriter out = response.getWriter();
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("code", 401);
					if (ex instanceof UsernameNotFoundException || ex instanceof BadCredentialsException) {
						map.put("message", "用户名或密码错误");
					} else if (ex instanceof DisabledException) {
						map.put("message", "账户被禁用");
					} else {
						map.put("message", "登录失败!");
					}
					out.write(objectMapper.writeValueAsString(map));
					out.flush();
					out.close();
				})
				// 登录成功，返回json
				.successHandler((request, response, authentication) -> {
				    request.getRequestDispatcher("/login/loginSuccuess.do").forward(request, response);  
				}).and().exceptionHandling()
				// 没有权限，返回json
				.accessDeniedHandler((request, response, ex) -> {
					response.setContentType("application/json;charset=utf-8");
					response.setStatus(HttpServletResponse.SC_FORBIDDEN);
					PrintWriter out = response.getWriter();
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("code", 403);
					map.put("message", "权限不足");
					out.write(objectMapper.writeValueAsString(map));
					out.flush();
					out.close();
				}).and().logout()
				// 退出成功，返回json
				.logoutSuccessHandler((request, response, authentication) -> {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("code", 200);
					map.put("message", "退出成功");
					map.put("data", authentication);
					response.setContentType("application/json;charset=utf-8");
					PrintWriter out = response.getWriter();
					out.write(objectMapper.writeValueAsString(map));
					out.flush();
					out.close();
				}).permitAll();

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 设置加密器
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		// 密码加密器
		return new BCryptPasswordEncoder();
	}

}
