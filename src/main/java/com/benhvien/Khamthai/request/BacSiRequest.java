package com.benhvien.Khamthai.request;

public class BacSiRequest {

	private String csytId;
	private int size;
	private int page;

	public String getCsytId() {
		return csytId;
	}

	public void setCsytId(String csytId) {
		this.csytId = csytId;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
}
