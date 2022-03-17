package com.techrocking.order.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class OktaAuthenticationToken extends AbstractAuthenticationToken {
	
	private static final long serialVersionUID = 1L;
	
	OktaUserDetails oktaUserDetails;

	public OktaAuthenticationToken(OktaUserDetails oktaUserDetails) {
		super(oktaUserDetails.getAuthorities());
		this.oktaUserDetails = oktaUserDetails;
	    super.setAuthenticated(true);
	}

	@Override
	public Object getCredentials() {
		return this.oktaUserDetails.getPassword();
	}

	@Override
	public Object getPrincipal() {
		return this.oktaUserDetails;
	}

}
