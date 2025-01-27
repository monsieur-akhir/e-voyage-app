
package tech.dev.eVoyageBackend.utils;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import tech.dev.eVoyageBackend.utils.EmailTemplateService;
import tech.dev.eVoyageBackend.utils.Utilities;

@Service
@Slf4j
public class EmailService {
//    private static final Logger log = LoggerFactory.getLogger(EmailService.class);
    private final String API_EMAIL_URL = "http://10.25.3.81:3010/mail";
    private final String SENDER_EMAIL = "FIYONGO.ext@orange.com";
    
    @Autowired
    private EmailTemplateService templateService;
//    
//    /**
//     * Envoie un email avec code OTP à un utilisateur
//     * @param recipientEmail Email du destinataire
//     * @param recipientName Nom du destinataire
//     * @param otpCode Code OTP à envoyer
//     * @throws IOException En cas d'erreur d'envoi
//     */
//    public void sendOtpEmail(String recipientEmail, String recipientName, String otpCode) throws IOException {
//        log.info("Début de l'envoi d'email OTP à {}", recipientEmail);
//        
//        try {
//            String htmlContent = generateOtpEmailTemplate(recipientName, otpCode);
//            
//            // Construction du corps de la requête
//            JSONObject jsonBody = new JSONObject()
//                .put("from", SENDER_EMAIL)
//                .put("to", new JSONArray().put(recipientEmail))
//                .put("subject", "Code de vérification - Connexion")
//                .put("text", "Votre code OTP est : " + otpCode)
//                .put("html", htmlContent);
//
//            String response = sendEmail(jsonBody.toString());
//            log.info("Email OTP envoyé avec succès à {}. Réponse : {}", recipientEmail, response);
//            
//        } catch (Exception e) {
//            log.error("Erreur lors de l'envoi de l'email OTP à {}", recipientEmail, e);
//            throw new IOException("Échec de l'envoi de l'email OTP", e);
//        }
//    }
//    
//    /**
//     * Envoie un email générique
//     * @param subject Sujet de l'email
//     * @param recipientEmail Email du destinataire
//     * @param htmlContent Contenu HTML de l'email
//     * @param textContent Contenu texte de l'email
//     * @throws IOException En cas d'erreur d'envoi
//     */
//    public void sendGenericEmail(String subject, String recipientEmail, String htmlContent, String textContent) throws IOException {
//        log.info("Début de l'envoi d'email générique à {}", recipientEmail);
//        
//        try {
//            JSONObject jsonBody = new JSONObject()
//                .put("from", SENDER_EMAIL)
//                .put("to", new JSONArray().put(recipientEmail))
//                .put("subject", subject)
//                .put("text", textContent)
//                .put("html", htmlContent);
//
//            String response = sendEmail(jsonBody.toString());
//            log.info("Email générique envoyé avec succès à {}. Réponse : {}", recipientEmail, response);
//            
//        } catch (Exception e) {
//            log.error("Erreur lors de l'envoi de l'email générique à {}", recipientEmail, e);
//            throw new IOException("Échec de l'envoi de l'email générique", e);
//        }
//    }
//    
//    /**
//     * Envoie effectif de l'email via l'API
//     * @param jsonBody Corps de la requête en JSON
//     * @return Réponse de l'API
//     * @throws IOException En cas d'erreur d'envoi
//     */
//    private String sendEmail(String jsonBody) throws IOException {
//        OkHttpClient client = new OkHttpClient.Builder()
//            .connectTimeout(30, TimeUnit.SECONDS)
//            .readTimeout(30, TimeUnit.SECONDS)
//            .writeTimeout(30, TimeUnit.SECONDS)
//            .build();
//        
//        // Correction de la création du RequestBody
//        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
//        RequestBody body = RequestBody.create(mediaType, jsonBody);  // Ordre corrigé
//
//        Request request = new Request.Builder()
//            .url(API_EMAIL_URL)
//            .post(body)
//            .addHeader("Content-Type", "application/json")
//            .addHeader("Accept", "application/json")
//            .build();
//
//        try (Response resp = client.newCall(request).execute()) {
//            if (resp.isSuccessful()) {
//                return resp.body().string();
//            } else {
//                String errorBody = resp.body() != null ? resp.body().string() : "No error body";
//                throw new IOException(String.format("Échec de la requête. Code: %d, Message: %s", 
//                    resp.code(), errorBody));
//            }
//        }
//    }
//    
//    /**
//     * Génère le template HTML pour l'email OTP
//     */
//    private String generateOtpEmailTemplate(String recipientName, String otpCode) {
//        return String.format(
//            "<div style='font-family: Arial, sans-serif; padding: 20px; max-width: 600px; margin: 0 auto;'>" +
//            "<div style='background-color: #f8f9fa; padding: 20px; border-radius: 5px;'>" +
//            "<h2 style='color: #2c3e50; margin-bottom: 20px;'>Code de vérification</h2>" +
//            "<p style='color: #34495e;'>Bonjour %s,</p>" +
//            "<p style='color: #34495e;'>Voici votre code de vérification :</p>" +
//            "<div style='background-color: #ffffff; padding: 15px; border-radius: 5px; " +
//            "text-align: center; font-size: 24px; font-weight: bold; margin: 20px 0; " +
//            "border: 1px solid #e0e0e0;'>" +
//            "%s" +
//            "</div>" +
//            "<p style='color: #7f8c8d; font-size: 14px;'>Ce code est valable pour une durée limitée.</p>" +
//            "<p style='color: #7f8c8d; font-size: 14px;'>Si vous n'avez pas demandé ce code, " +
//            "veuillez ignorer cet email.</p>" +
//            "</div>" +
//            "</div>",
//            recipientName,
//            otpCode
//        );
//    }
    
