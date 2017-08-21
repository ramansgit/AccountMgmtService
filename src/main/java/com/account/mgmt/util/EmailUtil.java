package com.account.mgmt.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.account.mgmt.exception.EmailException;

public class EmailUtil {

	public static Session session = null;

	/**
	 * returns session obj
	 * 
	 * @return
	 */
	public static Session getSession() {

		if (session == null) {
			Properties properties = new Properties();

			properties.put("mail.smtp.host", AccountMgmtConstant.SMTP_HOST_SERVER);
			properties.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.smtp.port", AccountMgmtConstant.SMTP_HOST_PORT);
			properties.put("mail.smtp.user", AccountMgmtConstant.EMAIL_ADMIN_USER);
			properties.put("mail.smtp.auth", "true");
			session = Session.getDefaultInstance(properties);
		}

		return session;
	}

	public static void sendMail(String userName, String toAddress, String linkWithJwt) throws EmailException {

		MimeMultipart multipart = new MimeMultipart();

		try {
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("kaha@adminteam"));
			msg.setRecipients(Message.RecipientType.TO, toAddress);
			msg.setSubject("Account Login Mail");
			msg.setSentDate(new Date());

			// BODY
			MimeBodyPart mbp = new MimeBodyPart();

			mbp.setContent(constructMailBody(userName, linkWithJwt), "text/html");

			multipart.addBodyPart(mbp);

			msg.setContent(multipart);
			
			session = getSession();
			Transport t = session.getTransport("smtp");
			t.connect((String) AccountMgmtConstant.EMAIL_ADMIN_USER, AccountMgmtConstant.EMAIL_ADMIN_PWD);
			t.sendMessage(msg, msg.getAllRecipients());
			t.close();

			System.out.println("Mail Sent Successfully");
		} catch (Exception e) {
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