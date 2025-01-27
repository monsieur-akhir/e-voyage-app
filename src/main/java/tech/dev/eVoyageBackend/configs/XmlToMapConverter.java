package tech.dev.eVoyageBackend.configs;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.IOException;
import java.util.Map;

public class XmlToMapConverter {

    public Map<String, Object> convertXmlToMap(String xmlResponse) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        
        Map<String, Object> resultMap = xmlMapper.readValue(xmlResponse, Map.class);

        return resultMap;
    }
}

