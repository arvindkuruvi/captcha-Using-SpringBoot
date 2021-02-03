package com.mindweaver.service.captchaservice;

public class CaptchaRequest {

	private String captcha;
	
	public CaptchaRequest(String captcha) {
		super();
		this.captcha = captcha;
	}

	public CaptchaRequest() {	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	@Override
	public String toString() {
		return "CaptchaRequest [captcha=" + captcha + "]";
	}
	
	
}
