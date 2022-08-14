package com.benhvien.Khamthai.exception;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ApiError {
	/** The status. */
	protected Integer status;

	/** The error. */
	protected String error;

	/** The message. */
	protected String message;

	/**
	 * Instantiates a new api error.
	 */
	public ApiError() {
		super();
	}

	/**
	 * Instantiates a new api error.
	 *
	 * @param status  the status
	 * @param error   the error
	 * @param message the message
	 */
	public ApiError(Integer status, String error, String message) {
		super();
		this.status = status;
		this.error = error;
		this.message = message;
	}

	/**
	 * Get status.
	 *
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * Set status.
	 *
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * Get error.
	 *
	 * @return the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * Set error.
	 *
	 * @param error the error to set
	 */
	public void setError(String error) {
		this.error = error;
	}

	/**
	 * Get message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Set message.
	 *
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof ApiError)) {
			return false;
		}
		ApiError castOther = (ApiError) other;
		return new EqualsBuilder().append(status, castOther.status).append(error, castOther.error)
				.append(message, castOther.message).isEquals();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(status).append(error).append(message).toHashCode();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).appendSuper(super.toString())
				.append("status", status).append("error", error).append("message", message).toString();
	}

}
