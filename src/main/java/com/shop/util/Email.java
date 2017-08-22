package com.shop.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 发邮件工具类
 */

public class Email {

	/**
	 * 发邮件
	 * @param to 收件人
	 * @param content 邮件内容
	 */
	public static void sendEamil(String to, String content) {
		// 1、获取Session
		Properties props = new Properties();
		props.setProperty("mail.host", "smtp.163.com");// 设置主机名
		props.setProperty("mail.smtp.auth", "true");// 设置需要认证
		Authenticator authenticator = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("niu827523398", "zxc123");
			}
		};
		Session session = Session.getInstance(props, authenticator);

		// 2、获取MimeMessage
		MimeMessage mime = new MimeMessage(session);
		try {
			mime.setFrom(new InternetAddress("niu827523398@163.com")); // 设置发件人
			mime.setRecipients(RecipientType.TO, to); // 设置收件人
			mime.setSubject("激活商店邮件"); // 设置标题
			mime.setContent(content, "text/html;charset=utf-8");

			// 3、发邮件
			Transport.send(mime);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
