package com.egelirli.firstwebappspr3.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

	@Autowired
	private UserValidationService  userService;

	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String goToayLoginPage() {
		
		//System.out.println("Request param is " + name); //NOT RECOMMENDED FOR PROD CODE
		return "login";
	}

	
	@RequestMapping(name = "login2", method = RequestMethod.GET)
	public String gotoLoginPostPage() {
			return "welcome";
		
	}
	
	
//	@RequestMapping(name = "login", method = RequestMethod.POST)
//	public String gotoLoginPostPage(
//			                    @RequestParam String name,
//								@RequestParam String password,
//								ModelMap model) {
//		
//		if(userService.isUserValid(name, password)) {
//			model.put("name", name);
//			model.put("password", password);
//			//System.out.println("Request param is " + name); //NOT RECOMMENDED FOR PROD CODE
//			return "welcome";
//			
//		}else {
//			model.put("errorMessage", "Invalid User!");
//			return "login";
//		}
//	}

	@RequestMapping(value = "welcome", method = RequestMethod.GET)
	public String goToWelcomePage() {
		
		//System.out.println("Request param is " + name); //NOT RECOMMENDED FOR PROD CODE
		return "welcome";
	}
	
	
}
