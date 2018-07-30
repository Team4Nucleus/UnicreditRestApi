package com.unicredit.cap.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "TASKDETAIL")
public class TaskDetail {

	
    @Id
    @GeneratedValue
    @Column(name = "ID")
	private long id;
    
    /*
    @Column(name = "TASK")
	private long task;
	*/
    
    @Column(name = "TEXT")
	private String text;
	
    @Column(name = "FROM_USER")
	private int fromUser;
	
    @Column(name = "FROM_ORG")
	private int fromOrg;
	
    @Column(name = "TO_USER")
    @Nullable
	private Integer toUser;
	
    @Column(name = "TO_ORG")
	private int toOrg;
	
    @Column(name = "FROM_DATE")
	private Date fromDate;
	
    @Column(name = "TO_DATE")
	private Date toDate;

   
    @ManyToOne
    @JsonIgnore
	@JoinColumn(name = "task")
    private Task task;
    
    
    @JsonView(TaskDetail.class)
    @ManyToOne
	@JoinColumn(name ="FROM_USER", insertable=false, updatable=false)
    private User fromUserDetails;
    
    
    @JsonView(TaskDetail.class)
    @ManyToOne
	@JoinColumn(name ="FROM_ORG", insertable=false, updatable=false)
    private Organization fromOrgDetails;
    

	@JsonView(TaskDetail.class)
    @ManyToOne
    @Nullable
	@JoinColumn(name ="TO_USER", insertable=false, updatable=false)
    private User toUserDetails;
    
    
    @JsonView(TaskDetail.class)
    @ManyToOne
	@JoinColumn(name ="TO_ORG", insertable=false, updatable=false)
    private Organization toOrgDetails;
    
    


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}



	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getFromUser() {
		return fromUser;
	}

	public void setFromUser(int fromUser) {
		this.fromUser = fromUser;
	}

	public int getFromOrg() {
		return fromOrg;
	}

	public void setFromOrg(int fromOrg) {
		this.fromOrg = fromOrg;
	}

	public Integer getToUser() {
		return toUser;
	}

	public void setToUser(Integer toUser) {
		this.toUser = toUser;
	}

	public int getToOrg() {
		return toOrg;
	}

	public void setToOrg(int toOrg) {
		this.toOrg = toOrg;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	
	public User getFromUserDetails() {
		return fromUserDetails;
	}

	public void setFromUserDetails(User fromUserDetails) {
		this.fromUserDetails = fromUserDetails;
	}
	

    public Organization getFromOrgDetails() {
		return fromOrgDetails;
	}

	public void setFromOrgDetails(Organization fromOrgDetails) {
		this.fromOrgDetails = fromOrgDetails;
	}
	
	 
	public User getToUserDetails() {
		return toUserDetails;
	}

	public void setToUserDetails(User toUserDetails) {
		this.toUserDetails = toUserDetails;
	}	    

	

	public Organization getToOrgDetails() {
		return toOrgDetails;
	}

	public void setToOrgDetails(Organization toOrgDetails) {
		this.toOrgDetails = toOrgDetails;
	}
    
	
	
	
	
}
