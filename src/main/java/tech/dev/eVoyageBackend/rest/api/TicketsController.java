

/*
 * Java controller for entity table tickets 
 * Created on 2025-01-12 ( Time 17:39:58 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2017 Savoir Faire Linux. All Rights Reserved.
 */

package tech.dev.eVoyageBackend.rest.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import tech.dev.eVoyageBackend.business.fact.BusinessFactory;
import tech.dev.eVoyageBackend.utils.*;
import tech.dev.eVoyageBackend.utils.dto.*;
import tech.dev.eVoyageBackend.utils.contract.*;
import tech.dev.eVoyageBackend.utils.contract.Request;
import tech.dev.eVoyageBackend.utils.contract.Response;
import tech.dev.eVoyageBackend.utils.enums.FunctionalityEnum;
import tech.dev.eVoyageBackend.business.*;
import tech.dev.eVoyageBackend.rest.fact.ControllerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;


/**
Controller for table "tickets"
 * 
 * @author SFL Back-End developper
 *
 */
@Log
@CrossOrigin("*")
@RestController
@RequestMapping(value="/tickets")
public class TicketsController {

	@Autowired
    private ControllerFactory<TicketsDto> controllerFactory;

    @Autowired
    private BusinessFactory businessFactory;
	@Autowired
	private TicketsBusiness ticketsBusiness;

    @Autowired
    private FunctionalError functionalError;
    private static final Logger log = LoggerFactory.getLogger(TicketsController.class);

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<TicketsDto> create(@RequestBody Request<TicketsDto> request) {
    	log.info("start method /tickets/create");
        Response<TicketsDto> response = controllerFactory.create(ticketsBusiness, request, FunctionalityEnum.CREATE_TICKETS);
		log.info("end method /tickets/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<TicketsDto> update(@RequestBody Request<TicketsDto> request) {
    	log.info("start method /tickets/update");
        Response<TicketsDto> response = controllerFactory.update(ticketsBusiness, request, FunctionalityEnum.UPDATE_TICKETS);
		log.info("end method /tickets/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<TicketsDto> delete(@RequestBody Request<TicketsDto> request) {
    	log.info("start method /tickets/delete");
        Response<TicketsDto> response = controllerFactory.delete(ticketsBusiness, request, FunctionalityEnum.DELETE_TICKETS);
		log.info("end method /tickets/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<TicketsDto> getByCriteria(@RequestBody Request<TicketsDto> request) {
    	log.info("start method /tickets/getByCriteria");
        Response<TicketsDto> response = controllerFactory.getByCriteria(ticketsBusiness, request, FunctionalityEnum.VIEW_TICKETS);
		log.info("end method /tickets/getByCriteria");
        return response;
    }

    @RequestMapping(value = "/download-pdf", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/pdf"})
    public void downloadTicket(@RequestBody Request<DownloadTicketRequest> request, HttpServletResponse response, Locale locale) {
        log.info("Start method /tickets/download");

        try {
            // Vérification des droits d'accès utilisateur
            businessFactory.checkUserAccess(request, FunctionalityEnum.VIEW_TICKETS, locale);

            // Construction du chemin du fichier
            String filePath = request.getData().getFilePath();
            Path path = Paths.get(filePath).toAbsolutePath();
            if (!Files.exists(path)) {
                log.error("File not found: {}", filePath);
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("Fichier introuvable : " + filePath);
                return;
            }

            Resource resource = new UrlResource(path.toUri());
            if (!resource.exists() || !resource.isReadable()) {
                log.error("File not readable: {}", filePath);
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("Fichier illisible : " + filePath);
                return;
            }

            // Configuration des en-têtes de réponse pour le téléchargement
            response.setContentType("application/pdf");
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"");
            response.setHeader(HttpHeaders.CONTENT_LENGTH, String.valueOf(Files.size(path)));

            // Écriture du fichier dans le flux de réponse
            Files.copy(path, response.getOutputStream());
            response.flushBuffer();

            log.info("End method /tickets/download - Success for file: {}", filePath);
        } catch (AccessDeniedException e) {
            // Gestion des droits d'accès insuffisants
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            try {
                response.getWriter().write("Accès refusé : " + e.getMessage());
            } catch (IOException ioException) {
                log.error("Erreur lors de l'écriture du message d'accès refusé", ioException);
            }
            log.warn("Accès refusé", e);
        } catch (IOException e) {
            // Gestion des erreurs liées à l'accès ou au flux de sortie
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            try {
                response.getWriter().write("Erreur lors du téléchargement du fichier : " + e.getMessage());
            } catch (IOException ioException) {
                log.error("Erreur lors de l'écriture de l'erreur", ioException);
            }
            log.error("Erreur d'IO lors du téléchargement du fichier", e);
        } catch (Exception e) {
            // Gestion des erreurs inattendues
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            try {
                response.getWriter().write("Une erreur inattendue s'est produite : " + e.getMessage());
            } catch (IOException ioException) {
                log.error("Erreur lors de l'écriture du message d'erreur inattendue", ioException);
            }
            log.error("Erreur inattendue lors du téléchargement du fichier", e);
        }

        log.info("End method /tickets/download");
    }

    @PostMapping(value = "/control-qr", consumes = {"application/json"}, produces = {"application/json"})
    public Response<TicketValidationDto> controlQrCode(@RequestBody Request<TicketValidationRequestDto> request, Locale locale) {
        log.info("Start method /tickets/control-qr");

        Response<TicketValidationDto> response = new Response<>();

        try {
            // Vérification des droits d'accès utilisateur
            log.info("Vérification des droits d'accès utilisateur...");
            businessFactory.checkUserAccess(request, FunctionalityEnum.VIEW_TICKETS, locale);
            log.info("Accès utilisateur vérifié avec succès.");

            // Exécution du contrôle du QR Code
            log.info("Appel du business pour le contrôle du QR Code...");
            response = ticketsBusiness.controlQrCode(request, locale);
            log.info("Business exécuté avec succès.");

            // Vérification du statut de la réponse
            if (response.getStatus() != null && response.getStatus().getCode() != null) {
                if (!"800".equals(response.getStatus().getCode())) {
                    log.warn("Erreur lors du contrôle du QR Code : {}", response.getStatus().getMessage());
                    response.setHasError(true);
                    return response;
                }
            }

            log.info("End method /tickets/control-qr - Success");
            return response;

        } catch (RuntimeException e) {
            log.error("Erreur métier lors du contrôle du QR Code", e);
            response.setStatus(functionalError.UNEXPECTED_ERROR("Erreur métier lors du contrôle du QR Code", locale));
            response.setHasError(true);
            return response;

        } catch (Exception e) {
            log.error("Erreur inattendue lors du contrôle du QR Code", e);
            response.setStatus(functionalError.UNEXPECTED_ERROR("Erreur inattendue lors du contrôle du QR Code", locale));
            response.setHasError(true);
            return response;
        }
    }

}
