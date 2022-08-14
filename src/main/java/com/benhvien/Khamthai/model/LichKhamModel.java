package com.benhvien.Khamthai.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Entity
@Document(collection = "lich_kham")
public class LichKhamModel {

	@Id
	private String id;

	@Field(value = "doiTuong")
	private String doiTuong;

	@Field(value = "bacSiId")
	private String bacSiId;

	@Field(value = "caLamId")
	private String caLamId;

	@Field(value = "ngayKham")
	private String ngayKham;

	@Field(value = "thaiPhuId")
	private String thaiPhuId;

	@Field(value = "coSoYTeId")
	private String coSoYTeId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBacSiId() {
		return bacSiId;
	}

	public void setBacSiId(String bacSiId) {
		this.bacSiId = bacSiId;
	}

	public String getCaLamId() {
		return caLamId;
	}

	public void setCaLamId(String caLamId) {
		this.caLamId = caLamId;
	}

	public String getNgayKham() {
		return ngayKham;
	}

	public void setNgayKham(String ngayKham) {
		this.ngayKham = ngayKham;
	}

	public String getThaiPhuId() {
		return thaiPhuId;
	}

	public void setThaiPhuId(String thaiPhuId) {
		this.thaiPhuId = thaiPhuId;
	}

	public String getCoSoYTeId() {
		return coSoYTeId;
	}

	public void setCoSoYTeId(String coSoYTeId) {
		this.coSoYTeId = coSoYTeId;
	}

	public String getDoiTuong() {
		return doiTuong;
	}

	public void setDoiTuong(String doiTuong) {
		this.doiTuong = doiTuong;
	}

}
