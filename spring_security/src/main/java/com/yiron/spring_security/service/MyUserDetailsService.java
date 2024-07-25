package com.yiron.spring_security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.yiron.spring_security.dao.MyUserRepo;
import com.yiron.spring_security.model.MyUser;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private MyUserRepo repo;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		System.out.println(userName);
		Optional<MyUser> user = repo.findByUserName(userName);
		if(user.isPresent()) {
			var userObj=user.get();
			return User.builder()
					.username(userObj.getUserName())
					.password(userObj.getPassword())
					.roles(getRoles(userObj))
					.build();
			
		}else {
			throw new UsernameNotFoundException(userName);
		}
	}

	private String[] getRoles(MyUser user) {
		if(user.getRole()==null) {
			return new String[] {"USER"};
		}
		return user.getRole().split(",");
	}

}
