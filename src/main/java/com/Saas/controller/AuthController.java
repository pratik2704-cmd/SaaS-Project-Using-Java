package com.Saas.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Saas.exceptions.UserException;
import com.Saas.payload.dto.UserDto;
import com.Saas.payload.response.AuthResponse;
import com.Saas.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")

public class AuthController {
	
	private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

	
	//localhost:8080/auth/signup	
	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> signupHandler(
			@RequestBody UserDto userDto
			) throws UserException{
		return ResponseEntity.ok(
				authService.signup(userDto)
				
				);
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> loginHandler(
			@RequestBody UserDto userDto
			) throws UserException{
		return ResponseEntity.ok(
				authService.login(userDto)
				
				);
	}

}
