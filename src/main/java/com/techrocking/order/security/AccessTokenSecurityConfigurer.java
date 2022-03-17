package com.techrocking.order.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class AccessTokenSecurityConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
	
	@Autowired
	private AccessTokenFilter accessTokenFilter;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.addFilterBefore(accessTokenFilter, UsernamePasswordAuthenticationFilter.class);
	}

}
