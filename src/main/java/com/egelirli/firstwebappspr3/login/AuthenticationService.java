package com.egelirli.firstwebappspr3.login;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
     
	public boolean isUserValid(String user, String password) {
		
		if(user.equals("veli") && password.equals("dummy")) {
			return true;
		}else {
			return false;
		}
			
	}
	 
}
