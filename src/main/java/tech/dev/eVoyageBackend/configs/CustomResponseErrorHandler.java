package tech.dev.eVoyageBackend.configs;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

public class CustomResponseErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        // Retourne `true` uniquement pour les codes d'erreur HTTP (4xx ou 5xx)
        return response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        // Laisser l'erreur être gérée par la logique de votre code (pas d'exception)
        System.out.println("Erreur HTTP détectée : " + response.getStatusCode());
    }
}
