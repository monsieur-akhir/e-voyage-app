package tech.dev.eVoyageBackend.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import tech.dev.eVoyageBackend.utils.ExceptionUtils;
import tech.dev.eVoyageBackend.utils.ParamsUtils;
import tech.dev.eVoyageBackend.utils.contract.Response;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.*;






@Component
public class HostingUtils {
	
	@Autowired
	private ParamsUtils paramsUtils;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	private ExceptionUtils exceptionUtils;

	private static final Logger slf4jLogger = LoggerFactory.getLogger(HostingUtils.class);
	
	
	
	
	public <T> Response<T> sendEmail(Map<String, String> from, List<Map<String, String>> toRecipients, String subject,
			String body, List<String> attachmentsFilesAbsolutePaths, Context context, String templateName,
			Locale locale) {
		Response<T> response = new Response<T>();
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

		// /*
		// * A retirer
		// */
		// if(true)
		// return response;
		//
		// /*
		// * retirer
		// */

		String smtpServer = paramsUtils.getSmtpHost();
		if (smtpServer != null) {
			Boolean auth = false;
			javaMailSender.setHost(smtpServer);
			javaMailSender.setPort(paramsUtils.getSmtpPort());
			javaMailSender.setUsername(paramsUtils.getSmtpMailAdresse());
			javaMailSender.setPassword(paramsUtils.getSmtpPassword());

			// ADD NEW CONFIG for "no object DCH for MIME type multipart/mixed" error
			MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
			mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
			mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
			mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
			mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
			mc.addMailcap("message/rfc822;; x-java-content- handler=com.sun.mail.handlers.message_rfc822");
			Thread.currentThread().setContextClassLoader(getClass().getClassLoader());
			auth = true;

			javaMailSender.setJavaMailProperties(getMailProperties(paramsUtils.getSmtpHost(), auth));

			MimeMessage message = javaMailSender.createMimeMessage();
			try {
				MimeMessageHelper helper = new MimeMessageHelper(message, Boolean.TRUE, "UTF-8");

				// sender
				helper.setFrom(new InternetAddress(from.get("email"), from.get("user")));
				// sender

				// recipients
				List<InternetAddress> to = new ArrayList<InternetAddress>();
				for (Map<String, String> recipient : toRecipients) {
					to.add(new InternetAddress(recipient.get("email"), recipient.get("user")));
				}
				helper.setTo(to.toArray(new InternetAddress[0]));

				// Subject and body
				helper.setSubject(subject);
				if (context != null && templateName != null) {
					body = templateEngine.process(templateName, context);
				}
				helper.setText(body, true);

				// Attachments
				if (attachmentsFilesAbsolutePaths != null && !attachmentsFilesAbsolutePaths.isEmpty()) {
					for (String attachmentPath : attachmentsFilesAbsolutePaths) {
						File pieceJointe = new File(attachmentPath);
						FileSystemResource file = new FileSystemResource(attachmentPath);
						if (pieceJointe.exists() && pieceJointe.isFile()) {
							helper.addAttachment(file.getFilename(), file);
						}
					}
				}

				javaMailSender.send(message);
				response.setHasError(Boolean.FALSE);
				/// gerer les cas d'exeption de non envoi de mail
			} catch (MessagingException e) {
				e.printStackTrace();
				exceptionUtils.EXCEPTION(response, locale, e);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				exceptionUtils.EXCEPTION(response, locale, e);
			}
		}
		return response;
	}
//	
//	private Properties getMailProperties(String host, Boolean auth) {
//		Properties properties = new Properties();
//		properties.setProperty("mail.smtp.auth", "false");
//		properties.setProperty("mail.transport.protocol", "smtp");
//		properties.put("mail.smtp.starttls.enable", "false");
//		properties.setProperty("mail.smtp.starttls.required", "false");
//		// properties.setProperty("mail.debug", "true");
//		return properties;
//	}


	private Properties getMailProperties(String host, Boolean auth) {
		Properties properties = new Properties();
		properties.setProperty("mail.transport.protocol", "smtp");
		properties.setProperty("mail.smtp.auth", auth.toString());
		properties.setProperty("mail.smtp.starttls.enable", "true");
		properties.setProperty("mail.smtp.starttls.required", "true");
		properties.setProperty("mail.debug", "true");
		if (host.equals("smtp.gmail.com")) {
			properties.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");
		}
		return properties;
	}

}
