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
@Table(name="COMPETENCYHOLDER")
public class CompetencyHolder {

	
	@Id
	@JsonView(ViewProfile.Application.class)
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
	private Long id;
	
    @Column(name = "NAME")
    @JsonView(ViewProfile.Application.class)
	private String name;

    
    @Column(name = "ORG")
    @JsonView(ViewProfile.Application.class)
	private String org;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}
    
    
}
