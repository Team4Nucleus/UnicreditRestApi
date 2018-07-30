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
@Table(name="PLACEMENTTRANSFER")
public class PlacementTransfer {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private long ID;
	
	/*
	@Column(name = "PLACEMENT")
	private long placement;
	*/
	
	@Column(name = "FROM_ORG")
	@Nullable
	private Long fromOrg;
	
	@Column(name = "FROM_USER")
	@Nullable
	private Long fromUser;
	
	@Column(name = "TO_ORG")
	private Long toOrg;
	
	@Column(name = "TO_USER")
	@Nullable
	private Long toUser;
	
	@Column(name = "DATE_FROM")
	private Date dateFrom;
	
	@Column(name = "DATE_TO")
	private Date dateTo;
	
	@Column(name = "USER_COMMENT")
	private String userComment;
	
	@Column(name = "MOVEMENT_TYPE")
	private String movementType;


    @ManyToOne
    @JsonIgnore
	@JoinColumn(name = "placement")
    private Placement placement;
    
    
    @JsonView(PlacementTransfer.class)
    @ManyToOne
	@JoinColumn(name ="FROM_ORG", insertable=false, updatable=false)
    private Organization fromOrgDetails;
    

	@JsonView(PlacementTransfer.class)
    @ManyToOne
	@JoinColumn(name ="FROM_USER", insertable=false, updatable=false)
    private User fromUserDetails;
    
    
    @JsonView(PlacementTransfer.class)
    @ManyToOne
	@JoinColumn(name ="TO_ORG", insertable=false, updatable=false)
    private Organization toOrgDetails;
    


	@JsonView(PlacementTransfer.class)
    @ManyToOne
    @Nullable
	@JoinColumn(name ="TO_USER", insertable=false, updatable=false)
    private User toUserDetails;
    


	public Placement getPlacement() {
		return placement;
	}

	public void setPlacement(Placement placement) {
		this.placement = placement;
	}

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}


	public Long getFromOrg() {
		return fromOrg;
	}

	public void setFromOrg(Long fromOrg) {
		this.fromOrg = fromOrg;
	}

	public Long getFromUser() {
		return fromUser;
	}

	public void setFromUser(Long fromUser) {
		this.fromUser = fromUser;
	}

	public Long getToOrg() {
		return toOrg;
	}

	public void setToOrg(Long toOrg) {
		this.toOrg = toOrg;
	}

	public Long getToUser() {
		return toUser;
	}

	public void setToUser(Long toUser) {
		this.toUser = toUser;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public String getUserComment() {
		return userComment;
	}

	public void setUserComment(String userComment) {
		this.userComment = userComment;
	}

	public String getMovementType() {
		return movementType;
	}

	public void setMovementType(String movementType) {
		this.movementType = movementType;
	}
	
	 
    public Organization getFromOrgDetails() {
		return fromOrgDetails;
	}

	public void setFromOrgDetails(Organization fromOrgDetails) {
		this.fromOrgDetails = fromOrgDetails;
	}
	
	public User getFromUserDetails() {
		return fromUserDetails;
	}

	public void setFromUserDetails(User fromUserDetails) {
		this.fromUserDetails = fromUserDetails;
	}
	

	public Organization getToOrgDetails() {
		return toOrgDetails;
	}

	public void setToOrgDetails(Organization toOrgDetails) {
		this.toOrgDetails = toOrgDetails;
	}
	
	public User getToUserDetails() {
		return toUserDetails;
	}

	public void setToUserDetails(User toUserDetails) {
		this.toUserDetails = toUserDetails;
	}
	
	
	
}
