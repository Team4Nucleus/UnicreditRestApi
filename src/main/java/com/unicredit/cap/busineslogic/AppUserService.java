package com.unicredit.cap.busineslogic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unicredit.cap.model.AppRola;
import com.unicredit.cap.model.AppUser;
import com.unicredit.cap.repository.DbContext;

@Service
public class AppUserService {


	@Autowired
	private DbContext db;
	
	public List<AppRola> getUserRoles(String username)
	{
		
		AppUser user = db.AppUser().findByUsername(username);
		
		return user.getRoles();
		
	}
	
	public List<AppRola> getAllRoles()
	{
		
		List<AppRola> role = db.Rola().findAll();
		return role;
		
	}
	
	
	public AppUser getUserByUsername(String username)
	{
		
		AppUser user = db.AppUser().findByUsername(username);
		
		
		return user;
		
	}

	
}
