package com.benhvien.Khamthai.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Document(collection = "tinh_thanh")
public class TinhThanhModel {

	@Id
	private String id;
	
	@Field
	private String ten;

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

	public TinhThanhModel(String ten) {
		super();
		this.ten = ten;
	}

	public TinhThanhModel() {
		super();
	}
	
	
}
