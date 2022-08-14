package com.benhvien.Khamthai.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Entity
@Document(collection = "so_kham")
public class SoKhamModel {

	@Id
	private String id;

	@Field(value = "thaiPhuId")
	private String thaiPhuId;

	@CreatedDate
	@Field(value = "ngayTao")
	private String ngayTao;

	@Field(value = "ngayKinhCuoi")
	private String ngayKinhCuoi;

	@Field(value = "ngaySinhDuKien")
	private String ngaySinhDuKien;

	@Field(value = "lanCoThaiThu")
	private String lanCoThaiThu;

	@Field(value = "trangThai")
	private boolean trangThai;

	@Field(value = "vacxinId")
	private List<String> vacxinId;

	@Field(value = "capNhat")
	private boolean capNhat;

	public void addVacxinId(String vacxinId) {
		this.vacxinId.add(vacxinId);
	}
	
	public List<String> getVacxinId() {
		return vacxinId;
	}

	public void setVacxinId(List<String> vacxinId) {
		this.vacxinId = vacxinId;
	}

	public boolean isCapNhat() {
		return capNhat;
	}

	public void setCapNhat(boolean capNhat) {
		this.capNhat = capNhat;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNgayTao() {
		return ngayTao;
	}

	public void setNgayTao(String ngayTao) {
		this.ngayTao = ngayTao;
	}

	public String getNgayKinhCuoi() {
		return ngayKinhCuoi;
	}

	public void setNgayKinhCuoi(String ngayKinhCuoi) {
		this.ngayKinhCuoi = ngayKinhCuoi;
	}

	public String getNgaySinhDuKien() {
		return ngaySinhDuKien;
	}

	public void setNgaySinhDuKien(String ngaySinhDuKien) {
		this.ngaySinhDuKien = ngaySinhDuKien;
	}

	public String getLanCoThaiThu() {
		return lanCoThaiThu;
	}

	public void setLanCoThaiThu(String lanCoThaiThu) {
		this.lanCoThaiThu = lanCoThaiThu;
	}

	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

	public String getThaiPhuId() {
		return thaiPhuId;
	}

	public void setThaiPhuId(String thaiPhuId) {
		this.thaiPhuId = thaiPhuId;
	}

}
