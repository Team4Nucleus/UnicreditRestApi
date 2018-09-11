package com.unicredit.cap.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unicredit.cap.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	 User findByUsername(String username);
	
}
