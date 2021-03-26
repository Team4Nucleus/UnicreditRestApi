package com.unicredit.cap.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unicredit.cap.model.AppRola;
import com.unicredit.cap.model.AppUser;

public interface AppRolaRepository  extends JpaRepository<AppRola, Long>{
	
	AppRola findByRolename(String rolename);
	
}
