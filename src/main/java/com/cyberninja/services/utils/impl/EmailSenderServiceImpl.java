package com.cyberninja.services.utils.impl;

import static com.cyberninja.common.ApplicationConstans.FRONT_DOMAIN;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.cyberninja.services.utils.EmailSenderServiceI;

@Service
public class EmailSenderServiceImpl implements EmailSenderServiceI {

	@Autowired
	private JavaMailSender javaMailSender;

	/**
	 * Envia el email
	 */
	@Override
	@Async
	public void sendEmail(String email, String token) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();

		mailMessage.setTo(email);
		mailMessage.setSubject("Complete Registration!");
		mailMessage.setFrom("CyberNinja");
		mailMessage.setText("To confirm your account, please click here: " +
				"http://" + FRONT_DOMAIN + "/account/confirm-account/" + token);

		javaMailSender.send(mailMessage);
	}
}
