package com.Saas.service.impl;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.Saas.configuration.JwtProvider;
import com.Saas.exceptions.UserException;
import com.Saas.model.User;
import com.Saas.repository.UserRepository;
import com.Saas.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	
	private UserRepository userRepository;
	private JwtProvider jwtProvider;

	@Override
	public User getUserFromJwtToken(String Token) throws UserException {
		
		String email = jwtProvider.getEmailFromToken(Token);
		User user = userRepository.findByEmail(email);
		if(user == null) {
			throw new UserException("Invalid Token");
		}
		
		return user;
	}

	@Override
	public User getCurrentUser() throws UserException {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByEmail(email);
		if(user == null) {
			throw new UserException("User not found");
		}
		return user;
	}

	@Override
	public User getUserByEmail(String email) throws UserException {
		User user = userRepository.findByEmail(email);
		if(user == null) {
			throw new UserException("User not found");
		}
		return user;
	}

	@Override
	public User getUserById(long id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

}
