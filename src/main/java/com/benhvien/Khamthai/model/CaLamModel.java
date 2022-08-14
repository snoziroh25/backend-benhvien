package com.benhvien.Khamthai.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Entity
@Document(collection = "ca_lam")
public class CaLamModel {

	@Id
	private String id;

	@Field(value = "ten")
	private String ten;

	@Field(value = "startTime")
	private String startTime;

	@Field(value = "endTime")
	private String endTime;

	@Field(value = "csytId")
	private String csytId;

	public String getCsytId() {
		return csytId;
	}

	public void setCsytId(String csytId) {
		this.csytId = csytId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
