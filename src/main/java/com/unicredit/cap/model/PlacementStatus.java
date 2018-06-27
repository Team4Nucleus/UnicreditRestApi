package com.unicredit.cap.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PLACEMENTSTATUS")
public class PlacementStatus {

    @Id
    @GeneratedValue
    @Column(name = "ID")
	private long ID;
    
    @Column(name = "CODE")
	private String CODE;
    
    @Column(name = "NAME")
	private String NAME;
    
    @Column(name = "ACTIVE")
	private int ACTIVE;
	
    
    public PlacementStatus () {}
    
    
	public String getCODE() {
		return CODE;
	}
	public void setCODE(String cODE) {
		CODE = cODE;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public int getACTIVE() {
		return ACTIVE;
	}
	public void setACTIVE(int aCTIVE) {
		ACTIVE = aCTIVE;
	}
	public long getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}

	
}
