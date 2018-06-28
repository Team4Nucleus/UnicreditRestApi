package com.unicredit.cap.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TASKDETAIL")
public class TaskDetail {

	
    @Id
    @GeneratedValue
    @Column(name = "ID")
	private long ID;
    
    @Column(name = "TASK")
	private long TASK;
	
    @Column(name = "TEXT")
	private String TEXT;
	
    @Column(name = "FROM_USER")
	private int FROM_USER;
	
    @Column(name = "FROM_ORG")
	private int FROM_ORG;
	
    @Column(name = "TO_USER")
	private int TO_USER;
	
    @Column(name = "TO_ORG")
	private int TO_ORG;
	
    @Column(name = "FROM_DATE")
	private Date FROM_DATE;
	
    @Column(name = "TO_DATE")
	private Date TO_DATE;

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public long getTASK() {
		return TASK;
	}

	public void setTASK(long tASK) {
		TASK = tASK;
	}

	public String getTEXT() {
		return TEXT;
	}

	public void setTEXT(String tEXT) {
		TEXT = tEXT;
	}

	public int getFROM_USER() {
		return FROM_USER;
	}

	public void setFROM_USER(int fROM_USER) {
		FROM_USER = fROM_USER;
	}

	public int getFROM_ORG() {
		return FROM_ORG;
	}

	public void setFROM_ORG(int fROM_ORG) {
		FROM_ORG = fROM_ORG;
	}

	public int getTO_USER() {
		return TO_USER;
	}

	public void setTO_USER(int tO_USER) {
		TO_USER = tO_USER;
	}

	public int getTO_ORG() {
		return TO_ORG;
	}

	public void setTO_ORG(int tO_ORG) {
		TO_ORG = tO_ORG;
	}

	public Date getFROM_DATE() {
		return FROM_DATE;
	}

	public void setFROM_DATE(Date fROM_DATE) {
		FROM_DATE = fROM_DATE;
	}

	public Date getTO_DATE() {
		return TO_DATE;
	}

	public void setTO_DATE(Date tO_DATE) {
		TO_DATE = tO_DATE;
	}
    
    
    
	
	
	
	
}
