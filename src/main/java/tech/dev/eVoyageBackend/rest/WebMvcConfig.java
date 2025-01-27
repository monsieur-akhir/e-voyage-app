package tech.dev.eVoyageBackend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tech.dev.eVoyageBackend.rest.RestInterceptor;
import tech.dev.eVoyageBackend.rest.SessionRenewalInterceptor;
import tech.dev.eVoyageBackend.rest.ConnectionInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Autowired
	private SessionRenewalInterceptor sessionRenewalInterceptor;

	@Autowired
	private ConnectionInterceptor connectionInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// Ajout des intercepteurs existants
		registry.addInterceptor(new RestInterceptor());
		registry.addInterceptor(sessionRenewalInterceptor).addPathPatterns("/**");

		// Ajout de l'intercepteur ConnectionInterceptor
		registry.addInterceptor(connectionInterceptor).addPathPatterns("/**");
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("*")  // Permet toutes les origines
				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
				.allowedHeaders("*")
				.allowCredentials(false);  // Désactive les credentials
	}
}
