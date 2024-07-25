package com.yiron.spring_security.resistercontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yiron.spring_security.dao.MyUserRepo;
import com.yiron.spring_security.model.MyUser;

@RestController
public class RegistrationController {
	
	@Autowired
	private MyUserRepo myUserRepo;
	
	@Autowired 
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/register/user")
	public MyUser createUser(@RequestBody MyUser user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return myUserRepo.save(user);
	}

}
