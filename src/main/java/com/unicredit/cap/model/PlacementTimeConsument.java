package com.unicredit.cap.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="PLACEMENTTIMECONSUMENT")
@IdClass(TimeConsume.class)
public class PlacementTimeConsument {

	
	@Id
	@Column(name = "APPLICATION")
	private Integer application;
	
	@Id
	@Column(name = "PLACEMENT")
	private Integer placement;
	
	@Id
	@Column(name = "ORG_ID")
	private int orgID;
	
	@Column(name = "NAME")
	private String orgName;
	
	@Column(name = "UKUPNO")
	private int timeInMinutes;


	public PlacementTimeConsument(){}
	
	public int getOrgID() {
		return orgID;
	}

	public void setOrgID(int orgID) {
		this.orgID = orgID;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public int getTimeInMinutes() {
		return timeInMinutes;
	}

	public void setTimeInMinutes(int timeInMinutes) {
		this.timeInMinutes = timeInMinutes;
	}

	
	public PlacementTimeConsument(int orgID, String orgName, int timeInMinutes) {
		super();
		this.orgID = orgID;
		this.orgName = orgName;
		this.timeInMinutes = timeInMinutes;

	}

	
	
}

