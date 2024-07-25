package com.yiron.spring_security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	
	@GetMapping("/home")
	public String home() {
		return "home";
		
	}
	
	@GetMapping("/admin/home")
	public String adminHome() {
		return "adminHome";
	}
	@GetMapping("/user/home")
	public String userHome() {
		return "userHome";
	}
	
	@GetMapping("/login")
	public String login() {
		return "custom_login";
	}
	

}
