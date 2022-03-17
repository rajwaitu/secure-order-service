package com.techrocking.order.security;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties
public class OktaUserDetails implements UserDetails  {
	
	private static final long serialVersionUID = 1L;
	
	  private final boolean enabled = true;
	  private final boolean credentialsNonExpired = true;
	  private final boolean accountNonLocked = true;
	  private final boolean accountNonExpired = true;

	  private String uid;
	  private String email;
	  private String name;

	  @JsonIgnore
	  @JsonDeserialize
	  private String password;

	  private String username;
	  private Object custom;


	  private List<GrantedAuthority> authorities;

	  public OktaUserDetails(String uid, String email, String name, String password, String username) {
	    this.uid = uid;
	    this.email = email;
	    this.name = name;
	    this.password = password;
	    this.username = username;
	  }

	  public OktaUserDetails() {

	  }

}
