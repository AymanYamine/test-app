package com.command.model;

import java.sql.Timestamp;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Cra {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;
	@Column(name = "start_date")
	private Date start_date;
	@Column(name = "end_date")
	private Date end_date;
	@Column(name = "month")
	private int month;
	@Column(name = "working_days_count")
	private int working_days_count;
	@Column(name = "createdAt")
	private Timestamp createdAt;
	@Column(name = "updatedAt")
	private Timestamp updatedAt;
	 
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private  Document document;
	
	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getWorking_days_count() {
		return working_days_count;
	}

	public void setWorking_days_count(int working_days_count) {
		this.working_days_count = working_days_count;
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

	public Cra(long id, Date start_date, Date end_date, int month, int working_days_count, Timestamp createdAt,
			Timestamp updatedAt, Document document) {
		super();
		this.id = id;
		this.start_date = start_date;
		this.end_date = end_date;
		this.month = month;
		this.working_days_count = working_days_count;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.document = document;
	}

	public Cra() {
		super();
		// TODO Auto-generated constructor stub
	}

}
