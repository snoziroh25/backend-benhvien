package com.benhvien.Khamthai.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Entity
@Document(collection = "ho_so_kham")
public class HoSoKhamModel {

	@Id
	private String id;
	
	@Field(value = "soKhamId")
	private String soKhamId;

	@Field(value = "coSoYTeId")
	private String coSoYTeId;

	@Field(value = "bacSiId")
	private String bacSiId;
	
	@Field(value = "ngayKham")
	private String ngayKham;
	
	@Field(value = "tuanThai")
	private String tuanThai;
	
	@Field(value = "trongLuongMe")
	private String trongLuongMe;
	
	@Field(value = "chieuCaoMe")
	private String chieuCaoMe;
	
	@Field(value = "huyetAp")
	private String huyetAp;
	
	@Field(value = "chieuCaoTuCung")
	private String chieuCaoTuCung;
	
	@Field(value = "vongBung")
	private String vongBung;
	
	@Field(value = "khungChau")
	private String khungChau;
	
	@Field(value = "thieuMau")
	private String thieuMau;
	
	@Field(value = "proteinNieu")
	private String proteinNieu;
	
	@Field(value = "xetNghiemHIV")
	private String xetNghiemHIV;
	
	@Field(value = "xetNghiemKhac")
	private String xetNghiemKhac;
	
	@Field(value = "timThai")
	private String timThai;
	
	@Field(value = "ngoiThai")
	private String ngoiThai;

	@Field(value = "ghiChu")
	private String ghiChu;
	
	@Field(value = "trangThai")
	private String trangThai;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getNgayKham() {
		return ngayKham;
	}

	public void setNgayKham(String ngayKham) {
		this.ngayKham = ngayKham;
	}

	public String getBacSiId() {
		return bacSiId;
	}

	public void setBacSiId(String bacSiId) {
		this.bacSiId = bacSiId;
	}

	public String getSoKhamId() {
		return soKhamId;
	}

	public void setSoKhamId(String soKhamId) {
		this.soKhamId = soKhamId;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public String getCoSoYTeId() {
		return coSoYTeId;
	}

	public void setCoSoYTeId(String coSoYTeId) {
		this.coSoYTeId = coSoYTeId;
	}

	public String getTuanThai() {
		return tuanThai;
	}

	public void setTuanThai(String tuanThai) {
		this.tuanThai = tuanThai;
	}

	public String getTrongLuongMe() {
		return trongLuongMe;
	}

	public void setTrongLuongMe(String trongLuongMe) {
		this.trongLuongMe = trongLuongMe;
	}

	public String getChieuCaoMe() {
		return chieuCaoMe;
	}

	public void setChieuCaoMe(String chieuCaoMe) {
		this.chieuCaoMe = chieuCaoMe;
	}

	public String getHuyetAp() {
		return huyetAp;
	}

	public void setHuyetAp(String huyetAp) {
		this.huyetAp = huyetAp;
	}

	public String getChieuCaoTuCung() {
		return chieuCaoTuCung;
	}

	public void setChieuCaoTuCung(String chieuCaoTuCung) {
		this.chieuCaoTuCung = chieuCaoTuCung;
	}

	public String getVongBung() {
		return vongBung;
	}

	public void setVongBung(String vongBung) {
		this.vongBung = vongBung;
	}

	public String getKhungChau() {
		return khungChau;
	}

	public void setKhungChau(String khungChau) {
		this.khungChau = khungChau;
	}

	public String getThieuMau() {
		return thieuMau;
	}

	public void setThieuMau(String thieuMau) {
		this.thieuMau = thieuMau;
	}

	public String getProteinNieu() {
		return proteinNieu;
	}

	public void setProteinNieu(String proteinNieu) {
		this.proteinNieu = proteinNieu;
	}

	public String getXetNghiemHIV() {
		return xetNghiemHIV;
	}

	public void setXetNghiemHIV(String xetNghiemHIV) {
		this.xetNghiemHIV = xetNghiemHIV;
	}

	public String getXetNghiemKhac() {
		return xetNghiemKhac;
	}

	public void setXetNghiemKhac(String xetNghiemKhac) {
		this.xetNghiemKhac = xetNghiemKhac;
	}

	public String getTimThai() {
		return timThai;
	}

	public void setTimThai(String timThai) {
		this.timThai = timThai;
	}

	public String getNgoiThai() {
		return ngoiThai;
	}

	public void setNgoiThai(String ngoiThai) {
		this.ngoiThai = ngoiThai;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	
	

}
