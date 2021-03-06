package com.unicredit.cap.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;
import com.unicredit.cap.helper.ViewProfile;

@Entity
@Table(name="HRUSER")
public class User {

    @Id
    @JsonView(ViewProfile.Placement.class)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
	private long id;
    
    @JsonView(ViewProfile.Placement.class)
    @Column(name = "HRORGANIZATION")
	private long hrOrganization;
	
    @JsonView(ViewProfile.Placement.class)
    @Column(name = "NAME")
	private String name;
	
    @JsonView(ViewProfile.Placement.class)
    @Column(name = "USERNAME")
	private String username;
	
    @JsonView(ViewProfile.Placement.class)
    @Column(name = "EMAIL")
	private String email;

    /*
    @ManyToOne
    @JoinColumn(name = "USERNAME", referencedColumnName = "USERNAME")
	private AppUser appUser;
    */
    
	public long getId() {
		return id;
	}

	/*
	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}
*/
	
	public void setId(long id) {
		this.id = id;
	}

	public long getHrOrganization() {
		return hrOrganization;
	}

	public void setHrOrganization(long hrOrganization) {
		this.hrOrganization = hrOrganization;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
    
	 
}
