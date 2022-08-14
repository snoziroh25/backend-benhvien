package com.benhvien.Khamthai.dto;

import java.util.List;

public class TreSoSinhDto {

	private String id;

	private String ten;

	private String gioiTinh;

	private String ngaySinh;

	private String diTat;

	private String canNangSinh;

	private String sinhNon;

	private String note;

	private String thaiPhuId;

	private String tenThaiPhu;

	private String coSoYTeId;

	private String tenCoSoYTe;

	private List<String> vacxinId;

	private String vacxin = "Chưa tiêm";

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

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
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

	public String getTenThaiPhu() {
		return tenThaiPhu;
	}

	public void setTenThaiPhu(String tenThaiPhu) {
		this.tenThaiPhu = tenThaiPhu;
	}

	public String getCoSoYTeId() {
		return coSoYTeId;
	}

	public void setCoSoYTeId(String coSoYTeId) {
		this.coSoYTeId = coSoYTeId;
	}

	public String getTenCoSoYTe() {
		return tenCoSoYTe;
	}

	public void setTenCoSoYTe(String tenCoSoYTe) {
		this.tenCoSoYTe = tenCoSoYTe;
	}

	public List<String> getVacxinId() {
		return vacxinId;
	}

	public void setVacxinId(List<String> vacxinId) {
		this.vacxinId = vacxinId;
	}

	public String getVacxin() {
		return vacxin;
	}

	public void setVacxin(String vacxin) {
		this.vacxin = vacxin;
	}

}
