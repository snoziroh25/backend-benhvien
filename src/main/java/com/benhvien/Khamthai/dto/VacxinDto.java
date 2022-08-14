package com.benhvien.Khamthai.dto;

public class VacxinDto {
	private String id;
	private String ten;
	private int doTuoiTiem;
	private String benhId;
	private String tenBenh;

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

	public int getDoTuoiTiem() {
		return doTuoiTiem;
	}

	public void setDoTuoiTiem(int doTuoiTiem) {
		this.doTuoiTiem = doTuoiTiem;
	}

	public String getBenhId() {
		return benhId;
	}

	public void setBenhId(String benhId) {
		this.benhId = benhId;
	}

	public String getTenBenh() {
		return tenBenh;
	}

	public void setTenBenh(String tenBenh) {
		this.tenBenh = tenBenh;
	}

}
