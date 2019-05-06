package com.unicredit.cap.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "TRANSFERRULES")
public class TransferRules {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private long id;
	
	
	@Column(name = "FROMORG")
	private Long fromOrg;
	
	@Column(name = "TOORG")
	private Long toOrg;
	
	    @ManyToOne
		@JoinColumn(name ="FROMORG", insertable=false, updatable=false)
	    private Organization fromOrgDetails;
	 
	    @ManyToOne
		@JoinColumn(name ="TOORG", insertable=false, updatable=false)
	    private Organization toOrgDetails;

		public long getID() {
			return id;
		}

		public void setID(long iD) {
			id = iD;
		}

		public Long getFromOrg() {
			return fromOrg;
		}

		public void setFromOrg(Long fromOrg) {
			this.fromOrg = fromOrg;
		}

		public Long getToOrg() {
			return toOrg;
		}

		public void setToOrg(Long toOrg) {
			this.toOrg = toOrg;
		}

		public Organization getFromOrgDetails() {
			return fromOrgDetails;
		}

		public void setFromOrgDetails(Organization fromOrgDetails) {
			this.fromOrgDetails = fromOrgDetails;
		}

		public Organization getToOrgDetails() {
			return toOrgDetails;
		}

		public void setToOrgDetails(Organization toOrgDetails) {
			this.toOrgDetails = toOrgDetails;
		}
	    
	    
	    
	    
}
