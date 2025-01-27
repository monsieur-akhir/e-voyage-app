package tech.dev.eVoyageBackend.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailTemplateService {
    private static final String TEMPLATE_BASE_PATH = "templates/email/";
    private final Map<String, String> templateCache = new ConcurrentHashMap<>();
    
    @Autowired
    private ResourceLoader resourceLoader;  // Injection de ResourceLoader

    public EmailTemplateService() {
    }

    /**
     * Charge et compile un template avec les variables fournies
     */
    public String processTemplate(String templateName, Map<String, Object> variables) throws IOException {
        String template = getTemplate(templateName);
        return replaceVariables(template, variables);
    }

    /**
     * Récupère le template du cache ou le charge depuis le fichier
     */
    private String getTemplate(String templateName) throws IOException {
        return templateCache.computeIfAbsent(templateName, this::loadTemplate);
    }

    /**
     * Charge le template depuis le fichier
     */
    private String loadTemplate(String templateName) {
        try {
            Resource resource = resourceLoader.getResource("classpath:" + TEMPLATE_BASE_PATH + templateName + ".html");
            if (!resource.exists()) {
                throw new IOException("Template non trouvé : " + templateName);
            }
            
            try (Reader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)) {
                return FileCopyUtils.copyToString(reader);
            }
        } catch (IOException e) {
            log.error("Erreur lors du chargement du template {}", templateName, e);
            throw new RuntimeException("Erreur de chargement du template: " + templateName, e);
        }
    }

    /**
     * Remplace les variables dans le template
     */
    private String replaceVariables(String template, Map<String, Object> variables) {
        String result = template;
        for (Map.Entry<String, Object> entry : variables.entrySet()) {
            String placeholder = "{{" + entry.getKey() + "}}";
            result = result.replace(placeholder, String.valueOf(entry.getValue()));
        }
        return result;
    }

    /**
     * Rafraîchit le cache pour un template spécifique
     */
    public void refreshTemplate(String templateName) {
        templateCache.remove(templateName);
        loadTemplate(templateName);
    }

    /**
     * Vide le cache complet
     */
    public void clearCache() {
        templateCache.clear();
    }
}