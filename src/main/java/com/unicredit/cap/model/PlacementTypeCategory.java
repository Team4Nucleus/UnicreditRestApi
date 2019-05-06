package com.unicredit.cap.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name="PLACEMENTTYPECATEGORY")
public class PlacementTypeCategory {

	
	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    @Column(name = "ID")
	    @JsonView(Placement.class)
		private long id;
	    
	    @Column(name = "CODE")
	    @JsonView(Placement.class)
		private String code;
	    
		@Column(name = "NAME")
	    @JsonView(Placement.class)
		private String name;
		
	    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = CascadeType.ALL)
	    private List<PlacementType> placementtypes = new ArrayList<PlacementType>();
		
	    
	    public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public List<PlacementType> getPlacementtypes() {
			return placementtypes;
		}

		public void setPlacementtypes(List<PlacementType> placementtypes) {
			this.placementtypes = placementtypes;
		}

		
}
