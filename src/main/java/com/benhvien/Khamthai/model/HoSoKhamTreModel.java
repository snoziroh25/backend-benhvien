package com.benhvien.Khamthai.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Entity
@Document(collection = "ho_so_kham_tre")
public class HoSoKhamTreModel {

	@Id
	private String id;

	@Field(value = "treSoSinhId")
	private String treSoSinhId;

	@Field(value = "coSoYTeId")
	private String coSoYTeId;

	@Field(value = "ngayKham")
	private String ngayKham;

	@Field(value = "canNang")
	private String canNang;

	@Field(value = "chieuCao")
	private String chieuCao;

	@Field(value = "bacSiId")
	private String bacSiId;

	@Field(value = "trangThai")
	private String trangThai;

	@Field(value = "ghiChu")
	private String ghiChu;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTreSoSinhId() {
		return treSoSinhId;
	}

	public void setTreSoSinhId(String treSoSinhId) {
		this.treSoSinhId = treSoSinhId;
	}

	public String getNgayKham() {
		return ngayKham;
	}

	public void setNgayKham(String ngayKham) {
		this.ngayKham = ngayKham;
	}

	public String getCanNang() {
		return canNang;
	}

	public void setCanNang(String canNang) {
		this.canNang = canNang;
	}

	public String getChieuCao() {
		return chieuCao;
	}

	public void setChieuCao(String chieuCao) {
		this.chieuCao = chieuCao;
	}

	public String getBacSiId() {
		return bacSiId;
	}

	public void setBacSiId(String bacSiId) {
		this.bacSiId = bacSiId;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	public String getCoSoYTeId() {
		return coSoYTeId;
	}

	public void setCoSoYTeId(String coSoYTeId) {
		this.coSoYTeId = coSoYTeId;
	}

}
