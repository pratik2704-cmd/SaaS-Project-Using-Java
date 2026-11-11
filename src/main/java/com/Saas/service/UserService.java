package com.Saas.service;

import java.util.List;

import com.Saas.exceptions.UserException;
import com.Saas.model.User;

public interface UserService {
	
	User getUserFromJwtToken(String Token) throws UserException;
	User getCurrentUser() throws UserException;
	User getUserByEmail(String email) throws UserException;
	User getUserById(long id) throws UserException, Exception;
	List<User> getAllUsers();

}
