package com.Saas.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Saas.model.User;
import com.Saas.repository.UserRepository;

@Service
public class CustomeUserImplementation implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	 public CustomeUserImplementation(UserRepository userRepository) {
	        this.userRepository = userRepository;
	    }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		
		GrantedAuthority authority = new SimpleGrantedAuthority(
				user.getRole().toString()
				);
		
		Collection<GrantedAuthority> authorities = 
				Collections.singletonList(authority);
		
		return new org.springframework.security.core.userdetails.User(
				user.getEmail(), user.getPassword(), authorities
				);
		
	}
}
