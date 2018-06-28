package com.unicredit.cap.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TASK")
public class Task {

    @Id
    @GeneratedValue
    @Column(name = "ID")
	private long ID;
    
    @Column(name = "PLACEMENT")
	private long IDPLACEMENT;
    
    @Column(name = "DESCRIPTION")
	private String DESCRIPTION;
    
    @Column(name = "STATUS")
	private int STATUS;
    
    @Column(name = "PRIORITY")
	private String PRIORITY;
    
    @Column(name = "CREATE_USER")
	private int CREATE_USER;
    
    @Column(name = "CREATION_DATE")
	private Date CREATION_DATE;
    
    @Column(name = "CLOSING_DATE")
	private Date CLOSING_DATE;
	
	@ManyToOne
	@JoinColumn(name = "STATUS", insertable=false, updatable=false)
	private TaskStatus TASKSTATUS;
	
	
	 @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	 @JoinColumn(name = "TASK")
	 private List<TaskDetail> TASKSDETAILS = new ArrayList<>();
	 
	/*
    @ManyToOne
	@JoinColumn(name = "PLACEMENT", insertable=false, updatable=false)
	private Placement PLACEMENT;
	*/
	
	public TaskStatus getTASKSTATUS() {
		return TASKSTATUS;
	}
	public List<TaskDetail> getTASKSDETAILS() {
		return TASKSDETAILS;
	}
	public void setTASKSDETAILS(List<TaskDetail> tASKSDETAILS) {
		TASKSDETAILS = tASKSDETAILS;
	}
	public void setTASKSTATUS(TaskStatus tASKSTATUS) {
		TASKSTATUS = tASKSTATUS;
	}

	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}
	public long getIDPLACEMENT() {
		return IDPLACEMENT;
	}
	public void setIDPLACEMENT(long iDPLACEMENT) {
		IDPLACEMENT = iDPLACEMENT;
	}
	public String getDESCRIPTION() {
		return DESCRIPTION;
	}
	public void setDESCRIPTION(String dESCRIPTION) {
		DESCRIPTION = dESCRIPTION;
	}
	public int getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(int sTATUS) {
		STATUS = sTATUS;
	}
	public String getPRIORITY() {
		return PRIORITY;
	}
	public void setPRIORITY(String pRIORITY) {
		PRIORITY = pRIORITY;
	}
	public int getCREATE_USER() {
		return CREATE_USER;
	}
	public void setCREATE_USER(int cREATE_USER) {
		CREATE_USER = cREATE_USER;
	}
	public Date getCREATION_DATE() {
		return CREATION_DATE;
	}
	public void setCREATION_DATE(Date cREATION_DATE) {
		CREATION_DATE = cREATION_DATE;
	}
	public Date getCLOSING_DATE() {
		return CLOSING_DATE;
	}
	public void setCLOSING_DATE(Date cLOSING_DATE) {
		CLOSING_DATE = cLOSING_DATE;
	}
	
	
	
}
