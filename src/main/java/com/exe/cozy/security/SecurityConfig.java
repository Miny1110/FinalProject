package com.exe.cozy.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration //이 클래스를 스프링부트의 환경세팅으로 만드는 어노테이션
@EnableWebSecurity //url이 security의 영향을 받게함
@EnableGlobalMethodSecurity(prePostEnabled = true) //Global -> 하나의 프로젝트 안의 모든 파일을 감시
public class SecurityConfig {
	
	//스프링 시큐리티에 등록
	@Autowired SecurityService securityService;

	@Bean //메소드의 객체 생성
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		//http.csrf().disable();
		//인증되지 않은 모든 요청을 허락
		http
		.authorizeRequests()
			.antMatchers("/reviewWrite").authenticated()
			.antMatchers("/qnaWrite_ok").authenticated()
			.antMatchers("/customer/**").hasRole("USER") //customer주소는 user권한만 접근 가능
			.antMatchers("/createItem").hasRole("ADMIN")
			.antMatchers("/createItem_ok").hasRole("ADMIN")//admin주소는 admin권한만 접근 가능
			.anyRequest().permitAll() //나머지 주소는 인증없이 접근가능
		.and()
			.formLogin()
				.usernameParameter("customerEmail")
				.passwordParameter("customerPwd")
				.loginPage("/login")
				.defaultSuccessUrl("/") //로그인이 성공하면 이 주소로 가라
		.and()
			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")) //이 주소와 일치하면 로그아웃
				.logoutSuccessUrl("/") //로그아웃 성공하면 여기로 이동
				.invalidateHttpSession(true) //세션을 삭제
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
