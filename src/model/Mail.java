package model;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

public class Mail {
	private static final Logger logger = (Logger) LoggerFactory.getLogger(Mail.class);
	private String host = "";
	private int port = 0;
	private String username = "";
	private String password = "";

	public Mail(String host, int port, String username, String password) {
		super();
		this.host = host;
		this.port = port;
		this.username = username;
		this.password = password;
	}

	public void sendMail(String para, String remitente, String asunto, String mensaje) {

		Properties prop = new Properties();
		prop.put("mail.smtp.auth", true);
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", host);
		prop.put("mail.smtp.port", port);
		prop.put("mail.smtp.ssl.trust", host);
        prop.put("mail.smtp.auth", "true");


		Session session = Session.getInstance(prop, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(remitente));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(para));
			message.setSubject(asunto);

			MimeBodyPart mimeBodyPart = new MimeBodyPart();
			mimeBodyPart.setContent(mensaje, "text/html");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(mimeBodyPart);


			message.setContent(multipart);

			Transport.send(message);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

}
