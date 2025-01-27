package tech.dev.eVoyageBackend.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "profiling")
public class ProfilingConfig {

    // Constantes dynamiques pour profilage
    private String operatorCode;       // Code opérateur
    private String accountType;        // Type de compte (PC)
    private String affiliateCode;      // Code affilié
    private String providerId;         // ID du fournisseur
    private String userType;           // Type d'utilisateur (CUSTOMER)
    private String password;           // Mot de passe pour le profilage

    // URLs des services de profilage
    private String apiUrl;             // URL de l'API de profilage
    private String pinValidationUrl;   // URL pour validation du PIN
    private String userInfoUrl;        // URL pour les informations utilisateur
    private String accountInfoUrl;     // URL pour les informations de compte
    private String walletToBankUrl;    // URL pour transfert de portefeuille à banque
    private String requestType;        // Type de requête (PROFILING)
    private String listaccountUrl;     // URL pour la liste des comptes
    private String listaccountType;      // URL pour la liste des portefeuilles
    private String autolinkType;       // Type de lien automatique
    private String autolinkUrl;        // URL pour le lien automatique
    // Configuration spécifique pour UserInquiry
    private String userinquiryUrl;     // URL de l'API UserInquiry
    private String addonId;            // Addon ID pour UserInquiry
    private String countryId;          // Country ID pour UserInquiry
    private String currency;           // Devise utilisée dans UserInquiry
    private String apiKey;             // Clé API pour UserInquiry

}
