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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "PLACEMENT")
public class Placement {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    @JsonView(Placement.class)
	private long id;
    

	/*
	@JsonView(Placement.class)
    @Column(name = "APPLICATION")
	private int application;
*/
	
    @JsonView(Placement.class)
    @Column(name = "DECISION_NUMBER")
	private String decisionNumber;
	
    @JsonView(Placement.class)
    @Column(name = "CLIENT_CORE_NO")
    private long clientCoreNo;
    
    @JsonView(Placement.class)
    @Column(name = "CLIENT_NAME")
	private String clientName;
    
    @JsonView(Placement.class)
    @Column(name = "CLIENT_JIB")
	private String clientJib;
    
    @JsonView(Placement.class)
    @Column(name = "CLIENT_PERSONAL_DOC")
	private String clientPersonalDoc;
    
    @JsonView(Placement.class)
    @Column(name = "CLIENT_PHONE")
	private String clientPhone;
    
    @JsonView(Placement.class)
    @Column(name = "CLIENT_EMAIL")
	private String clientEmail;
    
    @JsonView(Placement.class)
    @Column(name = "TYPE")
	private int type;
    
    @JsonView(Placement.class)
    @Column(name = "REQUEST_DATE")
	private Date requestDate;
    
    @JsonView(Placement.class)
    @Column(name = "CREATING_DATE")
	private Date cretaingDate;
    
    @JsonView(Placement.class)
    @Column(name = "CLOSING_DATE")
	private Date closingDate;
    
    @JsonView(Placement.class)
    @Column(name = "CREATING_ORG")
	private int creatingOrg;
    
    @JsonView(Placement.class)
    @Column(name = "CURRENT_ORG")
	private Integer currentOrg;
    
    @JsonView(Placement.class)
    @Column(name = "CREATE_USER")
	private int createUser;
    
    @JsonView(Placement.class)
    @Column(name = "CURRENT_USER")
	private Integer currentUser;
    
    @JsonView(Placement.class)
    @Column(name = "LOAN_AMOUNT")
	private long loanAmount;
    
    @JsonView(Placement.class)
    @Column(name = "PAYMENT_PERIOD")
	private String paymentPeriod;
    
    @JsonView(Placement.class)
    @Column(name = "LOAN_USAGE")
	private String loanUsage;
    
    @JsonView(Placement.class)
    @Column(name = "TOTAL_EXPOSURE")
	private long totalExposure;
    
    @JsonView(Placement.class)
    @Column(name = "USER_COMMENT")
	private String userComment;
    
    @JsonView(Placement.class)
    @Column(name = "STATUS")
    private int status;
    
    @JsonView(Placement.class)
    @ManyToOne
	@JoinColumn(name = "status", insertable=false, updatable=false)
    private PlacementStatus placementstatus;
    
    @JsonView(Placement.class)
    @ManyToOne
   	@JoinColumn(name = "type", insertable=false, updatable=false)
    private PlacementType placementtype;
       
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "placement", fetch = FetchType.LAZY, orphanRemoval = true )
    private List<PlacementTransfer> transfers = new ArrayList<PlacementTransfer>();
       
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "placement", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Task> tasks = new ArrayList<Task>();
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "placement", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Document> documents = new ArrayList<Document>();

    @ManyToOne
    @JsonIgnore
	@JoinColumn(name = "application")
    private Application application;
    
    
	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getDecisionNumber() {
		return decisionNumber;
	}

	public void setDecisionNumber(String decisionNumber) {
		this.decisionNumber = decisionNumber;
	}

	public long getClientCoreNo() {
		return clientCoreNo;
	}

	public void setClientCoreNo(long clientCoreNo) {
		this.clientCoreNo = clientCoreNo;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientJib() {
		return clientJib;
	}

	public void setClientJib(String clientJib) {
		this.clientJib = clientJib;
	}

	public String getClientPersonalDoc() {
		return clientPersonalDoc;
	}

	public void setClientPersonalDoc(String clientPersonalDoc) {
		this.clientPersonalDoc = clientPersonalDoc;
	}

	public String getClientPhone() {
		return clientPhone;
	}

	public void setClientPhone(String clientPhone) {
		this.clientPhone = clientPhone;
	}

	public String getClientEmail() {
		return clientEmail;
	}

	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public Date getCretaingDate() {
		return cretaingDate;
	}

	public void setCretaingDate(Date cretaingDate) {
		this.cretaingDate = cretaingDate;
	}

	public Date getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}

	public int getCreatingOrg() {
		return creatingOrg;
	}

	public void setCreatingOrg(int creatingOrg) {
		this.creatingOrg = creatingOrg;
	}

	public Integer getCurrentOrg() {
		return currentOrg;
	}

	public void setCurrentOrg(Integer currentOrg) {
		this.currentOrg = currentOrg;
	}

	public int getCreateUser() {
		return createUser;
	}

	public void setCreateUser(int createUser) {
		this.createUser = createUser;
	}

	public Integer getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(Integer currentUser) {
		this.currentUser = currentUser;
	}

	public long getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(long loanAmount) {
		this.loanAmount = loanAmount;
	}

	public String getPaymentPeriod() {
		return paymentPeriod;
	}

	public void setPaymentPeriod(String paymentPeriod) {
		this.paymentPeriod = paymentPeriod;
	}

	public String getLoanUsage() {
		return loanUsage;
	}

	public void setLoanUsage(String loanUsage) {
		this.loanUsage = loanUsage;
	}

	public long getTotalExposure() {
		return totalExposure;
	}

	public void setTotalExposure(long totalExposure) {
		this.totalExposure = totalExposure;
	}

	public String getUserComment() {
		return userComment;
	}

	public void setUserComment(String userComment) {
		this.userComment = userComment;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public PlacementStatus getPlacementstatus() {
		return placementstatus;
	}

	public void setPlacementstatus(PlacementStatus placementstatus) {
		this.placementstatus = placementstatus;
	}

	public PlacementType getPlacementtype() {
		return placementtype;
	}

	public void setPlacementtype(PlacementType placementtype) {
		this.placementtype = placementtype;
	}

	public List<PlacementTransfer> getTransfers() {
		return transfers;
	}

	public void setTransfers(List<PlacementTransfer> transfers) {
		this.transfers = transfers;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}
    
    
	
	
	
}
