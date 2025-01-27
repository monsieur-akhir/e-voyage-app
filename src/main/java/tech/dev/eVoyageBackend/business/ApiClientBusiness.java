package tech.dev.eVoyageBackend.business;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class ApiClientBusiness {

    public static String postSoap(String url, String soapRequest) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_XML);

        HttpEntity<String> requestEntity = new HttpEntity<>(soapRequest, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        return response.getBody();
    }
}
