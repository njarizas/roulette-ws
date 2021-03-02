package com.nj4s.roulette.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExceptionInfo {

	@JsonProperty("status")
	private int status;
	
	@JsonProperty("error")
	private String error;

	@JsonProperty("message")
	private String message;
	

	@JsonProperty("path")
	private String path;

	public ExceptionInfo(int status, String error, String message, String path) {
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}

	public String getMessage() {
		return message;
	}

	public int getStatus() {
		return status;
	}

	public String getPath() {
		return path;
	}

}
