package com.Saas.service.impl;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Saas.configuration.JwtProvider;
import com.Saas.domain.UserRole;
import com.Saas.exceptions.UserException;
import com.Saas.mapper.UserMapper;
import com.Saas.model.User;
import com.Saas.payload.dto.UserDto;
import com.Saas.payload.response.AuthResponse;
import com.Saas.repository.UserRepository;
import com.Saas.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
	
	private  UserRepository userRepository;
	private  PasswordEncoder passwordEncoder;
	private  JwtProvider jwtProvider = new JwtProvider();
	private  CustomeUserImplementation customeUserImplementation;
	
	public AuthServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            CustomeUserImplementation customeUserImplementation) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.customeUserImplementation = customeUserImplementation;
    }
	
	@Override
	public AuthResponse signup(UserDto userDto) throws UserException {
		
		User user = userRepository.findByEmail(userDto.getEmail());
		if(user != null) {
			throw new UserException("Email id already registered !");
		}
		
		if(userDto.getRole().equals(UserRole.ROLE_ADMIN)) {
			throw new UserException("role admin is not allowed !");
		}
		
		User newUser = new User();
		newUser.setEmail(userDto.getEmail());
		newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
		newUser.setRole(userDto.getRole());
		newUser.setFullName(userDto.getFullName());
		newUser.setPhone(userDto.getPhone());
		newUser.setLastLogin(LocalDateTime.now());
		newUser.setCreatedAt(LocalDateTime.now());
		
		newUser.setUpdatedAt(LocalDateTime.now());
		
		User savedUser = userRepository.save(newUser);
		
		Authentication authentication = 
				new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String jwt = jwtProvider.generateToken(authentication);
		
		AuthResponse authResponse = new AuthResponse();
		authResponse.setJwt(jwt);
		authResponse.setMessage("Registered Successfully ");
		authResponse.setUser(UserMapper.toDTO(savedUser));
		
		return authResponse;
	}

	@Override
	public AuthResponse login(UserDto userDto) throws UserException {
		String email = userDto.getEmail();
		String password = userDto.getPassword();
		
		Authentication authentication = authenticate(email, password);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		
		String role = authorities.iterator().next().getAuthority();
		
		String jwt = jwtProvider.generateToken(authentication);
		
		User user = userRepository.findByEmail(email);
		
		user.setLastLogin(LocalDateTime.now());
		userRepository.save(user);
		
		AuthResponse authResponse = new AuthResponse();
		authResponse.setJwt(jwt);
		authResponse.setMessage("Login Successfully ");
		authResponse.setUser(UserMapper.toDTO(user));
		
		return authResponse;
	}

	private Authentication authenticate(String email, String password) throws UserException {
		
		UserDetails userDetails = customeUserImplementation.loadUserByUsername(email);
		
		if(userDetails == null) {
			throw new UserException("Email id doesn't exixt" + email);
		}
		
		if(!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new UserException("Password doesn't mathch");
		}
		
		
		
		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}

}
