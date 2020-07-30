package schoolsys.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import schoolsys.user.service.UserService;

@Configuration
/**
 * 认证和鉴权配置
 */
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	// 用户服务类
	private UserService userService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic()
				.authenticationEntryPoint((request, response, authException) -> {
					// 未登录时
					request.getRequestDispatcher("/login/noLogin.do").forward(request, response);
				})
				.and().authorizeRequests().antMatchers("/doLogin.do", "/login/getToken.do").permitAll()
				// 必须授权才能范围
				.anyRequest().authenticated()
				.and().formLogin().loginPage("/login/getToken.do").loginProcessingUrl("/doLogin.do")
				.failureHandler((request, response, ex) -> {
					// 登录失败，返回json
					request.getRequestDispatcher("/login/loginFail.do").forward(request, response);
				})
				.successHandler((request, response, authentication) -> {
					// 登录成功，返回json
				    request.getRequestDispatcher("/login/loginSuccuess.do").forward(request, response);  
				}).and().logout().logoutUrl("/logout.do")
				.logoutSuccessHandler((request, response, authentication) -> {
					// 退出成功
					request.getRequestDispatcher("/login/logoutSuccess.do").forward(request, response);
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
