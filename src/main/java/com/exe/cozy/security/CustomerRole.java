package com.exe.cozy.security;

import lombok.Getter;

@Getter
public enum CustomerRole {
	
	ADMIN("ROLE_ADMIN"),
	USER("ROLE_USER");

	private String value;
	
	CustomerRole(String value){
		this.value = value;
	}
}
