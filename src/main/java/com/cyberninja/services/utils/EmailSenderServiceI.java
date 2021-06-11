package com.cyberninja.services.utils;

import javax.mail.MessagingException;

public interface EmailSenderServiceI {

	void sendEmail(String email, String token) throws MessagingException;

}
