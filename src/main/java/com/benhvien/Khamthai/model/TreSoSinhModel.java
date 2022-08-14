package com.benhvien.Khamthai.model;

import java.util.List;

import javax.persistence.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Entity
@Document(collection = "tre_so_sinh")
public class TreSoSinhModel {

	@Id
	private String id;

	@Field(value = "ten")
	private String ten;

	@Field(value = "gioiTinh")
	private String gioiTinh;

	@Field(value = "ngaySinh")
	private String ngaySinh;

	@Field(value = "diTat")
	private String diTat;

	@Field(value = "canNangSinh")
	private String canNangSinh;

	@Field(value = "sinhNon")
	private String sinhNon;

	@Field(value = "note")
	private String note;

	@Field(value = "thaiPhuId")
	private String thaiPhuId;

	@Field(value = "coSoYTeId")
	private String coSoYTeId;

	@Field(value = "vacxinId")
	private List<String> vacxinId;

	public void addVacxinId(String vacxinId) {
		this.vacxinId.add(vacxinId);
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getCoSoYTeId() {
		return coSoYTeId;
	}

	public void setCoSoYTeId(String coSoYTeId) {
		this.coSoYTeId = coSoYTeId;
	}

	public List<String> getVacxinId() {
		return vacxinId;
	}

	public void setVacxinId(List<String> vacxinId) {
		this.vacxinId = vacxinId;
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

	public String getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getDiTat() {
		return diTat;
	}

	public void setDiTat(String diTat) {
		this.diTat = diTat;
	}

	public String getCanNangSinh() {
		return canNangSinh;
	}

	public void setCanNangSinh(String canNangSinh) {
		this.canNangSinh = canNangSinh;
	}

	public String getSinhNon() {
		return sinhNon;
	}

	public void setSinhNon(String sinhNon) {
		this.sinhNon = sinhNon;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getThaiPhuId() {
		return thaiPhuId;
	}

	public void setThaiPhuId(String thaiPhuId) {
		this.thaiPhuId = thaiPhuId;
	}

}
