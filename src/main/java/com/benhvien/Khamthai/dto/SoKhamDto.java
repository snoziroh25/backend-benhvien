package com.benhvien.Khamthai.dto;

import java.util.List;

public class SoKhamDto {

	private String id;

	private String thaiPhuId;

	private String ngayKinhCuoi;

	private String ngaySinhDuKien;

	private String lanCoThaiThu;

	private String ngayTao;

	private boolean trangThai;

	private boolean capNhat;

	private String tenThaiPhu;

	private String status;

	private String dongMo;

	private List<String> vacxinId;

	private String vacxin = "Chưa tiêm";

	public String getVacxin() {
		return vacxin;
	}

	public void setVacxin(String vacxin) {
		this.vacxin = vacxin;
	}

	public List<String> getVacxinId() {
		return vacxinId;
	}

	public void setVacxinId(List<String> vacxinId) {
		this.vacxinId = vacxinId;
	}

	public String getDongMo() {
		return dongMo;
	}

	public void setDongMo(String dongMo) {
		this.dongMo = dongMo;
	}

	public String getId() {
		return id;
	}

	public String getNgayTao() {
		return ngayTao;
	}

	public void setNgayTao(String ngayTao) {
		this.ngayTao = ngayTao;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getThaiPhuId() {
		return thaiPhuId;
	}

	public void setThaiPhuId(String thaiPhuId) {
		this.thaiPhuId = thaiPhuId;
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

	public boolean isCapNhat() {
		return capNhat;
	}

	public void setCapNhat(boolean capNhat) {
		this.capNhat = capNhat;
	}

	public String getTenThaiPhu() {
		return tenThaiPhu;
	}

	public void setTenThaiPhu(String tenThaiPhu) {
		this.tenThaiPhu = tenThaiPhu;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
