package tech.dev.eVoyageBackend.configs;

import java.util.*;
import java.io.IOException;
import java.security.SecureRandom;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.ZoneId;

import lombok.extern.java.Log;
import okhttp3.OkHttpClient;
import tech.dev.eVoyageBackend.dao.entity.ApiLogs;
import tech.dev.eVoyageBackend.dao.entity.ApiUser;
import tech.dev.eVoyageBackend.dao.entity.User;
import tech.dev.eVoyageBackend.dao.repository.ApiLogsRepository;
import tech.dev.eVoyageBackend.dao.repository.ApiUserRepository;
import tech.dev.eVoyageBackend.dao.repository.UserRepository;
import tech.dev.eVoyageBackend.utils.Utilities;
import tech.dev.eVoyageBackend.utils.contract.Request;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@Log
@Service
public class UtilesFonctions {

    @Autowired private UserRepository userRepository;
    @Autowired private ApiUserRepository apiUserRepository;
    @Autowired private ApiLogsRepository apiLogsRepository;

    // @Autowired private ParametreGenerauxBusiness parametreService;

    private final Logger slf4jLogger = LoggerFactory.getLogger(getClass());


    public void tokenExpiration(String token, Integer userId, Locale locale) {
        LocalDateTime currentTime = LocalDateTime.now();

        if (userId == null) {
            String goodToken= "";
            if(token.startsWith("Bearer")){
                goodToken = token.split(" ")[1];
            }else{
                goodToken = token;
            }
        
            System.out.println(goodToken);

            List<ApiUser> usersFind = apiUserRepository.findByToken(goodToken, false);
            if (usersFind.isEmpty()) {
                throw new InvalidTokenException("Token invalide");
            }

            LocalDateTime tokenExpireAt = usersFind.get(0).getTokenExpireAt()
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();

            if (currentTime.isAfter(tokenExpireAt)) {
                throw new SessionExpiredException("Session expirée");
            } else {
                System.out.println("Le token est encore valide.");
            }
        } else {
            List<User> usersFind = userRepository.findByToken(token, false);
            if (usersFind.isEmpty()) {
                throw new InvalidTokenException("Token invalide");
            }

            LocalDateTime tokenExpireAt = usersFind.get(0).getTokenExpireAt()
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();

            if (currentTime.isAfter(tokenExpireAt)) {
                throw new SessionExpiredException("Session expirée");
            } else {
                System.out.println("Le token est encore valide.");
            }
        }
    }


// Méthode surchargée sans le paramètre `type`
    public void tokenExpiration(String token, Locale locale) {
        // Appel de la méthode principale avec un type par défaut
        tokenExpiration(token, null, locale);
    }

    public class InvalidTokenException extends RuntimeException {
        public InvalidTokenException(String message) {
            super(message);
        }
    }
    
    public class SessionExpiredException extends RuntimeException {
        public SessionExpiredException(String message) {
            super(message);
        }
    }


    // public String getOauthToken() {
    //     try {
    //         // Récupérer les paramètres depuis la base de données
    //         String tokenUrl = parametreService.getParamValue("tokenUrl",
    //                 "https://localhost:3030/authentification/apitoken/token");
    //         String grantType = parametreService.getParamValue("grant_type", "client_credentials");
    //         String authorization = parametreService.getParamValue("Authorization",
    //                 "Basic authorization");

    //         HttpHeaders headers = new HttpHeaders();
    //         headers.set("Content-Type", "application/x-www-form-urlencoded");
    //         headers.set("Accept", "application/json");
    //         headers.set("Authorization", authorization);

    //         String requestBody = "grant_type=" + grantType;
    //         HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
    //         RestTemplate restTemplate = new RestTemplate();
    //         ResponseEntity<Map> response = restTemplate.exchange(tokenUrl, HttpMethod.POST, request, Map.class);

    //         if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
    //             Map<String, Object> responseBody = response.getBody();
    //             String accessToken = (String) responseBody.get("access_token");
    //             Integer expiresIn = (Integer) responseBody.get("expires_in");

    //             // Calculer la date d'expiration
    //             LocalDateTime expirationDateTime = LocalDateTime.now().plusSeconds(expiresIn);

    //             // Enregistrer le nouveau token et la date d'expiration en base de données
    //             ParametreGenerauxDto tokenDto = new ParametreGenerauxDto();
    //             tokenDto.setParamName("oauthToken");
    //             tokenDto.setParamValue(accessToken);

    //             ParametreGenerauxDto expirationDto = new ParametreGenerauxDto();
    //             expirationDto.setParamName("oauthTokenExpiration");
    //             expirationDto.setParamValue(expirationDateTime.toString());

    //             Request<ParametreGenerauxDto> requestToSave = new Request<>();
    //             requestToSave.setDatas(Arrays.asList(tokenDto, expirationDto));
    //             parametreService.createOrUpdateLogin(requestToSave, Locale.getDefault());

    //             log.info("Nouveau token OAuth obtenu et enregistré avec expiration.");
    //             return accessToken;
    //         } else {
    //             log.severe("Échec de l'obtention du token OAuth : " + response.getStatusCode());
    //             return null;
    //         }
    //     } catch (Exception e) {
    //         slf4jLogger.error("Erreur lors de l'obtention du token OAuth : " + e.getMessage());
    //         return null;
    //     }
    // }

