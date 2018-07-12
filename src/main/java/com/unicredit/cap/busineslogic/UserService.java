package com.unicredit.cap.busineslogic;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unicredit.cap.exception.CapNotFoundException;
import com.unicredit.cap.model.User;
import com.unicredit.cap.repository.DbContext;

@Service
public class UserService {

	@Autowired
	private DbContext db;
	
	public List<User> getAllUser(){
		
		return db.Usere().findAll();
		
	}
	
	
	public User getUserById(Long id){
		
		Optional<User> user = db.Usere().findById(id);
		
		if(!user.isPresent())
			 throw new CapNotFoundException("User with id=" + id + " was not found");
		
		return user.get();
		
	}
}
