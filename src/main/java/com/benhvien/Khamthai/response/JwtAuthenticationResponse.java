package com.benhvien.Khamthai.response;

public class JwtAuthenticationResponse {
	private String accessToken;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public JwtAuthenticationResponse(String accessToken) {
		this.accessToken = accessToken;
	}
};
