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
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "TASK")
public class Task {

    @Id
    @GeneratedValue
    @Column(name = "ID")
	private long id;
    
    /*
    @Column(name = "PLACEMENT")
	private long placement;
    */
    
    @Column(name = "DESCRIPTION")
	private String description;
    
    @Column(name = "STATUS")
	private int status;
    
    @Column(name = "PRIORITY")
	private String priority;
    
    
    @Column(name = "CREATE_USER")
	private long createUser;
	
    
    @Column(name = "CREATION_DATE")
	private Date createDate;
    
    @Column(name = "CLOSING_DATE")
	private Date closingDate;
	
	@ManyToOne
	@JoinColumn(name = "status", insertable=false, updatable=false)
	private TaskStatus taskstatus;
	

	//@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "task", fetch = FetchType.LAZY)
	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "task", fetch = FetchType.LAZY)
	@OrderBy("FROM_DATE ASC")
	private List<TaskDetail> taskdetails = new ArrayList<TaskDetail>();

    @ManyToOne
    @JsonIgnore
	@JoinColumn(name = "placement")
    private Placement placement;
    
    
    @ManyToOne
	@JoinColumn(name ="CREATE_USER", insertable=false, updatable=false)
    private User createUserDetails;
	
	


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}




	public Placement getPlacement() {
		return placement;
	}


	public void setPlacement(Placement placement) {
		this.placement = placement;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public String getPriority() {
		return priority;
	}


	public void setPriority(String priority) {
		this.priority = priority;
	}


	public Long getCreateUser() {
		return createUser;
	}


	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}


	public Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	public Date getClosingDate() {
		return closingDate;
	}


	public void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}


	public TaskStatus getTaskstatus() {
		return taskstatus;
	}


	public void setTaskstatus(TaskStatus taskstatus) {
		this.taskstatus = taskstatus;
	}


	public List<TaskDetail> getTaskdetails() {
		return taskdetails;
	}


	public void setTaskdetails(List<TaskDetail> taskdetails) {
		this.taskdetails = taskdetails;
	}

	public User getCreateUserDetails() {
		return createUserDetails;
	}


	public void setCreateUserDetails(User createUserDetails) {
		this.createUserDetails = createUserDetails;
	}


	 
	
	
}
