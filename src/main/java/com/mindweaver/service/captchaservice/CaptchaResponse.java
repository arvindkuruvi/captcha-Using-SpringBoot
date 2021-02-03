package com.mindweaver.service.captchaservice;

public class CaptchaResponse {

	private boolean success = true;
	
	private String message ;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public CaptchaResponse(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}

	public CaptchaResponse() {	}

	@Override
	public String toString() {
		return "CaptchaResponse [success=" + success + ", message=" + message + "]";
	}
	
	
}
