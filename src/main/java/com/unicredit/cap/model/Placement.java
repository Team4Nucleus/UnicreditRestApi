package com.unicredit.cap.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.unicredit.cap.helper.ViewProfile;

@Entity
@Table(name = "PLACEMENT")
public class Placement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    @JsonView(ViewProfile.Placement.class)
	private long id;
    

	/*
	@JsonView(Placement.class)
    @Column(name = "APPLICATION")
	private int application;
*/
	
    @JsonView(ViewProfile.Placement.class)
    @Column(name = "DECISION_NUMBER")
	private String decisionNumber;
	
    @JsonView(ViewProfile.Placement.class)
    @Column(name = "CLIENT_CORE_NO")
    private long clientCoreNo;
    
    @JsonView(ViewProfile.Placement.class)
    @Column(name = "CLIENT_NAME")
	private String clientName;
    
    @JsonView(ViewProfile.Placement.class)
    @Column(name = "CLIENT_JIB")
	private String clientJib;
    
    @JsonView(ViewProfile.Placement.class)
    @Column(name = "CLIENT_PERSONAL_DOC")
	private String clientPersonalDoc;
    
    @JsonView(ViewProfile.Placement.class)
    @Column(name = "CLIENT_PHONE")
	private String clientPhone;
    
    @JsonView(ViewProfile.Placement.class)
    @Column(name = "CLIENT_EMAIL")
	private String clientEmail;
    
    @JsonView(ViewProfile.Placement.class)
    @Column(name = "TYPE")
	private int type;
    
    @JsonView(ViewProfile.Placement.class)
    @Column(name = "REQUEST_DATE")
	private Date requestDate;
    
    @JsonView(ViewProfile.Placement.class)
    @Column(name = "CREATING_DATE")
	private Date cretaingDate;
    
    @JsonView(ViewProfile.Placement.class)
    @Column(name = "CLOSING_DATE")
	private Date closingDate;
    
    @JsonView(ViewProfile.Placement.class)
    @Column(name = "CREATING_ORG")
	private Long creatingOrg;
    
    @JsonView(ViewProfile.Placement.class)
    @Column(name = "CURRENT_ORG")
	private Long currentOrg;
    
    
    @JsonView(ViewProfile.Placement.class)
    @Column(name = "CREATE_USER")
	private Long createUser;
	
    
    @JsonView(ViewProfile.Placement.class)
    @Column(name = "CURRENT_USER")
	private Long currentUser;
    
    @JsonView(ViewProfile.Placement.class)
    @Column(name = "LOAN_AMOUNT")
	private Long loanAmount;
    
    @JsonView(ViewProfile.Placement.class)
    @Column(name = "PAYMENT_PERIOD")
	private String paymentPeriod;
    
    @JsonView(ViewProfile.Placement.class)
    @Column(name = "LOAN_USAGE")
	private String loanUsage;
    
    @JsonView(ViewProfile.Placement.class)
    @Column(name = "TOTAL_EXPOSURE")
	private Long totalExposure;
    
    @JsonView(ViewProfile.Placement.class)
    @Column(name = "USER_COMMENT")
	private String userComment;
    
    @JsonView(ViewProfile.Placement.class)
    @Column(name = "STATUS")
    private int status;
    
    @JsonView(ViewProfile.Placement.class)
    @Column(name = "CURRENCY")
    private String currency;
    
    @JsonView(ViewProfile.Placement.class)
    @Column(name = "AMOUNT_IN_BAM", columnDefinition = "NUMBER(30,5)")
    private Double amountInBam;
    
    @JsonView(ViewProfile.Placement.class)
    @Column(name = "SOCIALRISK")
    private String socialRisk;
    

	@JsonView(ViewProfile.Placement.class)
    @ManyToOne
	@JoinColumn(name = "status", insertable=false, updatable=false)
    private PlacementStatus placementstatus;
    
    @JsonView(ViewProfile.Placement.class)
    @ManyToOne
   	@JoinColumn(name = "type", insertable=false, updatable=false)
    private PlacementType placementtype;
       
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "placement", fetch = FetchType.LAZY, orphanRemoval = true )
    @OrderBy("DATE_TO DESC")
    private List<PlacementTransfer> transfers = new ArrayList<PlacementTransfer>();
       
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "placement", fetch = FetchType.LAZY, orphanRemoval = true)
    @OrderBy("CREATION_DATE DESC")
    private List<Task> tasks = new ArrayList<Task>();
    
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "placement", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Document> documents = new ArrayList<Document>();

    @ManyToOne
    @JsonIgnore
	@JoinColumn(name = "application")
    private Application application;
    
    
    @JsonView(ViewProfile.Placement.class)
    @ManyToOne
	@JoinColumn(name ="CREATING_ORG", insertable=false, updatable=false)
    private Organization creatingOrgDetails;
    
    @JsonView(ViewProfile.Placement.class)
    @ManyToOne
	@JoinColumn(name ="CURRENT_ORG", insertable=false, updatable=false)
    private Organization currentOrgDetails;
    


	@JsonView(ViewProfile.Placement.class)
    @ManyToOne
	@JoinColumn(name ="CREATE_USER", insertable=false, updatable=false)
    private User createUserDetails;
    
    @JsonView(ViewProfile.Placement.class)
    @ManyToOne
	@JoinColumn(name ="CURRENT_USER", insertable=false, updatable=false)
    private User currentUserDetails;
    
    
    public String getCurrency() {
  		return currency;
  	}

  	public void setCurrency(String currency) {
  		this.currency = currency;
  	}

  	public Double getAmountInBam() {
  		return amountInBam;
  	}

  	public void setAmountInBam(Double amountInBam) {
  		this.amountInBam = amountInBam;
  	}

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

	public Long getCreatingOrg() {
		return creatingOrg;
	}

	public void setCreatingOrg(Long creatingOrg) {
		this.creatingOrg = creatingOrg;
	}

	public Long getCurrentOrg() {
		return currentOrg;
	}

	public void setCurrentOrg(Long currentOrg) {
		this.currentOrg = currentOrg;
	}

	public Long getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}

	public Long getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(Long currentUser) {
		this.currentUser = currentUser;
	}

	public Long getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(Long loanAmount) {
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

	public Long getTotalExposure() {
		return totalExposure;
	}

	public void setTotalExposure(Long totalExposure) {
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
	

    public Organization getCreatingOrgDetails() {
		return creatingOrgDetails;
	}

	public void setCreatingOrgDetails(Organization creatingOrgDetails) {
		this.creatingOrgDetails = creatingOrgDetails;
	}
	
	public Organization getCurrentOrgDetails() {
		return currentOrgDetails;
	}

	public void setCurrentOrgDetails(Organization currentOrgDetails) {
		this.currentOrgDetails = currentOrgDetails;
	}
    
	public User getCreateUserDetails() {
		return createUserDetails;
	}

	public void setCreateUserDetails(User createUserDetails) {
		this.createUserDetails = createUserDetails;
	}
	
	
	public User getCurrentUserDetails() {
		return currentUserDetails;
	}

	public void setCurrentUserDetails(User currentUserDetails) {
		this.currentUserDetails = currentUserDetails;
	}

	public String getSocialRisk() {
		return socialRisk;
	}

	public void setSocialRisk(String socialRisk) {
		this.socialRisk = socialRisk;
	}


	
	
	
	
}
