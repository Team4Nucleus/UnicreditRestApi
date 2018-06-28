package com.unicredit.cap.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PLACEMENT")
public class Placement {

    @Id
    @GeneratedValue
    @Column(name = "ID")
	private long ID;
    
    @Column(name = "APPLICATION")
	private int APPLICATION;
    
    @Column(name = "DECISION_NUMBER")
	private String DECISION_NUMBER;
	
    @Column(name = "CLIENT_CORE_NO")
    private long CLIENT_CORE_NO;
    
    @Column(name = "CLIENT_NAME")
	private String CLIENT_NAME;
    
    @Column(name = "CLIENT_JIB")
	private String CLIENT_JIB;
    
    @Column(name = "CLIENT_PERSONAL_DOC")
	private String CLIENT_PERSONAL_DOC;
    
    @Column(name = "CLIENT_PHONE")
	private String CLIENT_PHONE;
    
    @Column(name = "CLIENT_EMAIL")
	private String CLIENT_EMAIL;
    
    @Column(name = "TYPE")
	private int TYPE;
    
    @Column(name = "REQUEST_DATE")
	private Date REQUEST_DATE;
    
    @Column(name = "CREATING_DATE")
	private Date CREATING_DATE;
    
    @Column(name = "CLOSING_DATE")
	private Date CLOSING_DATE;
    
    @Column(name = "CREATING_ORG")
	private int CREATING_ORG;
    
    @Column(name = "CURRENT_ORG")
	private Integer CURRENT_ORG;
    
    @Column(name = "CREATE_USER")
	private int CREATE_USER;
    
    @Column(name = "CURRENT_USER")
	private Integer CURRENT_USER;
    
    @Column(name = "LOAN_AMOUNT")
	private long LOAN_AMOUNT;
    
    @Column(name = "PAYMENT_PERIOD")
	private String PAYMENT_PERIOD;
    
    @Column(name = "LOAN_USAGE")
	private String LOAN_USAGE;
    
    @Column(name = "TOTAL_EXPOSURE")
	private long TOTAL_EXPOSURE;
    
    @Column(name = "USER_COMMENT")
	private String USER_COMMENT;
    
    @Column(name = "STATUS")
    private int STATUS;
    
    @ManyToOne
	@JoinColumn(name = "STATUS", insertable=false, updatable=false)
    private PlacementStatus PLACEMENTSTATUS;
    
