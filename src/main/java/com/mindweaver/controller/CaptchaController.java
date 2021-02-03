package com.mindweaver.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindweaver.service.captchaservice.CaptchaRequest;
import com.mindweaver.service.captchaservice.CaptchaResponse;
import com.mindweaver.service.captchaservice.CaptchaService;

@RestController
@RequestMapping("/captcha")
@CrossOrigin(origins = "*")
public class CaptchaController 
{
	@Autowired
	CaptchaService captchaService;
	
	private static final Logger log = LoggerFactory.getLogger(CaptchaController.class);
	
	@GetMapping("/captcha")
	public void getCaptcha(HttpServletRequest request, HttpServletResponse response) {
		
		log.info("<----- Get Captcha Request -------->");
		captchaService.captcha(request, response);
	}
	
	
	@PostMapping("/check-captcha")
	public ResponseEntity<CaptchaResponse> validateCaptcha(@Valid @RequestBody CaptchaRequest request) {
		
		log.info("<------------- validate captcha request " + request +" ------------->");
		CaptchaResponse response = captchaService.validateCaptcha(request);
		
		log.info("<------------- validate captcha response " + response +" ------------->");
		return ResponseEntity.ok().body(response);
	}
}
