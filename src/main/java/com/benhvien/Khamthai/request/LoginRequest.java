package com.benhvien.Khamthai.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class LoginRequest {
	@NotEmpty(message = "cannot be empty")
	@NotNull(message = "cannot be null")
	private String username;

	@NotEmpty(message = "cannot be empty")
	@NotNull(message = "can not be null")
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
