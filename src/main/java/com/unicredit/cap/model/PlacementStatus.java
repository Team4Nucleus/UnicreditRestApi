package com.unicredit.cap.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name="PLACEMENTSTATUS")
public class PlacementStatus {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    @JsonView(Placement.class)
	private long id;
    
    @Column(name = "CODE")
    @JsonView(Placement.class)
	private String code;
    
    @Column(name = "NAME")
    @JsonView(Placement.class)
	private String name;
    
    @Column(name = "ACTIVE")
    @JsonView(Placement.class)
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
