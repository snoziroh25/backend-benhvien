package com.benhvien.Khamthai.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Entity
@Document(collection = "vacxin")
public class VacxinModel {

	@Id
	private String id;

	@Field(value = "ten")
	private String ten;

	@Field(value = "benhId")
	private String benhId;

	@Field(value = "doTuoiTiem")
	private int doTuoiTiem;

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

	public String getBenhId() {
		return benhId;
	}

	public void setBenhId(String benhId) {
		this.benhId = benhId;
	}

	public int getDoTuoiTiem() {
		return doTuoiTiem;
	}

	public void setDoTuoiTiem(int doTuoiTiem) {
		this.doTuoiTiem = doTuoiTiem;
	}

}
