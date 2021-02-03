package com.mindweaver.service.captchaservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CaptchaService {
	void captcha(HttpServletRequest request, HttpServletResponse response);
	
	CaptchaResponse validateCaptcha(CaptchaRequest request);
}
