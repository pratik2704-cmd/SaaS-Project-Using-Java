package com.Saas.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Saas.exceptions.UserException;
import com.Saas.mapper.UserMapper;
import com.Saas.model.User;
import com.Saas.payload.dto.UserDto;
import com.Saas.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
	
	private UserService userService;
	
	@GetMapping("/profile")
	public ResponseEntity<UserDto> getUserProfile(@RequestHeader("Authorization") String jwt) throws UserException{
		User user = userService.getUserFromJwtToken(jwt);
		
		return ResponseEntity.ok(UserMapper.toDTO(user));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id) throws UserException{
		User user = userService.getUserFromJwtToken(jwt);
		
		return ResponseEntity.ok(UserMapper.toDTO(user));
	}

}
