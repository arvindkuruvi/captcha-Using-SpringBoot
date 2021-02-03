package com.mindweaver.service.captchaservice;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CaptchaServiceImpl implements CaptchaService
{	
	private static final Logger log = LoggerFactory.getLogger(CaptchaServiceImpl.class);
	
	public static HashMap<String, CaptchaRequest> captchaRegister = new HashMap<String, CaptchaRequest>();

	public static final String FILE_TYPE = "jpeg";
	
	@Override
	public void captcha(HttpServletRequest request, HttpServletResponse response) {
		
		try
		{
			this.generateImg(request, response);
		}
		catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	private void generateImg(HttpServletRequest request, HttpServletResponse response) throws IOException 
	{
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setHeader("Progma", "no-cache");
		response.setDateHeader("Max-Age", 0);
		
		
		String captcha = generateCaptcha(5);
		
		log.info("Generated Captcha : " + captcha);
		
		int width = 130, height = 35;
		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.OPAQUE);
		Graphics graphics = bufferedImage.createGraphics();
		graphics.setFont(new Font("Arial", Font.BOLD, 20));
		graphics.setColor(new Color(0,0,0)); //color of captcha bg
		graphics.fillRect(0, 0, width, height);
		graphics.setColor(new Color(255, 255, 255)); //color of text
		graphics.drawString(captcha, 20, 25);
		
		 //using the captcharegister  to store the generated captcha
		captchaRegister.put(captcha , new CaptchaRequest(captcha));
		
		OutputStream outputStream = response.getOutputStream();
		ImageIO.write(bufferedImage, FILE_TYPE, outputStream);
		outputStream.close();
	}
	
	private String generateCaptcha(int captchaLength) {
		String captcha = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		
		StringBuffer captchaBuffer = new StringBuffer();
		Random random = new Random();
		
		while(captchaBuffer.length() < captchaLength) {
			int index = (int) (random.nextFloat() * captcha.length());
			captchaBuffer.append(captcha.substring(index, index+1));
		}
		return captchaBuffer.toString();
	}

	@Override
	public CaptchaResponse validateCaptcha(CaptchaRequest request) {
		
		CaptchaResponse response = new CaptchaResponse();
		
	    Set<Entry<String, CaptchaRequest>> entrySet = captchaRegister.entrySet();
	    
	    Iterator<Entry<String, CaptchaRequest>> itr = entrySet.iterator();
	    
	    while (itr.hasNext()) 
	    {
			Map.Entry<String, CaptchaRequest> entry = (Map.Entry<String, CaptchaRequest>) itr.next();
			
			if(entry.getKey().equals(request.getCaptcha()))
			{
				log.info("<-------------------------------------------->");
				log.info("Captcha : " + request.getCaptcha());
				log.info(entry.getKey() + " is present in Register");
				log.info("captcha validated!");
				response.setMessage("captcha validated!");
				response.setSuccess(true);
				
				itr.remove();
				log.info("<-------------------------------------------->");
				return response;
			}
			else
			{
				response.setMessage("captcha not validated!");
				response.setSuccess(false);
			}
		}
	    log.info("<-------------------------------------------->");
	    log.info("captcha not validated!");
	    log.info("<-------------------------------------------->");
		return response;
	}

}
