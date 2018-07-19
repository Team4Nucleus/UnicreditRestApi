package com.unicredit.cap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unicredit.cap.busineslogic.UserService;
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
	 public User findById(@PathVariable final Long id){
		
	    return service.getUserById(id);
	    }
	
}
