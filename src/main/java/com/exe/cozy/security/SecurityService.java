package com.exe.cozy.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.exe.cozy.customer.CustomerService;
import com.exe.cozy.domain.CustomerDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service("securityService")
public class SecurityService implements UserDetailsService {
	
	@Autowired CustomerService customerSerivce;
	
	//사용자명으로 비밀번호를 조회해서 리턴하는 메소드
	@Override
	public UserDetails loadUserByUsername(String customerEmail) throws UsernameNotFoundException {
		
		//사용자명으로 SiteUser 객체를 조회
		CustomerDto customerDto = customerSerivce.getReadData(customerEmail);
				
		if(customerDto==null) { //isEmpty()
			throw new UsernameNotFoundException("이메일 또는 비밀번호가 일치하지 않습니다.");
		}
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		if("관리자".equals(customerDto.getCustomerName())) {
			authorities.add(new SimpleGrantedAuthority(CustomerRole.ADMIN.getValue()));
		}else {
			authorities.add(new SimpleGrantedAuthority(CustomerRole.USER.getValue()));
		}
				
		//사용자명, 비밀번호, 권한을 입력해서 스프링 시큐리티의 user객체를 리턴
		//내가 만든 유저가 아니라 스프링 자체에 있는 유저
		return new User(customerDto.getCustomerEmail(),customerDto.getCustomerPwd(),authorities);
	
	}

}
