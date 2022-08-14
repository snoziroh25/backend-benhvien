package com.benhvien.Khamthai.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Entity
@Document(collection = "bac_si")
public class BacSiModel {

	@Id
	private String id;

	@Field(value = "ten")
	private String ten;

	@Field(value = "sdt")
	private String sdt;

	@Field(value = "coSoYTeId")
	private String coSoYTeId;

	public String getCoSoYTeId() {
		return coSoYTeId;
	}

	public void setCoSoYTeId(String coSoYTeId) {
		this.coSoYTeId = coSoYTeId;
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

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

}
