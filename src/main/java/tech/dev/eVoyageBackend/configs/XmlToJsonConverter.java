package tech.dev.eVoyageBackend.configs;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Component;

@Component
public class XmlToJsonConverter {

    // Convertit XML en JSON tout en supprimant la racine
    public String convertXmlToJson(String xmlString, String rootElement) {
        try {
            JSONObject json = XML.toJSONObject(xmlString);

            // Supprimer l'élément racine "Response"
            JSONObject jsonWithoutRoot = removeRootElement(json, rootElement);

            return jsonWithoutRoot.toString(4); // JSON bien formaté
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Méthode utilitaire pour supprimer un élément racine spécifique
    private JSONObject removeRootElement(JSONObject json, String rootElement) {
        if (json.has(rootElement)) {
            return json.getJSONObject(rootElement);
        }
        return json;
    }
}
