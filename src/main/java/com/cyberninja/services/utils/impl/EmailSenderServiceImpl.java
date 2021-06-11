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
		mailMessage.setFrom("Cyber Ninja <cyberninjaes@gmail.com>");
		mailMessage.setText(
				 "<div style='background-color: #f4f4f4; margin: 0 !important; padding: 0 !important;'> "
				+ "    <!-- HIDDEN PREHEADER TEXT --> "
				+ "    <div style='display: none; font-size: 1px; color: #fefefe; line-height: 1px; font-family: Lato, Helvetica, Arial, sans-serif; max-height: 0px; max-width: 0px; opacity: 0; overflow: hidden;'> We're thrilled to have you here! Get ready to dive into your new account. </div> "
				+ "    <table border='0' cellpadding='0' cellspacing='0' width='100%'> "
				+ "        <!-- LOGO --> "
				+ "        <tr> "
				+ "            <td bgcolor='#f4f4f4' align='center'> "
				+ "                <table border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width: 600px;'> "
				+ "                    <tr> "
				+ "                        <td align='center' valign='top' style='padding: 40px 10px 40px 10px;'> </td> "
				+ "                    </tr> "
				+ "                </table> "
				+ "            </td> "
				+ "        </tr> "
				+ "        <tr> "
				+ "            <td bgcolor='#f4f4f4' align='center' style='padding: 0px 10px 0px 10px;'> "
				+ "                <table border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width: 600px;'> "
				+ "                    <tr> "
				+ "                        <td bgcolor='#ffffff' align='center' valign='top' style='padding: 40px 20px 20px 20px; border-radius: 4px 4px 0px 0px; color: #111111; font-family: Lato, Helvetica, Arial, sans-serif; font-size: 48px; font-weight: 400; letter-spacing: 4px; line-height: 48px;'> "
				+ "                            <h1 style='font-size: 48px; font-weight: 400; margin: 2;'>Welcome!</h1> <img src=' https://img.icons8.com/clouds/100/000000/handshake.png' width='125' height='120' style='display: block; border: 0px;' /> "
				+ "                        </td> "
				+ "                    </tr> "
				+ "                </table> "
				+ "            </td> "
				+ "        </tr> "
				+ "        <tr> "
				+ "            <td bgcolor='#f4f4f4' align='center' style='padding: 0px 10px 0px 10px;'> "
				+ "                <table border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width: 600px;'> "
				+ "                    <tr> "
				+ "                        <td bgcolor='#ffffff' align='left' style='padding: 20px 30px 40px 30px; color: #666666; font-family: Lato, Helvetica, Arial, sans-serif; font-size: 18px; font-weight: 400; line-height: 25px;'> "
				+ "                            <p style='margin: 0;'>We're excited to have you get started. First, you need to confirm your account. Just press the button below.</p> "
				+ "                        </td> "
				+ "                    </tr> "
				+ "                    <tr> "
				+ "                        <td bgcolor='#ffffff' align='left'> "
				+ "                            <table width='100%' border='0' cellspacing='0' cellpadding='0'> "
				+ "                                <tr> "
				+ "                                    <td bgcolor='#ffffff' align='center' style='padding: 20px 30px 60px 30px;'> "
				+ "                                        <table border='0' cellspacing='0' cellpadding='0'> "
				+ "                                            <tr> "
				+ "                                                <td align='center' style='border-radius: 3px;' bgcolor='#f60'><a href='#' target='_blank' style='font-size: 20px; font-family: Helvetica, Arial, sans-serif; color: #ffffff; text-decoration: none; color: #ffffff; text-decoration: none; padding: 15px 25px; border-radius: 2px; border: 1px solid #f60; display: inline-block;'>Confirm Account</a></td> "
				+ "                                            </tr> "
				+ "                                        </table> "
				+ "                                    </td> "
				+ "                                </tr> "
				+ "                            </table> "
				+ "                        </td> "
				+ "                    </tr> <!-- COPY --> "
				+ "                    <tr> "
				+ "                        <td bgcolor='#ffffff' align='left' style='padding: 0px 30px 0px 30px; color: #666666; font-family: Lato, Helvetica, Arial, sans-serif; font-size: 18px; font-weight: 400; line-height: 25px;'> "
				+ "                            <p style='margin: 0;'>If that doesn't work, copy and paste the following link in your browser</p> "
				+ "                        </td> "
				+ "                    </tr> <!-- COPY --> "
				+ "                    <tr> "
				+ "                        <td bgcolor='#ffffff' align='left' style='padding: 20px 30px 20px 30px; color: #666666; font-family: Lato, Helvetica, Arial, sans-serif; font-size: 18px; font-weight: 400; line-height: 25px;'> "
				+ "                            <p style='margin: 0;'><a href='http://" + FRONT_DOMAIN + "/account/confirm-account/" + token + "' target='_blank' style='color: #f60;'>http://" + FRONT_DOMAIN + "/account/confirm-account/" + token + "</a></p> "
				+ "                        </td> "
				+ "                    </tr> "
				+ "                    <tr> "
				+ "                        <td bgcolor='#ffffff' align='left' style='padding: 0px 30px 40px 30px; border-radius: 0px 0px 4px 4px; color: #666666; font-family: Lato, Helvetica, Arial, sans-serif; font-size: 18px; font-weight: 400; line-height: 25px;'> "
				+ "                            <p style='margin: 0;'>Cheers,<br>CyberNinja Team</p> "
				+ "                        </td> "
				+ "                    </tr> "
				+ "                </table> "
				+ "            </td> "
				+ "        </tr> "
				+ "        <tr> "
				+ "            <td bgcolor='#f4f4f4' align='center' style='padding: 30px 10px 0px 10px;'> "
				+ "                <table border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width: 600px;'> "
				+ "                    <tr> "
				+ "                        <td bgcolor='#f4f4f4' align='center' style='padding: 30px 30px 30px 30px; border-radius: 4px 4px 4px 4px; color: #666666; font-family: Lato, Helvetica, Arial, sans-serif; font-size: 18px; font-weight: 400; line-height: 25px;'> "
				+ "                        </td> "
				+ "                    </tr> "
				+ "                </table> "
				+ "            </td> "
				+ "        </tr> "
				+ "        <tr> "
				+ "            <td bgcolor='#f4f4f4' align='center' style='padding: 0px 10px 0px 10px;'> "
				+ "                <table border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width: 600px;'> "
				+ "                    <tr> "
				+ "                        <td bgcolor='#f4f4f4' align='left' style='padding: 0px 30px 30px 30px; color: #666666; font-family: Lato, Helvetica, Arial, sans-serif; font-size: 14px; font-weight: 400; line-height: 18px;'> <br> "
				+ "                        </td> "
				+ "                    </tr> "
				+ "                </table> "
				+ "            </td> "
				+ "        </tr> "
				+ "    </table> "
				+ "</div> ");
		
		javaMailSender.send(mailMessage);
	}
}
