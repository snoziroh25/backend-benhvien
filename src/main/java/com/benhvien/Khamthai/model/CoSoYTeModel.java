package com.benhvien.Khamthai.model;

import javax.persistence.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Entity
@Document(collection = "co_so_y_te")
public class CoSoYTeModel {

	@Id
	private String id;

	@Field(value = "ten")
	private String ten;

	@Field(value = "diaChi")
	private String diaChi;

	@Field(value = "soYTeId")
	private String soYTeId;
	
	@Field(value = "xaPhuongId")
	private String xaPhuongId;

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

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getSoYTeId() {
		return soYTeId;
	}

	public void setSoYTeId(String soYTeId) {
		this.soYTeId = soYTeId;
	}

	public String getXaPhuongId() {
		return xaPhuongId;
	}

	public void setXaPhuongId(String xaPhuongId) {
		this.xaPhuongId = xaPhuongId;
	}

	
}
