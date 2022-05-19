package com.study.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.study.handler.CustomAccessDeniedHandler;
import com.study.handler.CustomLoginSuccessHandler;
import com.study.service.CustomUserDetailService;

@EnableWebSecurity // security담당
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	private DataSource dataSource;
	
	public SecurityConfig(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	//아래 4개 ref설정 
	@Bean
	public CustomLoginSuccessHandler loginSuccessHandler() {
		return new CustomLoginSuccessHandler();
	}
	
	@Bean
	public CustomAccessDeniedHandler accessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailService();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	//http액션
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//한글 설정
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8");
		encodingFilter.setForceEncoding(true); //기존 인코딩이 있어도 설정한 값 사용
		http.addFilterBefore(encodingFilter, CsrfFilter.class);
		
//		<security:form-login login-page="/login" authentication-failure-url="/login-error",
//				                                 authentication-success-handler-ref="customLoginSuccessHandler"/>
		http.formLogin()
			.loginPage("/login")
			.failureUrl("/login-error")
			.successHandler(loginSuccessHandler());
	
//		<security:logout invalidate-session="true" logout-success-url="/" delete-cookies="remember-me"/>	
		http.logout()
			.invalidateHttpSession(true)
			.logoutSuccessUrl("/")
			.deleteCookies("remember-me");
		
//		<security:access-denied-handler ref="customAccessDeniedHandler" />
		http.exceptionHandling(exception -> exception.accessDeniedHandler(accessDeniedHandler()));
	
//		<security:remember-me data-source-ref="ds" token-validity-seconds="604800"/>
		http.rememberMe()
			.tokenRepository(persistentTokenRepository())
			.tokenValiditySeconds(604800);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
	}

	//토큰 저장소(db이용) 
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl jdbcTokenRepositoryImpl = new JdbcTokenRepositoryImpl();
		jdbcTokenRepositoryImpl.setDataSource(dataSource);
		return jdbcTokenRepositoryImpl;
		
	}
	
	
}