    @Value("${app.email.templates.otp:otp-template}")
    private String otpTemplateName;


    @Value("${app.email.templates.confirmation:confirmation-template}")
    private String confirmationTemplateName;
    
    @Value("${api.email.url}")
    private String apiEmailUrl;
    
    @Value("${sender.email}")
    private String senderEmail;
    
    @Value("${app.ministere.nom:Orange RDC}")
    private String nomMinistere;
    
    @Value("${app.login.url}")
    private String loginBaseUrl;
    
    @Value("${app.api.url}")
    private String apiUrl;
    
    @Value("${app.api.doc.url}")
    private String docUrl;
    
    public void sendOtpEmail(String recipientEmail, String recipientName, String otpCode) throws IOException {
        Map<String, Object> variables = new HashMap<>();
        variables.put("entete", "Code de vérification");
        variables.put("titre", "Authentification à double facteur");
        variables.put("recipientName", recipientName);
        variables.put("otpCode", otpCode);
        variables.put("expirationTime", "2 minutes");
        variables.put("currentDate", new SimpleDateFormat("dd/MM/yyyy à HH:mm").format(Utilities.getCurrentDate()));
        variables.put("nomMinistere", nomMinistere);

        String htmlContent = templateService.processTemplate(otpTemplateName, variables);
        String textContent = String.format(
            "Code de vérification\n\n" +
            "Bonjour %s,\n\n" +
            "Votre code OTP est : %s\n" +
            "Ce code est valable pour une durée de 2 minutes.\n\n" +
            "Ce mail est automatique, merci de ne pas y répondre.\n\n" +
            "© %s",
            recipientName, otpCode, nomMinistere
        );

        sendEmail("Code de vérification - Connexion", recipientEmail, htmlContent, textContent);
    }

