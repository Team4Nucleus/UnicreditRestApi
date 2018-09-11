package com.unicredit.cap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unicredit.cap.busineslogic.AppUserService;
import com.unicredit.cap.model.AppRola;


@RestController
@RequestMapping("/rest/rola")
public class AppRolaController {
	
	
	 @Autowired 
	 private AppUserService service;	 
	 
	 @GetMapping(value = "/all")
	    public List<AppRola> findAll() {
	        return service.getAllRoles();
	    }
	  
}
