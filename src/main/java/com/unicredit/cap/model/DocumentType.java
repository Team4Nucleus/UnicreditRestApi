package com.unicredit.cap.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="DOCUMENTTYPE")
public class DocumentType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
	private long id;
    
    @Column(name = "CODE")
	private String code;
    
    @Column(name = "NAME")
	private String name;
    
    @Column(name = "ACTIVE")
	private int active;
    
    @Column(name = "ORG", nullable = true)
    private Integer org;

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

	public Integer getOrg() {
		return org;
	}

	public void setOrg(Integer org) {
		this.org = org;
	}
	
    
   

	
}
