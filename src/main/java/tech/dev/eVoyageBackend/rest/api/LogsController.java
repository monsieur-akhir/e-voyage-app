

/*
 * Java controller for entity table logs 
 * Created on 2024-08-20 ( Time 10:24:21 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2017 Savoir Faire Linux. All Rights Reserved.
 */

package tech.dev.eVoyageBackend.rest.api;

import lombok.extern.java.Log;
import tech.dev.eVoyageBackend.business.*;
import tech.dev.eVoyageBackend.business.fact.BusinessFactory;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

/**
Controller for table "logs"
 * 
 * @author SFL Back-End developper
 *
 */
@Log
@CrossOrigin("*")
@RestController
@RequestMapping(value="/logs")
public class LogsController {

    @Autowired
    private ControllerFactory<LogsDto> controllerFactory;
    @Autowired
    private LogsBusiness logsBusiness;
    @Autowired private BusinessFactory businessFactory;
    @Autowired private ExceptionUtils exceptionUtils;
    @Autowired private HttpServletRequest requestBasic;
    @Autowired
    private ExportBusiness exportBusiness;

    private Logger slf4jLogger = LoggerFactory.getLogger(getClass());

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<LogsDto> create(@RequestBody Request<LogsDto> request) {
    	log.info("start method /logs/create");
        Response<LogsDto> response = controllerFactory.create(logsBusiness, request, FunctionalityEnum.CREATE_LOGS);
		log.info("end method /logs/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<LogsDto> update(@RequestBody Request<LogsDto> request) {
    	log.info("start method /logs/update");
        Response<LogsDto> response = controllerFactory.update(logsBusiness, request, FunctionalityEnum.UPDATE_LOGS);
		log.info("end method /logs/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<LogsDto> delete(@RequestBody Request<LogsDto> request) {
    	log.info("start method /logs/delete");
        Response<LogsDto> response = controllerFactory.delete(logsBusiness, request, FunctionalityEnum.DELETE_LOGS);
		log.info("end method /logs/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<LogsDto> getByCriteria(@RequestBody Request<LogsDto> request) {
    	log.info("start method /logs/getByCriteria");
        Response<LogsDto> response = controllerFactory.getByCriteria(logsBusiness, request, FunctionalityEnum.VIEW_LOGS);
		log.info("end method /logs/getByCriteria");
        return response;
    }
   
    @RequestMapping(value = "/getLogDetail", method = RequestMethod.POST, consumes = {"application/json"}, produces = "application/json")
    public Response<LogsDto> getLogDetail(@RequestBody Request<LogsDto> request) {
        log.info("Début méthode /getLogDetail");
    
        Response<LogsDto> response = new Response<>();
        String languageID = (String) requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
        Locale locale = new Locale(languageID, "");
    
        try {
            // Vérification des droits d'accès
            businessFactory.checkUserAccess(request, FunctionalityEnum.VIEW_LOGS, locale);
    
            response = logsBusiness.getLogDetail(request, locale);
    
            // Log les erreurs si présentes
            if (response.isHasError()) {
                slf4jLogger.info("Erreur| code: {} - message: {}", response.getStatus().getCode(), response.getStatus().getMessage());
            }
        } catch (Exception e) {
            exceptionUtils.EXCEPTION(response, locale, e);
        }
    
        log.info("Fin méthode /getLogDetail");
        return response;
    }

    @RequestMapping(value = "/exportLogs", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"})
    public ResponseEntity<byte[]> exportLogs(@RequestBody Request<LogsDto> request) {
        log.info("Début de l'exportation des Logs.");

        String languageID = (String) requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
        Locale locale = new Locale(languageID, "");

        try {
            // Récupérer les données
            Response<LogsDto> response = controllerFactory.getByCriteria(logsBusiness, request, FunctionalityEnum.VIEW_LOGS);
            if (response.isHasError() || response.getItems().isEmpty()) {
                throw new IllegalArgumentException("Aucune donnée trouvée pour les critères spécifiés.");
            }

            List<LogsDto> items = response.getItems();
            String timestamp = DateUtils.getCurrentTimestamp();
            String filePath = System.getProperty("java.io.tmpdir") + "logs_" + timestamp + ".xlsx";

            exportBusiness.exportLogsToExcel(items, filePath);

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
