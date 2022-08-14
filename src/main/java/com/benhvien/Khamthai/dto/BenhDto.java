package com.benhvien.Khamthai.dto;

public class BenhDto {

	private String id;
	private String ten;
	private String moTa;
	private String doiTuong;
	private boolean benhOThaiPhu;
	private boolean canTiem;

	public boolean isCanTiem() {
		return canTiem;
	}

	public boolean isBenhOThaiPhu() {
		return benhOThaiPhu;
	}

	public void setBenhOThaiPhu(boolean benhOThaiPhu) {
		this.benhOThaiPhu = benhOThaiPhu;
	}

	public void setCanTiem(boolean canTiem) {
		this.canTiem = canTiem;
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

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public String getDoiTuong() {
		return doiTuong;
	}

	public void setDoiTuong(String doiTuong) {
		this.doiTuong = doiTuong;
	}

}
