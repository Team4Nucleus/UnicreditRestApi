package com.unicredit.cap.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="PLACEMENTTRANSFER")
public class PlacementTransfer {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private long ID;
	
	
	@Column(name = "PLACEMENT")
	private long IDPLACEMENT;
	
	@Column(name = "FROM_ORG")
	private int FROM_ORG;
	
	@Column(name = "FROM_USER")
	private int FROM_USER;
	
	@Column(name = "TO_ORG")
	private int TO_ORG;
	
	@Column(name = "TO_USER")
	private int TO_USER;
	
	@Column(name = "DATE_FROM")
	private Date DATE_FROM;
	
	@Column(name = "DATE_TO")
	private Date DATE_TO;
	
	@Column(name = "USER_COMMENT")
	private String USER_COMMENT;
	
	@Column(name = "MOVEMENT_TYPE")
	private String MOVEMENT_TYPE;
	
	/*
	 @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "PLACEMENT", insertable=false, updatable = false)
	 private Placement PLACEMENT;
	*/
	
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}
	public long getPLACEMENT() {
		return IDPLACEMENT;
	}
	public void setPLACEMENT(long pLACEMENT) {
		IDPLACEMENT = pLACEMENT;
	}
	public int getFROM_ORG() {
		return FROM_ORG;
	}
	public void setFROM_ORG(int fROM_ORG) {
		FROM_ORG = fROM_ORG;
	}
	public int getFROM_USER() {
		return FROM_USER;
	}
	public void setFROM_USER(int fROM_USER) {
		FROM_USER = fROM_USER;
	}
	public int getTO_ORG() {
		return TO_ORG;
	}
	public void setTO_ORG(int tO_ORG) {
		TO_ORG = tO_ORG;
	}
	public int getTO_USER() {
		return TO_USER;
	}
	public void setTO_USER(int tO_USER) {
		TO_USER = tO_USER;
	}
	public Date getDATE_FROM() {
		return DATE_FROM;
	}
	public void setDATE_FROM(Date dATE_FROM) {
		DATE_FROM = dATE_FROM;
	}
	public Date getDATE_TO() {
		return DATE_TO;
	}
	public void setDATE_TO(Date dATE_TO) {
		DATE_TO = dATE_TO;
	}
	public String getUSER_COMMENT() {
		return USER_COMMENT;
	}
	public void setUSER_COMMENT(String uSER_COMMENT) {
		USER_COMMENT = uSER_COMMENT;
	}
	public String getMOVEMENT_TYPE() {
		return MOVEMENT_TYPE;
	}
	public void setMOVEMENT_TYPE(String mOVEMENT_TYPE) {
		MOVEMENT_TYPE = mOVEMENT_TYPE;
	}
	
	
	
}
