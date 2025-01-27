package tech.dev.eVoyageBackend.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import tech.dev.eVoyageBackend.configs.CustomResponseErrorHandler;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        // Désactiver le mode streaming en utilisant BufferingClientHttpRequestFactory
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(5000); // Timeout pour la connexion
        requestFactory.setReadTimeout(5000);    // Timeout pour la lecture

        RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(requestFactory));

        // Ajouter un gestionnaire d'erreurs personnalisé (optionnel)
        restTemplate.setErrorHandler(new CustomResponseErrorHandler());

        return restTemplate;
    }
}
