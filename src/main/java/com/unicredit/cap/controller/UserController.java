package com.unicredit.cap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unicredit.cap.busineslogic.AppUserService;
import com.unicredit.cap.busineslogic.UserService;
import com.unicredit.cap.model.AppRola;
import com.unicredit.cap.model.AppUser;
import com.unicredit.cap.model.AppUserWrapper;
import com.unicredit.cap.model.Placement;
import com.unicredit.cap.model.Task;
import com.unicredit.cap.model.User;

@RestController
@RequestMapping("/rest/user")
public class UserController {

	 @Autowired
	 private UserService service;
	 
	 @GetMapping(value = "/all")
	    public List<User> findAll() {
	        return service.getAllUser();
	    }

	 @GetMapping(value = "/{id}")
	 public AppUserWrapper findById(@PathVariable final Long id){
		
	    return service.getUserAndAppuserById(id);
	    }
	 
	 
	 @PostMapping(value = "/create")
	 public AppUserWrapper createUser(@RequestBody final AppUserWrapper userWrapper ) {       
	        
		 return service.createUser(userWrapper.getUser(),  userWrapper.getAppUser(), userWrapper.getAppRola());
		 
	    }
	 
	 
	 @GetMapping(value = "/name/{username}")
	 public AppUserWrapper findByUsername(@PathVariable final String username){
		
	    return service.getUserAndAppuserByUsername(username);
	    }
	 
	 
}
