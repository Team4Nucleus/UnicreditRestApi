package com.unicredit.cap.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.unicredit.cap.model.Application;

public interface ApplicationRepository  extends JpaRepository<Application, Long>{

	@Query(
			  value = "SELECT * FROM APPLICATION", 
			  nativeQuery = true)
		public List<Application> getAllAplications();
	
	
	
	//public List<Application> getAllApplicationByCreateUser(int id, Sort sort);
	
	@Query(value = "SELECT * FROM APPLICATION WHERE ROWNUM <= 100 ORDER BY CREATE_DATE DESC",  nativeQuery = true)
	public List<Application> findTop100ByOrderByCreateDateDesc();
	
	@Query(value = "SELECT * FROM APPLICATION WHERE CREATE_USER = ? AND ROWNUM <= 100 ORDER BY CREATE_DATE DESC",  nativeQuery = true)
	public List<Application> findTop100ByCreateUserOrderByCreateDateDesc(int createUser);
	
	

}
