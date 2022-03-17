package com.techrocking.order.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
public class WebSecurityConfig {
	
	@Configuration
	@Order(1)
	public static class NonSecureApiConfigurationAdapter extends WebSecurityConfigurerAdapter {

		protected void configure(HttpSecurity http) throws Exception {
			http.antMatcher("/swagger-ui/**").authorizeRequests().anyRequest().permitAll();			
		}
	}

	@Configuration
	@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
	public static class ScureApiConfigurationAdapter extends WebSecurityConfigurerAdapter {

		@Autowired
		private AccessTokenSecurityConfigurer accessTokenSecurityConfigurer;

		protected void configure(HttpSecurity http) throws Exception {

			http.cors().and().csrf().disable()
					.sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
					.and()
					.antMatcher("/v1/orders")
					.authorizeRequests()
					.anyRequest().authenticated()
					.and()
					.apply(accessTokenSecurityConfigurer);
		}

		@Bean
		CorsConfigurationSource corsConfigurationSource() {
			final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
			CorsConfiguration configuration = new CorsConfiguration();
			configuration.setAllowCredentials(true);
			configuration.addAllowedOrigin("*");
			configuration.addAllowedHeader("*");
			configuration.addAllowedMethod("*");
			configuration.addExposedHeader("Authorization");
			source.registerCorsConfiguration("/**", configuration);
			return source;
		}
	}

}
