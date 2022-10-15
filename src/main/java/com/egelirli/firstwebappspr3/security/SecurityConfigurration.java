package com.egelirli.firstwebappspr3.security;


import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfigurration {

	//LDAP Config
	//In memory
	
	@Bean
	public InMemoryUserDetailsManager createUserDetailsManager() {
		
		Function<String, String> passwdEncoder = 
					input ->  passwordEncoder().encode(input);
		
		
		UserDetails userDetails1 = createNewUserDetail(passwdEncoder, "veli", "dummy");
		UserDetails userDetails2 = createNewUserDetail(passwdEncoder, "deli", "mummy");
		
		return new InMemoryUserDetailsManager(userDetails1, userDetails2);	 
	}


	private UserDetails createNewUserDetail(
			             Function<String, String> passwdEncoder, 
			             String username, 
			             String password) {
		
		UserDetails userDetails = User.builder()
				 .passwordEncoder(passwdEncoder)
				 .username(username)
				 .password(password)
				 .roles("ADMIN", "DEVELOPER")
				 .build();
		return userDetails;
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	
	
	
	
	
}
