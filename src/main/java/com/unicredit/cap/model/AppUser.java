package com.unicredit.cap.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="APPUSER")
public class AppUser {

	
    @Id
    @GeneratedValue
    @Column(name = "ID")
	private Long id;
	
    @Column(name = "USERNAME")
	private String username;
	
    @Column(name = "PASSWORD")
	private String password;
	
    @Column(name = "FIRSTNAME")
	private String firstname;
	
    @Column(name = "LASTNAME")
	private String lastname;

    @Column(name = "ACTIVE")
   	private Integer active;
    
    public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "APPUSERROLE", joinColumns = @JoinColumn(name = "APPUSER", referencedColumnName = "ID"),
            						 inverseJoinColumns = @JoinColumn(name = "APPROLE", referencedColumnName = "ID"))
    private List<AppRola> roles;
    
    
    
	public List<AppRola> getRoles() {
		return roles;
	}

	public void setRoles(List<AppRola> roles) {
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
    
	
}
