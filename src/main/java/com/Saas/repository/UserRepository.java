package com.Saas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Saas.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByEmail(String email);

}
