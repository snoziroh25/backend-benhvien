package com.benhvien.Khamthai.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Entity
@Document(collection = "xa_phuong")
public class XaPhuongModel {

	@Id
	private String id;

	@Field(value = "ten")
	private String ten;

	@Field(value = "quanHuyenId")
	private String quanHuyenId;

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

	public String getQuanHuyenId() {
		return quanHuyenId;
	}

	public void setQuanHuyenId(String quanHuyenId) {
		this.quanHuyenId = quanHuyenId;
	}

}
