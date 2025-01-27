package tech.dev.eVoyageBackend.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import tech.dev.eVoyageBackend.configs.ProfilingConfig;
import tech.dev.eVoyageBackend.utils.FunctionalError;
import tech.dev.eVoyageBackend.utils.contract.Response;

import java.util.Locale;
import java.util.Map;

@Service
public class PinValidationBusiness {

    private final RestTemplate restTemplate;
    private final ProfilingConfig profilingConfig;
    @Autowired
    private FunctionalError funtionalError;

    public PinValidationBusiness(RestTemplate restTemplate, ProfilingConfig profilingConfig) {
        this.restTemplate = restTemplate;
        this.profilingConfig = profilingConfig;
    }

    public boolean validatePin(String msisdn, String pin) {
        String url = profilingConfig.getPinValidationUrl();
        String requestBody = String.format("msisdn=%s&usertype=%s&pin=%s", msisdn, profilingConfig.getUserType(), pin);

        try {
            String response = restTemplate.postForObject(url, requestBody, String.class);
            return response != null && response.contains("\"responseCode\":200");
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la validation du PIN : " + e.getMessage());
        }
    }


}
