

/*
 * Java controller for entity table api_logs 
 * Created on 2024-11-25 ( Time 15:12:19 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2017 Savoir Faire Linux. All Rights Reserved.
 */

package tech.dev.eVoyageBackend.rest.api;

import lombok.extern.java.Log;
import tech.dev.eVoyageBackend.business.*;
import tech.dev.eVoyageBackend.rest.fact.ControllerFactory;
import tech.dev.eVoyageBackend.utils.*;
import tech.dev.eVoyageBackend.utils.contract.*;
import tech.dev.eVoyageBackend.utils.dto.*;
import tech.dev.eVoyageBackend.utils.enums.FunctionalityEnum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Locale;

/**
Controller for table "api_logs"
 * 
 * @author SFL Back-End developper
 *
 */
@Log
@CrossOrigin("*")
@RestController
@RequestMapping(value="/apiLogs")
public class ApiLogsController {

    @Autowired
    private ControllerFactory<ApiLogsDto> controllerFactory;
    @Autowired
    private ExportBusiness exportBusiness;
    @Autowired
    private ApiLogsBusiness apiLogsBusiness;
    @Autowired
    private HttpServletRequest requestBasic;

    private Logger slf4jLogger = LoggerFactory.getLogger(getClass());

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<ApiLogsDto> create(@RequestBody Request<ApiLogsDto> request) {
    	log.info("start method /apiLogs/create");
        Response<ApiLogsDto> response = controllerFactory.create(apiLogsBusiness, request, FunctionalityEnum.CREATE_API_LOGS);
		log.info("end method /apiLogs/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<ApiLogsDto> update(@RequestBody Request<ApiLogsDto> request) {
    	log.info("start method /apiLogs/update");
        Response<ApiLogsDto> response = controllerFactory.update(apiLogsBusiness, request, FunctionalityEnum.UPDATE_API_LOGS);
		log.info("end method /apiLogs/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<ApiLogsDto> delete(@RequestBody Request<ApiLogsDto> request) {
    	log.info("start method /apiLogs/delete");
        Response<ApiLogsDto> response = controllerFactory.delete(apiLogsBusiness, request, FunctionalityEnum.DELETE_API_LOGS);
		log.info("end method /apiLogs/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<ApiLogsDto> getByCriteria(@RequestBody Request<ApiLogsDto> request) {
    	log.info("start method /apiLogs/getByCriteria");
        Response<ApiLogsDto> response = controllerFactory.getByCriteria(apiLogsBusiness, request, FunctionalityEnum.VIEW_API_LOGS);
		log.info("end method /apiLogs/getByCriteria");
        return response;
    }

    @RequestMapping(value = "/exportApiLogs", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"})
    public ResponseEntity<byte[]> exportApiLogs(@RequestBody Request<ApiLogsDto> request) {
        log.info("Début de l'exportation des API Logs.");
        String languageID = (String) requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
        Locale locale = new Locale(languageID, "");
        try {
            // Récupérer les données
            Response<ApiLogsDto> response =controllerFactory.getByCriteria(apiLogsBusiness, request, FunctionalityEnum.VIEW_API_LOGS);
            if (response.isHasError() || response.getItems().isEmpty()) {
                throw new IllegalArgumentException("Aucune donnée trouvée pour les critères spécifiés.");
            }

            List<ApiLogsDto> items = response.getItems();
            String timestamp = DateUtils.getCurrentTimestamp();
            String filePath = System.getProperty("java.io.tmpdir") + "api_logs_" + timestamp + ".xlsx";

            exportBusiness.exportApiLogsToExcel(items, filePath);

            // Lire le fichier généré
            File file = new File(filePath);
            if (!file.exists()) {
                throw new IOException("Le fichier généré n'existe pas.");
            }

            byte[] fileContent = Files.readAllBytes(file.toPath());

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName());
            headers.add(HttpHeaders.CONTENT_TYPE, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

            return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            slf4jLogger.error("Erreur de validation : {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage().getBytes());
        } catch (Exception e) {
            slf4jLogger.error("Erreur inattendue : {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