    // public String getValidOauthToken() {
    //     try {
    //         // Vérifier si un token existant est présent dans la base de données
    //         String storedToken = parametreService.getParamValue("oauthToken", null);
    //         String tokenExpiration = parametreService.getParamValue("oauthTokenExpiration", null);

    //         if (storedToken != null && tokenExpiration != null) {
    //             LocalDateTime expirationDateTime = LocalDateTime.parse(tokenExpiration);

    //             // Vérifier si le token est encore valide
    //             if (LocalDateTime.now().isBefore(expirationDateTime)) {
    //                 log.info("Token OAuth valide trouvé dans la base de données.");
    //                 return storedToken; // Utiliser le token existant
    //             } else {
    //                 log.info("Token OAuth expiré, récupération d'un nouveau token.");
    //             }
    //         }

    //         // Si aucun token valide n'est trouvé, ou s'il est expiré, en obtenir un nouveau
    //         return getOauthToken();
    //     } catch (Exception e) {
    //         slf4jLogger.error("Erreur lors de la gestion du token OAuth : " + e.getMessage());
    //         return null;
    //     }
    // }


    public static JsonNode convertXmlToJson(String xmlData) throws Exception {
        // Initialiser un mapper pour la conversion XML -> JSON
        XmlMapper xmlMapper = new XmlMapper();
        ObjectMapper jsonMapper = new ObjectMapper();

        // Convertir XML en arbre JSON
        JsonNode jsonNode = xmlMapper.readTree(xmlData.getBytes());
        return jsonNode;
    }

    public static List<JsonNode> extractTransactions(JsonNode jsonNode) {
        List<JsonNode> transactions = new ArrayList<>();

        // Naviguer dans l'arborescence JSON pour trouver les transactions
        JsonNode walletResponse = jsonNode.path("wallet_response");
        if (walletResponse.isMissingNode()) {
            return transactions; // Aucun `wallet_response` trouvé
        }

        JsonNode dataNodes = walletResponse.path("data");
        if (dataNodes.isArray()) {
            for (JsonNode dataNode : dataNodes) {
                if (dataNode.has("TXN_ID")) {
                    transactions.add(dataNode); // Ajouter uniquement les transactions avec un TXN_ID
                }
            }
        }

        return transactions;
    }

    private void saveApiLogs(String url, String method, HttpHeaders headers, HttpEntity<String> requestEntity,
    ResponseEntity<String> response) {
        ApiLogs apilogs = new ApiLogs();
        apilogs.setRequestTime(Utilities.getCurrentDate());
        apilogs.setCreatedDate(Utilities.getCurrentDate());
        apilogs.setRequestUrl(url);
        apilogs.setRequestMethod(method);
        apilogs.setRequestHeaders(headers.toString());
        apilogs.setRequestBody(requestEntity.toString());
        apilogs.setResponseStatus(response.getStatusCode().value());
        apilogs.setResponseBody(response.getBody());
        apilogs.setStatus("ACTIVE");
        apilogs.setCreatedBy("system");

        apiLogsRepository.save(apilogs);
    }

    /**
     * Sauvegarde les logs de l'API en cas d'erreur.
     */
    private void saveErrorLogs(String url, String method, HttpHeaders headers, HttpEntity<String> requestEntity,
                            Exception e) {
        ApiLogs apilogs = new ApiLogs();
        apilogs.setRequestTime(Utilities.getCurrentDate());
        apilogs.setCreatedDate(Utilities.getCurrentDate());
        apilogs.setRequestUrl(url);
        apilogs.setRequestMethod(method);
        apilogs.setRequestHeaders(headers.toString());
        apilogs.setRequestBody(requestEntity.toString());
        apilogs.setResponseStatus(500); // Statut d'erreur générique
        apilogs.setResponseBody(e.getMessage());
        apilogs.setStatus("ERROR");
        apilogs.setCreatedBy("system");

        apiLogsRepository.save(apilogs);
    }

    public String smsApiCall(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
    
        // Créer la requête GET
        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(url)
                .get()
                .addHeader("Content-Type", "application/json")
                .build();
    
        // Exécuter la requête
        try (okhttp3.Response resp = client.newCall(request).execute()) {
            // Log de l'URL appelée
            System.out.println("Requête URL : " + url);
            
            // Log du code de réponse
            System.out.println("Code de réponse : " + resp.code());
    
            // Log des headers de la réponse
            System.out.println("Headers de la réponse : " + resp.headers());
    
            // Vérifier si la réponse est réussie (statut 200 OK)
            if (resp.isSuccessful()) {
                // Lire la réponse en tant que chaîne
                String responseString = resp.body() != null ? resp.body().string() : null;
    
                // Log de la réponse
                System.out.println("Réponse brute : " + responseString);
    
                return responseString != null ? responseString + "{SUCCES SMS}" : null;
            } else {
                throw new IOException("Requête non réussie, code de réponse : " + resp.code());
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de l'exécution de la requête : " + e.getMessage());
            throw e;
        }
    }
    
   
    
}
