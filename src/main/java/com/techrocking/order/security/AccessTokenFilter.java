package com.techrocking.order.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

@Component
public class AccessTokenFilter extends GenericFilterBean {
	
	@Autowired
	private AccessTokenValidator accessTokenValidator;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		// access_token set in header by zuul RouteFilter
		String access_token = httpRequest.getHeader("access_token");
		
		/*
		 * if(access_token == null) {
		 * sendUnsuccessfulAuthentication(httpRequest,httpResponse); }
		 */
		
		//VerifyTokenResponse tokenResponse =  accessTokenValidator.verifyToken(access_token);
		VerifyTokenResponse fakeTokenResponse = new VerifyTokenResponse();
		fakeTokenResponse.setUid("abcnddddd");
		fakeTokenResponse.setUsername("abc@gmail.com");
		fakeTokenResponse.setActive(Boolean.TRUE);
		
		if (fakeTokenResponse != null && fakeTokenResponse.getActive()) {
			Authentication auth = accessTokenValidator.createAuthentication(fakeTokenResponse);
			SecurityContextHolder.getContext().setAuthentication(auth);
			chain.doFilter(request, response);
		} else {
			sendUnsuccessfulAuthentication(httpRequest,httpResponse);
		}
		
	}
	
	private void sendUnsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		SecurityContextHolder.clearContext();
		String json = String.format("{\"message\": \"%s\"}", "Unauthorized Access!");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

}
