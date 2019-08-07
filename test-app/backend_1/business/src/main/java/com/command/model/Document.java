package com.command.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Document {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;
	@Column(name = "document_name")
	private String document_name;
	@Column(name = "document_location")
	private String document_location;
	@Column(name = "type")
	private String type;
	@Column(name = "createdAt")
	private Timestamp createdAt;
	@Column(name = "updatedAt")
	private Timestamp updatedAt;
 


	

	public Document(long id, String document_name, String document_location, String type, Timestamp createdAt,
			Timestamp updatedAt) {
		super();
		this.id = id;
		this.document_name = document_name;
		this.document_location = document_location;
		this.type = type;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		
	}

	public Document() {
		super();
		// TODO Auto-generated constructor stub
	}
//public void setCraId(long cra_id) {
//	this.cra_id=cra_id ;

//}
//public Long getCraId() {
//	return cra_id;
//}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDocument_name() {
		return document_name;
	}

	public void setDocument_name(String document_name) {
		this.document_name = document_name;
	}

	public String getDocument_location() {
		return document_location;
	}

	public void setDocument_location(String document_location) {
		this.document_location = document_location;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	
}
