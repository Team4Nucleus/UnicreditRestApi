package com.unicredit.cap.busineslogic;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unicredit.cap.exception.CapNotFoundException;
import com.unicredit.cap.helper.AccountPassword;
import com.unicredit.cap.model.AppRola;
import com.unicredit.cap.model.AppUser;
import com.unicredit.cap.model.AppUserWrapper;
import com.unicredit.cap.model.User;
import com.unicredit.cap.repository.DbContext;

@Service
public class UserService {

	@Autowired
	private DbContext db;
	
	public List<User> getAllUser(){
		
		return db.User().findAll();
		
	}
		
	public User getUserById(Long id){
		
		User user = db.User().findOne(id);
		
		if(user == null)
			 throw new CapNotFoundException("User with id=" + id + " was not found");
		
		return user;
		
	}
	
	public AppUserWrapper getUserAndAppuserById(Long id){
		
		User user = db.User().findOne(id);
		
		if(user == null)
			 throw new CapNotFoundException("User with id=" + id + " was not found");
		
		
		User userNew = user;
		AppUser appUserNew = db.AppUser().findByUsername(userNew.getUsername());
		List<AppRola> appRolaNew = appUserNew.getRoles();
		
		AppUserWrapper appUserWrapper = new AppUserWrapper();
		appUserWrapper.setUser(userNew);
		appUserWrapper.setAppUser(appUserNew);
		appUserWrapper.setAppRola(appRolaNew);
		
		return  appUserWrapper;
	}
	
	public AppUserWrapper getUserAndAppuserByUsername(String username){
		
		User user = db.User().findByUsername(username);
		if(user == null)
			 throw new CapNotFoundException("User with username=" + username + " was not found");
		
		AppUser appUserNew = db.AppUser().findByUsername(username);
		List<AppRola> appRolaNew = appUserNew.getRoles();
		
		AppUserWrapper appUserWrapper = new AppUserWrapper();
		appUserWrapper.setUser(user);
		appUserWrapper.setAppUser(appUserNew);
		appUserWrapper.setAppRola(appRolaNew);
		
		return  appUserWrapper;
	}
	
	
	public User getUserByUsername(String username)
	{
		User user = db.User().findByUsername(username);
		
		if(user == null)
			 throw new CapNotFoundException("User with username =" + username + " was not found!");
		
		return user;
	}
	
	public AppUserWrapper createUser(User user, AppUser appUser, List<AppRola> role)
	{
	
		if (!user.getUsername().equals(appUser.getUsername()))
			 throw new CapNotFoundException("Missmatch in username between Employer and his user profile!");
		
		if (role.size() == 0)
			 throw new CapNotFoundException("User must have at least one role selected!");

		if(appUser.getId() == null || appUser.getId() == 0)
			if(db.AppUser().findByUsername(appUser.getUsername()) != null)
				 throw new CapNotFoundException("Username [" + appUser.getUsername() + "] allready exists!");

		
		appUser.setRoles(role);
		
		String pw_hash =  BCrypt.hashpw(appUser.getPassword(), BCrypt.gensalt()); 
		appUser.setPassword(pw_hash);
		
		db.AppUser().save(appUser);
		db.User().save(user);
		
		
		
		User userNew = db.User().findByUsername(appUser.getUsername());
		AppUser appUserNew = db.AppUser().findByUsername(appUser.getUsername());
		List<AppRola> appRolaNew = appUserNew.getRoles();
		
		AppUserWrapper appUserWrapper = new AppUserWrapper();
		appUserWrapper.setUser(userNew);
		appUserWrapper.setAppUser(appUserNew);
		appUserWrapper.setAppRola(appRolaNew);
		
		return appUserWrapper;
	}
	
	public AppUserWrapper updateUser(User user, AppUser appUser, List<AppRola> role)
	{
		
		if (!user.getUsername().equals(appUser.getUsername()))
			 throw new CapNotFoundException("Missmatch in username between Employer and his user profile!");
		
		if (role.size() == 0)
			 throw new CapNotFoundException("User must have at least one role selected!");

			if(db.AppUser().findByUsername(appUser.getUsername()) == null)
				 throw new CapNotFoundException("Username [" + appUser.getUsername() + "] doesn't exists!");

			
		AppUser currentAppUser = db.AppUser().findByUsername(appUser.getUsername());
		User	currentUser = db.User().findByUsername(user.getUsername());
		
		currentAppUser.setFirstname(appUser.getFirstname());
		currentAppUser.setLastname(appUser.getLastname());
		currentAppUser.setActive(appUser.getActive());
		currentAppUser.setRoles(role);
		
		if (!appUser.getPassword().equals("") && appUser.getPassword() != null)
		{
			String pw_hash =  BCrypt.hashpw(appUser.getPassword(), BCrypt.gensalt()); 
			currentAppUser.setPassword(pw_hash);
		}
		
		currentUser.setName(user.getName());
		currentUser.setEmail(user.getEmail());
		currentUser.setHrOrganization(user.getHrOrganization());
		
		
		db.AppUser().save(currentAppUser);
		db.User().save(currentUser);
		
		
		
		User userNew = db.User().findByUsername(appUser.getUsername());
		AppUser appUserNew = db.AppUser().findByUsername(appUser.getUsername());
		List<AppRola> appRolaNew = appUserNew.getRoles();
		
		AppUserWrapper appUserWrapper = new AppUserWrapper();
		appUserWrapper.setUser(userNew);
		appUserWrapper.setAppUser(appUserNew);
		appUserWrapper.setAppRola(appRolaNew);
		
		return appUserWrapper;
	}


	public AccountPassword changePassword(AccountPassword account) {
		// TODO Auto-generated method stub
		
		AppUser user = db.AppUser().findByUsername(account.getUsername());
		
		if (user == null)
			 throw new CapNotFoundException("Username " + account.getUsername() + " not found!");
		
		if(BCrypt.checkpw(account.getOldPassword(), user.getPassword()))
		{
			String pw_hash =  BCrypt.hashpw(account.getNewPassword(), BCrypt.gensalt()); 
			user.setPassword(pw_hash);
			
			db.AppUser().save(user);
			
			return account;
		}
		else throw new CapNotFoundException("Wrong Password!");
		
			
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
