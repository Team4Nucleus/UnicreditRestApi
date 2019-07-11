package com.unicredit.cap.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;
import com.unicredit.cap.helper.ViewProfile;

@Entity
@Table(name="PLACEMENTSTATUS")
public class PlacementStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    @JsonView(ViewProfile.Placement.class)
	private long id;
    
    @Column(name = "CODE")
    @JsonView(ViewProfile.Placement.class)
	private String code;
    
    @Column(name = "NAME")
    @JsonView(ViewProfile.Placement.class)
	private String name;
    
    @Column(name = "ACTIVE")
    @JsonView(ViewProfile.Placement.class)
	private int active;

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

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}
	
    
   
	
}
