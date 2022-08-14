package com.benhvien.Khamthai.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Entity
@Document(collection = "dang_ky_tiem")
public class DangKyTiemModel {

	@Id
	private String id;

	@Field(value = "target")
	private String target;

	@Field(value = "targetId")
	private String targetId;

	@Field(value = "csytId")
	private String csytId;

	@Field(value = "ngayTiem")
	private String ngayTiem;

	@Field(value = "thaiPhuId")
	private String thaiPhuId;

	@Field(value = "benhId")
	private String benhId;

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCsytId() {
		return csytId;
	}

	public void setCsytId(String csytId) {
		this.csytId = csytId;
	}

	public String getNgayTiem() {
		return ngayTiem;
	}

	public void setNgayTiem(String ngayTiem) {
		this.ngayTiem = ngayTiem;
	}

	public String getBenhId() {
		return benhId;
	}

	public void setBenhId(String benhId) {
		this.benhId = benhId;
	}

	public String getThaiPhuId() {
		return thaiPhuId;
	}

	public void setThaiPhuId(String thaiPhuId) {
		this.thaiPhuId = thaiPhuId;
	}

}
