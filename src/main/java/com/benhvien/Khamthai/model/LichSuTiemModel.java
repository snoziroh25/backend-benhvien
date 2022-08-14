package com.benhvien.Khamthai.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Entity
@Document(collection = "lich_su_tiem")
public class LichSuTiemModel {

	@Id
	private String id;

	@Field(value = "target")
	private String target;

	@Field(value = "targetId")
	private String targetId;

	@Field(value = "coSoYTeId")
	private String coSoYTeId;

	@Field(value = "bacSiId")
	private String bacSiId;

	@Field(value = "vacxinId")
	private String vacxinId;

	@Field(value = "ngayTiem")
	private String ngayTiem;

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCoSoYTeId() {
		return coSoYTeId;
	}

	public void setCoSoYTeId(String coSoYTeId) {
		this.coSoYTeId = coSoYTeId;
	}

	public String getBacSiId() {
		return bacSiId;
	}

	public void setBacSiId(String bacSiId) {
		this.bacSiId = bacSiId;
	}

	public String getVacxinId() {
		return vacxinId;
	}

	public void setVacxinId(String vacxinId) {
		this.vacxinId = vacxinId;
	}

	public String getNgayTiem() {
		return ngayTiem;
	}

	public void setNgayTiem(String ngayTiem) {
		this.ngayTiem = ngayTiem;
	}

}
