package com.unicredit.cap.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="DOCUMENTRECEIVE")
public class DocumentReceive {

	 	@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    @Column(name = "ID")
		private long id;
	 
	    @Column(name = "DOCUMENT") 
		private Long document;
	 	
	    @Column(name = "FROMUSER")
		private Long fromUser;
	 	
	    @Column(name = "TOUSER")
		private Long toUser;
	 	
	    @Column(name = "RECEIVEDATE")
		private Date receiveDate;
	 	
	    @Column(name = "ACTIVE")
	 	private int active;
	    
	    
	    @ManyToOne
		@JoinColumn(name ="FROMUSER", insertable=false, updatable=false)
	    private User fromUserDetails;
	    
	    
	    @ManyToOne
		@JoinColumn(name ="TOUSER", insertable=false, updatable=false)
	    private User toUserDetails;


		public long getId() {
			return id;
		}


		public void setId(long id) {
			this.id = id;
		}


		public Long getDocument() {
			return document;
		}


		public void setDocument(Long document) {
			this.document = document;
		}


		public Long getFromUser() {
			return fromUser;
		}


		public void setFromUser(Long fromUser) {
			this.fromUser = fromUser;
		}


		public Long getToUser() {
			return toUser;
		}


		public void setToUser(Long toUser) {
			this.toUser = toUser;
		}


		public Date getReceiveDate() {
			return receiveDate;
		}


		public void setReceiveDate(Date receiveDate) {
			this.receiveDate = receiveDate;
		}


		public int getActive() {
			return active;
		}


		public void setActive(int active) {
			this.active = active;
		}


		public User getFromUserDetails() {
			return fromUserDetails;
		}


		public void setFromUserDetails(User fromUserDetails) {
			this.fromUserDetails = fromUserDetails;
		}


		public User getToUserDetails() {
			return toUserDetails;
		}


		public void setToUserDetails(User toUserDetails) {
			this.toUserDetails = toUserDetails;
		}
	    
	    
	
}
