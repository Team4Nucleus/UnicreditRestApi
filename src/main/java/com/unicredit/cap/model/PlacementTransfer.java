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
	private Integer fromOrg;
	
	@Column(name = "FROM_USER")
	@Nullable
	private Integer fromUser;
	
	@Column(name = "TO_ORG")
	private Integer toOrg;
	
	@Column(name = "TO_USER")
	@Nullable
	private Integer toUser;
	
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


	public Integer getFromOrg() {
		return fromOrg;
	}

	public void setFromOrg(Integer fromOrg) {
		this.fromOrg = fromOrg;
	}

	public Integer getFromUser() {
		return fromUser;
	}

	public void setFromUser(Integer fromUser) {
		this.fromUser = fromUser;
	}

	public Integer getToOrg() {
		return toOrg;
	}

	public void setToOrg(Integer toOrg) {
		this.toOrg = toOrg;
	}

	public Integer getToUser() {
		return toUser;
	}

	public void setToUser(Integer toUser) {
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
	
	
	
	
}
