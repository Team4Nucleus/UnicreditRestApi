package com.unicredit.cap.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name="APPLICATION")
public class Application  {

	@JsonView(Application.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
	private long id;
    
	@JsonView(Application.class)
    @Column(name = "CODE")
	private String code;
    
	
	@JsonView(Application.class)
    @Column(name = "CREATE_USER")
	private long createUser;
		
    
	@JsonView(Application.class)
    @Column(name = "APPLICATION_DATE")
	private Date applicationDate;
    
	@JsonView(Application.class)
    @Column(name = "CREATE_DATE")
	private Date createDate;
    
	@JsonView(Application.class)
    @Column(name = "DESCRIPTION")
	private String description;
	
	
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "application", cascade = CascadeType.ALL)
    private List<Placement> placements = new ArrayList<Placement>();
    

	@JsonView(Application.class)
    @ManyToOne
    @JoinColumn(name = "CREATE_USER", insertable=false, updatable=false)
	private User createUserDetails;
  
    
    
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


	public Long getCreateUser() {
		return createUser;
	}


	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}


	public Date getApplicationDate() {
		return applicationDate;
	}


	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}


	public Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public List<Placement> getPlacements() {
		return placements;
	}


	public void setPlacements(List<Placement> placements) {
		this.placements = placements;
	}
	

	public User getCreateUserDetails() {
		return createUserDetails;
	}


	public void setCreateUserDetails(User createUserDetails) {
		this.createUserDetails = createUserDetails;
	}
	

	
	
	
	
}
