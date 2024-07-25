package com.yiron.spring_security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.yiron.spring_security.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	

	@Autowired
	private MyUserDetailsService userDetailsService;

	// implementation of spring security

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(registry -> {
			registry.requestMatchers("/home", "/register/**").permitAll();
			registry.requestMatchers("/admin/**").hasRole("ADMIN");
			registry.requestMatchers("/user/**").hasRole("USER");
			registry.anyRequest().authenticated();

		}).formLogin(httpSecurityFormLoginConfigurer -> {
			httpSecurityFormLoginConfigurer.loginPage("/login").
			successHandler(new AuthenticationSuccessHandler()).permitAll();
		});
		return http.build();
	}

//	@Bean
//	UserDetailsService userDetailsService() {
//		UserDetails normalUser = User.builder().username("gc")
//				.password("$2a$10$ehrAKPha5ah6sHbxZq9C2Oak9cJnFR0AZfEhudBSVzsHmKfQbbvfu").roles("USER").build();
//		UserDetails adminUser = User.builder().username("admin")
//				.password("$2a$10$5Lekl0IeRyv2L1XIZwHMsexAxDopYy16B/XZpDgGBmULkcBRonULu").roles("ADMIN").build();
//
//		return new InMemoryUserDetailsManager(normalUser, adminUser);
//
//	}

	@Bean
	UserDetailsService userDetailsService() {
		return userDetailsService;
	}

	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
