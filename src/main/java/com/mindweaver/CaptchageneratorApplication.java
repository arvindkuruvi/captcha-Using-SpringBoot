package com.mindweaver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class CaptchageneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CaptchageneratorApplication.class, args);
	}

}
