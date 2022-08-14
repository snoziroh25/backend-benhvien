package com.benhvien.Khamthai.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Entity
@Document(collection = "thong_bao")
public class ThongBaoModel {

	@Id
	private String id;

	@Field(value = "content")
	private String content;

	@Field(value = "thaiPhuId")
	private String thaiPhuId;

	@Field(value = "status")
	private String status;

	@Field(value = "type")
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getThaiPhuId() {
		return thaiPhuId;
	}

	public void setThaiPhuId(String thaiPhuId) {
		this.thaiPhuId = thaiPhuId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
