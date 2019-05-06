package com.unicredit.cap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.unicredit.cap.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	 User findByUsername(String username);
	 
	 
	 @Query(value = "SELECT h.* from HRUSER h" + 
	 		"	 join APPUSER u on u.USERNAME = h.USERNAME" + 
	 		"	 join APPUSERROLE r on r.APPUSER = u.ID" + 
	 		"	 join APPROLE rol on rol.ID = r.APPROLE" + 
	 		"	 WHERE rol.ROLENAME  = 'ROLE_MANAGER'" + 
	 		"	 and h.HRORGANIZATION = ? ",  nativeQuery = true)
		public List<User> findHrManagerOfOrg(long l);
	 
	 
	
}
