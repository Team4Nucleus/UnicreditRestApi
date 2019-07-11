package com.unicredit.cap.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;
import com.unicredit.cap.helper.ViewProfile;

@Entity
@Table(name="HRORGANIZATION")
public class Organization {

    @Id
    @JsonView(ViewProfile.Placement.class)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
	private long id;
    
    @JsonView(ViewProfile.Placement.class)
    @Column(name = "CODE")
	private String code;
    
    @JsonView(ViewProfile.Placement.class)
    @Column(name = "NAME")
	private String name;
    
    @JsonView(ViewProfile.Placement.class)
    @Column(name = "DESCRIPTION")
	private String description;
    
    @JsonView(ViewProfile.Placement.class)
    @Column(name = "EMAIL")
    private String email;
    

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "hrOrganization")
    private List<User> users = new ArrayList<>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
    
	

	
}
