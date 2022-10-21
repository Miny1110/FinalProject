package com.exe.cozy.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration //이 클래스를 스프링부트의 환경세팅으로 만드는 어노테이션
@EnableWebSecurity //url이 security의 영향을 받게함
public class SecurityConfig {
	
	//스프링 시큐리티에 등록
	@Autowired SecurityService securityService;

	@Bean //메소드의 객체 생성
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		//인증되지 않은 모든 요청을 허락
		http
		.authorizeRequests().antMatchers("/**").permitAll() //모든 주소 허가
		.and()
		.formLogin().usernameParameter("customerEmail").passwordParameter("customerPwd").loginPage("/customer/login").defaultSuccessUrl("/") //로그인이 성공하면 이 주소로 가라
		;
		
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//스프링 시큐리티의 인증을 담당
	//AuthenticationManager의 Bean 생성시 스프링의 내부동작으로 인해
	//UserSecurityService와 PasswordEncoder(암호화)가 자동으로 설정된다.
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
}
