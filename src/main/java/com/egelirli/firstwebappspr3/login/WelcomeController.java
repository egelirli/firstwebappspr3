package com.egelirli.firstwebappspr3.login;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class WelcomeController {

	//@Autowired
	//private AuthenticationService  userService;

	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String goToWelcomePage(ModelMap model) {
		model.put("name", getLoggedInUSerName());
		//System.out.println("Request param is " + name); //NOT RECOMMENDED FOR PROD CODE
		return "welcome";
	}

	private String getLoggedInUSerName() {
		
		Authentication  authent =
					SecurityContextHolder.getContext().getAuthentication();
		return authent.getName();
	}
//	@RequestMapping(name = "login2", method = RequestMethod.GET)
//	public String gotoLoginPostPage() {
//			return "welcome";
//		
//	}
	
	
//	@RequestMapping(value = "login", method = RequestMethod.POST)
//	public String gotoLoginPostPage(
//			                    @RequestParam String name,
//								@RequestParam String password,
//								ModelMap model) {
//		
//		if(userService.isUserValid(name, password)) {
//			model.put("name", name);
//			//System.out.println("Request param is " + name); //NOT RECOMMENDED FOR PROD CODE
//			return "welcome";
//			
//		}else {
//			model.put("errorMessage", "Invalid User!");
//			return "login";
//		}
//	}
//
//	@RequestMapping(value = "welcome", method = RequestMethod.GET)
//	public String goToWelcomePage() {
//		
//		//System.out.println("Request param is " + name); //NOT RECOMMENDED FOR PROD CODE
//		return "welcome";
//	}
//	
	
}
