package tech.dev.eVoyageBackend.utils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class ParamsUtils {

	@Value("${smtp.mail.adresse}")
	private String smtpLogin;

	@Value("${url.admin}")
	private String urlAdmin;
	
	@Value("${url.appl}")
	private String urlAppl;

	@Value("${smtp.mail.host}")
	private String smtpHost;

	@Value("${smtp.mail.port}")
	private Integer smtpPort;

	@Value("${smtp.mail.adresse}")
	private String smtpMailAdresse;

	@Value("${smtp.mail.password}")
	private String smtpPassword;

	@Value("${baseUrl}")
	private String baseUrl;

	@Value("${tiketsRepository}")
	private String tiketsRopository;

	@Value("${logoCompagnie}")
	private String logoCompagnie;

    @Value("${url-envoie-sms}")
	private String urlEnvoieSMS;
//    
    @Value("${session-time}")
	private Integer sessionTime;

}
