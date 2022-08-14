package com.benhvien.Khamthai.enums;

public enum ApiStatus {
	OK("00"), INPUT_ERROR("01"), DATA_INTEGRITY_ERROR("02"), DUPLICATE_ERROR("03"), AUTHORIZATION_ERROR("04"),
	CONVENI_CANCELED_ERROR("05"), INPUT_RELATED_ERROR("06"), DATA_DOES_NOT_EXIST("07"), DEAD_LINE_ERROR("08"),
	OTHER("99");

	private String code;

	private ApiStatus(String code) {
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}
}
