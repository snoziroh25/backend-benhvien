package com.benhvien.Khamthai.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Entity
@Document(collection = "benh")
public class BenhModel {

	@Id
	private String id;
	
	@Field(value = "ten")
	private String ten;
	
	@Field(value = "moTa")
	private String moTa;
	
	@Field(value = "benhOThaiPhu")
	private boolean benhOThaiPhu;
	
	@Field(value = "canTiem")
	private boolean canTiem;

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

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public boolean isBenhOThaiPhu() {
		return benhOThaiPhu;
	}

	public void setBenhOThaiPhu(boolean benhOThaiPhu) {
		this.benhOThaiPhu = benhOThaiPhu;
	}

	public boolean isCanTiem() {
		return canTiem;
	}

	public void setCanTiem(boolean canTiem) {
		this.canTiem = canTiem;
	}
	
	
}
