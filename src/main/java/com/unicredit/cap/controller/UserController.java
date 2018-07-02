package com.unicredit.cap.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unicredit.cap.model.User;
import com.unicredit.cap.repository.UserRepository;

@RestController
@RequestMapping("/rest/user")
public class UserController {

	 @Autowired
	 private UserRepository repository;
	 
	 @GetMapping(value = "/all")
	    public List<User> findAll() {
	        return repository.findAll();
	    }

	 @GetMapping(value = "/{id}")
	 public Optional<User> findById(@PathVariable final Long id){
		
	    return repository.findById(id);
	    }
	
}
