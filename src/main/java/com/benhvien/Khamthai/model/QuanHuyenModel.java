package com.benhvien.Khamthai.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Entity
@Document(collection = "quan_huyen")
public class QuanHuyenModel {

	@Id
	private String id;
	
	@Field(value = "ten")
	private String ten;
	
	@Field(value = "tinhThanhId")
	private String tinhThanhId;

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

	public String getTinhThanhId() {
		return tinhThanhId;
	}

	public void setTinhThanhId(String tinhThanhId) {
		this.tinhThanhId = tinhThanhId;
	}
	
	
}