    public void sendOtpResetEmail(String recipientEmail, String recipientName, String otpCode) throws IOException {
        Map<String, Object> variables = new HashMap<>();
        variables.put("entete", "Code OTP pour le changement de mot de passe");
        variables.put("titre", "Réinitialisation de mot de passe");
        variables.put("recipientName", recipientName);
        variables.put("otpCode", otpCode);
        variables.put("expirationTime", "2 minutes");
        variables.put("currentDate", new SimpleDateFormat("dd/MM/yyyy à HH:mm").format(Utilities.getCurrentDate()));
        variables.put("nomMinistere", nomMinistere);

        String htmlContent = templateService.processTemplate(otpTemplateName, variables);
        String textContent = String.format(
                "Code de vérification\n\n" +
                        "Bonjour %s,\n\n" +
                        "Votre code OTP pour changer votre mot de passe est : %s\n" +
                        "Ce code est valable pour une durée de 2 minutes.\n\n" +
                        "Ce mail est automatique, merci de ne pas y répondre.\n\n" +
                        "© %s",
                recipientName, otpCode, nomMinistere
        );

        sendEmail("Code de vérification - Réinitialisation de mot de passe", recipientEmail, htmlContent, textContent);
    }

    public void sendPasswordChangedEmail(String recipientEmail, String recipientName) throws IOException {
        Map<String, Object> variables = new HashMap<>();
        variables.put("entete", "Confirmation de changement de mot de passe");
        variables.put("titre", "Confirmation de changement de mot de passe");
        variables.put("recipientName", recipientName);
        variables.put("currentDate", new SimpleDateFormat("dd/MM/yyyy à HH:mm").format(Utilities.getCurrentDate()));
        variables.put("nomMinistere", nomMinistere);

        String htmlContent = templateService.processTemplate(confirmationTemplateName, variables);
        String textContent = String.format(
                "Code de vérification\n\n" +
                        "Bonjour %s,\n\n" +
                        "Votre mot de passe a été changé avec succès.\n" +
                        "Ce mail est automatique, merci de ne pas y répondre.\n\n" +
                        "© %s",
                recipientName, nomMinistere
        );

        sendEmail("Confirmation de changement de mot de passe", recipientEmail, htmlContent, textContent);
    }


    public void sendEmail(String subject, String recipientEmail, String htmlContent, String textContent) throws IOException {
        log.info("Envoi d'email à {}", recipientEmail);
        
        try {
            JSONObject jsonBody = new JSONObject()
                .put("from", senderEmail)
                .put("to", new JSONArray().put(recipientEmail))
                .put("subject", subject)
                .put("text", textContent)
                .put("html", htmlContent);

            String response = sendEmailRequest(jsonBody.toString());
            log.info("Email envoyé avec succès à {}. Réponse : {}", recipientEmail, response);
            
        } catch (Exception e) {
            log.error("Erreur lors de l'envoi de l'email à {}", recipientEmail, e);
            throw new IOException("Échec de l'envoi de l'email", e);
        }
    }
    
