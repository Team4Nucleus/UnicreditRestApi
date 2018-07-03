package com.unicredit.cap.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="DOCUMENT")
public class Document {

    @Id
    @GeneratedValue
    @Column(name = "ID")
	private long id;
    
    
    @Column(name = "APPLICATION")
	private Integer application;
    
    @Column(name = "PLACEMENT")
	private Integer placement;
    
    @Column(name = "NAME")
	private String name;
    
    @Column(name = "ATTACH_USER")
	private int attachUser;
    
    @Column(name = "META_NAME")
	private String metaName;
    
    @Column(name = "META_USER")
	private String metaUSer;
    
    @Column(name = "META_DATE")
	private String metaDate;
    
    @Column(name = "META_SIZE")
	private String metaSize;
    
    @Column(name = "ORIGINAL")
	private int original;
    
    @Column(name = "PATH")
	private String path;
    
    @Column(name = "TYPE")
	private int type;
	
	
	@ManyToOne
	@JoinColumn(name = "type", insertable=false, updatable=false)
	private DocumentType documenttype;


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public Integer getApplication() {
		return application;
	}


	public void setApplication(Integer application) {
		this.application = application;
	}


	public Integer getPlacement() {
		return placement;
	}


	public void setPlacement(Integer placement) {
		this.placement = placement;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getAttachUser() {
		return attachUser;
	}


	public void setAttachUser(int attachUser) {
		this.attachUser = attachUser;
	}


	public String getMetaName() {
		return metaName;
	}


	public void setMetaName(String metaName) {
		this.metaName = metaName;
	}


	public String getMetaUSer() {
		return metaUSer;
	}


	public void setMetaUSer(String metaUSer) {
		this.metaUSer = metaUSer;
	}


	public String getMetaDate() {
		return metaDate;
	}


	public void setMetaDate(String metaDate) {
		this.metaDate = metaDate;
	}


	public String getMetaSize() {
		return metaSize;
	}


	public void setMetaSize(String metaSize) {
		this.metaSize = metaSize;
	}


	public int getOriginal() {
		return original;
	}


	public void setOriginal(int original) {
		this.original = original;
	}


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}


	public DocumentType getDocumenttype() {
		return documenttype;
	}


	public void setDocumenttype(DocumentType documenttype) {
		this.documenttype = documenttype;
	}
	
	
	
	
	
	
}