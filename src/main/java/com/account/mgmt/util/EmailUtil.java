package com.account.mgmt.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.account.mgmt.exception.EmailException;

public class EmailUtil {

	public static Session session = null;

	/**
	 * returns session obj
	 * @return
	 */
	public static Session getSession() {

		if (session == null) {

			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", AccountMgmtConstant.SMTP_HOST_SERVER);
			props.put("mail.smtp.port", AccountMgmtConstant.SMTP_HOST_PORT);

			session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(AccountMgmtConstant.EMAIL_ADMIN_USER,
							AccountMgmtConstant.EMIAL_ADMIN_PWD);
				}
			});
		}
		return session;
	}

	/**
	 * 
	 * @param userName
	 * @param toAddress
	 * @param linkWithJwt
	 * @throws com.account.mgmt.exception.EmailException
	 */
	public static void sendMail(String userName, String toAddress, String linkWithJwt)
			throws com.account.mgmt.exception.EmailException {

		

		Message message = new MimeMessage(getSession());
		try {
			message.setFrom(new InternetAddress("kaha@admin.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));
			message.setSubject("Account Login Request");
			message.setContent(constructMailBody(userName, linkWithJwt),"text/html; charset=utf-8");
			Transport.send(message);
		} catch (AddressException e) {
			throw new EmailException(AccountMgmtConstant.EMAIL_NOT_SENT_ERR_MSG + toAddress);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new EmailException(AccountMgmtConstant.EMAIL_NOT_SENT_ERR_MSG + toAddress);
		}

	}

	/**
	 * 
	 * @param userName
	 * @param link
	 * @return
	 */
	public static String constructMailBody(String userName, String link) {

		String htmlBody = "Dear <b>" + userName + "</b>," + "<br><br> Thank you for logging to Kaha System."
				+ "<br><br>Please <a href=" + link + "> Click here</a> to view your account info"
				+ "<br><br>Note:  This link will be active only for 15 mins, while accessing the link if you get expired message, please login to Kaha System to regenerate the link."
				+ "<br><br> Regards," + "<br><br> Kaha Team";

		return htmlBody;
	}
	public static void main(String[] args) throws EmailException {
		sendMail("sathish.raman", "sathish.raman36@gmail.com", "http://localhost:9095");
	}

}