    /**
     * Envoie les identifiants de connexion à un utilisateur
     * @param recipientEmail Email du destinataire
     * @param recipientName Nom du destinataire
     * @param login Identifiant de connexion
     * @param password Mot de passe
     */
    public void sendPasswordEmail(String recipientEmail, String recipientName, String login, String password) throws IOException {
        log.info("Envoi des identifiants de connexion à {}", recipientEmail);
        
        Map<String, Object> variables = new HashMap<>();
        variables.put("entete", "Identifiants de connexion");
        variables.put("titre", "Accès à votre compte");
        variables.put("recipientName", recipientName);
        variables.put("login", login);
        variables.put("password", password);
        variables.put("loginUrl", loginBaseUrl);
        variables.put("currentDate", new SimpleDateFormat("dd/MM/yyyy à HH:mm").format(Utilities.getCurrentDate()));
        variables.put("nomMinistere", nomMinistere);

        String htmlContent = templateService.processTemplate("password-template", variables);
        String textContent = String.format(
            "Bonjour %s,\n\n" +
            "Voici vos identifiants de connexion :\n\n" +
            "Identifiant : %s\n" +
            "Mot de passe : %s\n\n" +
            "Pour vous connecter, rendez-vous sur : %s\n\n" +
            "Important : Pour des raisons de sécurité, nous vous recommandons de :\n" +
            "- Changer votre mot de passe dès votre première connexion\n" +
            "- Ne jamais partager vos identifiants avec un tiers\n" +
            "- Utiliser un mot de passe fort et unique\n\n" +
            "Ce mail est automatique, merci de ne pas y répondre.\n\n" +
            "© %s\n" +
            "Email envoyé le %s",
            recipientName, login, password, loginBaseUrl, nomMinistere,
            new SimpleDateFormat("dd/MM/yyyy à HH:mm").format(Utilities.getCurrentDate())
        );

        sendEmail("Vos identifiants de connexion", recipientEmail, htmlContent, textContent);
    }
    
    
    /**
     * Envoie les identifiants d'authentification API
     */
    public void sendAuthCredentials(String recipientEmail, String recipientName, 
                                  String clientId, String clientSecret) throws IOException {
        log.info("Envoi des identifiants d'authentification API à {}", recipientEmail);
        
        // Création des credentials en Base64 pour l'exemple
        String base64Credentials = Base64.getEncoder()
            .encodeToString((clientId + ":" + clientSecret).getBytes(StandardCharsets.UTF_8));
        
        Map<String, Object> variables = new HashMap<>();
        variables.put("entete", "Identifiants API");
        variables.put("titre", "Accès à l'API via Basic Auth");
        variables.put("recipientName", recipientName);
        variables.put("clientId", clientId);
        variables.put("clientSecret", clientSecret);
        variables.put("base64Credentials", base64Credentials);
        variables.put("apiUrl", apiUrl);
        variables.put("docUrl", docUrl);
        variables.put("currentDate", new SimpleDateFormat("dd/MM/yyyy à HH:mm").format(Utilities.getCurrentDate()));
        variables.put("nomMinistere", nomMinistere);

        String htmlContent = templateService.processTemplate("auth-credentials-template", variables);
        String textContent = String.format(
            "Bonjour %s,\n\n" +
            "Voici vos identifiants d'authentification pour l'API :\n\n" +
            "Client ID : %s\n" +
            "Client Secret : %s\n\n" +
            "Credentials Base64 : %s\n\n" +
            "Pour obtenir un token, utilisez :\n" +
            "curl -X POST %s/token \\\n" +
            "    -H \"Authorization: Basic %s\" \\\n" +
            "    -H \"Content-Type: application/x-www-form-urlencoded\" \\\n" +
            "    -d \"grant_type=client_credentials\"\n\n" +
            "Important :\n" +
            "- Ces identifiants sont confidentiels\n" +
            "- Stockez-les de manière sécurisée\n" +
            "- N'incluez jamais ces identifiants dans le code source\n\n" +
            "Documentation : %s\n\n" +
            "© %s\n" +
            "Email envoyé le %s",
            recipientName, clientId, clientSecret, base64Credentials, apiUrl, base64Credentials, 
            docUrl, nomMinistere, new SimpleDateFormat("dd/MM/yyyy à HH:mm").format(Utilities.getCurrentDate())
        );

        sendEmail("Identifiants d'authentification API", recipientEmail, htmlContent, textContent);
    }
    
    private String sendEmailRequest(String jsonBody) throws IOException {
        OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build();
        
        RequestBody body = RequestBody.create(
            MediaType.parse("application/json; charset=utf-8"),
            jsonBody
        );

        Request request = new Request.Builder()
            .url(apiEmailUrl)
            .post(body)
            .addHeader("Content-Type", "application/json")
            .addHeader("Accept", "application/json")
            .build();

        try (Response resp = client.newCall(request).execute()) {
            if (resp.isSuccessful() && resp.body() != null) {
                return resp.body().string();
            } else {
                String errorBody = resp.body() != null ? resp.body().string() : "No error body";
                throw new IOException(String.format("Échec de la requête. Code: %d, Message: %s", 
                    resp.code(), errorBody));
            }
        }
    }
}