    @ManyToOne
   	@JoinColumn(name = "TYPE", insertable=false, updatable=false)
    private PlacementType PLACEMENTTYPE;
    
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true )
    @JoinColumn(name = "PLACEMENT")
    private List<PlacementTransfer> TRANSFERS = new ArrayList<>();
    
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "PLACEMENT")
    private List<Task> TASKS = new ArrayList<>();
    
    
    
    
	public List<Task> getTASKS() {
		return TASKS;
	}
	public void setTASKS(List<Task> tASKS) {
		TASKS = tASKS;
	}
	public List<PlacementTransfer> getTRANSFERS() {
		return TRANSFERS;
	}
	public void setTRANSFERS(List<PlacementTransfer> tRANSFERS) {
		TRANSFERS = tRANSFERS;
	}
	public PlacementType getPLACEMENTTYPE() {
		return PLACEMENTTYPE;
	}
	public void setPLACEMENTTYPE(PlacementType pLACEMENTTYPE) {
		PLACEMENTTYPE = pLACEMENTTYPE;
	}
	public PlacementStatus getPLACEMENTSTATUS() {
		return PLACEMENTSTATUS;
	}
	public void setPLACEMENTSTATUS(PlacementStatus pLACEMENTSTATUS) {
		PLACEMENTSTATUS = pLACEMENTSTATUS;
	}
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}
	public int getAPPLICATION() {
		return APPLICATION;
	}
	public void setAPPLICATION(int aPPLICATION) {
		APPLICATION = aPPLICATION;
	}
	public String getDECISION_NUMBER() {
		return DECISION_NUMBER;
	}
	public void setDECISION_NUMBER(String dECISION_NUMBER) {
		DECISION_NUMBER = dECISION_NUMBER;
	}
	public long getCLIENT_CORE_NO() {
		return CLIENT_CORE_NO;
	}
	public void setCLIENT_CORE_NO(long cLIENT_CORE_NO) {
		CLIENT_CORE_NO = cLIENT_CORE_NO;
	}
	public String getCLIENT_NAME() {
		return CLIENT_NAME;
	}
	public void setCLIENT_NAME(String cLIENT_NAME) {
		CLIENT_NAME = cLIENT_NAME;
	}
	public String getCLIENT_JIB() {
		return CLIENT_JIB;
	}
	public void setCLIENT_JIB(String cLIENT_JIB) {
		CLIENT_JIB = cLIENT_JIB;
	}
	public String getCLIENT_PERSONAL_DOC() {
		return CLIENT_PERSONAL_DOC;
	}
	public void setCLIENT_PERSONAL_DOC(String cLIENT_PERSONAL_DOC) {
		CLIENT_PERSONAL_DOC = cLIENT_PERSONAL_DOC;
	}
	public String getCLIENT_PHONE() {
		return CLIENT_PHONE;
	}
	public void setCLIENT_PHONE(String cLIENT_PHONE) {
		CLIENT_PHONE = cLIENT_PHONE;
	}
	public String getCLIENT_EMAIL() {
		return CLIENT_EMAIL;
	}
	public void setCLIENT_EMAIL(String cLIENT_EMAIL) {
		CLIENT_EMAIL = cLIENT_EMAIL;
	}
	public int getTYPE() {
		return TYPE;
	}
	public void setTYPE(int tYPE) {
		TYPE = tYPE;
	}
	public Date getREQUEST_DATE() {
		return REQUEST_DATE;
	}
	public void setREQUEST_DATE(Date rEQUEST_DATE) {
		REQUEST_DATE = rEQUEST_DATE;
	}
	public Date getCREATING_DATE() {
		return CREATING_DATE;
	}
	public void setCREATING_DATE(Date cREATING_DATE) {
		CREATING_DATE = cREATING_DATE;
	}
	public Date getCLOSING_DATE() {
		return CLOSING_DATE;
	}
	public void setCLOSING_DATE(Date cLOSING_DATE) {
		CLOSING_DATE = cLOSING_DATE;
	}
	public int getCREATING_ORG() {
		return CREATING_ORG;
	}
	public void setCREATING_ORG(int cREATING_ORG) {
		CREATING_ORG = cREATING_ORG;
	}
	public Integer getCURRENT_ORG() {
		return CURRENT_ORG;
	}
	public void setCURRENT_ORG(Integer cURRENT_ORG) {
		CURRENT_ORG = cURRENT_ORG;
	}
	public int getCREATE_USER() {
		return CREATE_USER;
	}
	public void setCREATE_USER(int cREATE_USER) {
		CREATE_USER = cREATE_USER;
	}
	public Integer getCURRENT_USER() {
		return CURRENT_USER;
	}
	public void setCURRENT_USER(Integer cURRENT_USER) {
		CURRENT_USER = cURRENT_USER;
	}
	public long getLOAN_AMOUNT() {
		return LOAN_AMOUNT;
	}
	public void setLOAN_AMOUNT(long lOAN_AMOUNT) {
		LOAN_AMOUNT = lOAN_AMOUNT;
	}
	public String getPAYMENT_PERIOD() {
		return PAYMENT_PERIOD;
	}
	public void setPAYMENT_PERIOD(String pAYMENT_PERIOD) {
		PAYMENT_PERIOD = pAYMENT_PERIOD;
	}
	public String getLOAN_USAGE() {
		return LOAN_USAGE;
	}
	public void setLOAN_USAGE(String lOAN_USAGE) {
		LOAN_USAGE = lOAN_USAGE;
	}
	public long getTOTAL_EXPOSURE() {
		return TOTAL_EXPOSURE;
	}
	public void setTOTAL_EXPOSURE(long tOTAL_EXPOSURE) {
		TOTAL_EXPOSURE = tOTAL_EXPOSURE;
	}
	public String getUSER_COMMENT() {
		return USER_COMMENT;
	}
	public void setUSER_COMMENT(String uSER_COMMENT) {
		USER_COMMENT = uSER_COMMENT;
	}
	
	
	public int getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(int sTATUS) {
		STATUS = sTATUS;
	}
	
	
}
