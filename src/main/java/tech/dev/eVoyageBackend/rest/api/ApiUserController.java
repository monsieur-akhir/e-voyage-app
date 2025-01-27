

/*
 * Java controller for entity table api_user 
 * Created on 2024-11-16 ( Time 14:33:28 )
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

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

/**
Controller for table "api_user"
 * 
 * @author SFL Back-End developper
 *
 */
@Log
@CrossOrigin("*")
@RestController
@RequestMapping(value="/api/auth")
public class ApiUserController {

	@Autowired 
    private ControllerFactory<ApiUserDto> controllerFactory;
	@Autowired 
    private ApiUserBusiness apiUserBusiness;

    @Autowired 
    private ExceptionUtils exceptionUtils;
    @Autowired 
    private HttpServletRequest requestBasic;
    @Autowired
	private FunctionalError functionalError;

    private Logger slf4jLogger = LoggerFactory.getLogger(getClass());

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<ApiUserDto> create(@RequestBody Request<ApiUserDto> request) {
    	log.info("start method /apiUser/create");
        Response<ApiUserDto> response = controllerFactory.create(apiUserBusiness, request, FunctionalityEnum.CREATE_API_USER);
		log.info("end method /apiUser/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<ApiUserDto> update(@RequestBody Request<ApiUserDto> request) {
    	log.info("start method /apiUser/update");
        Response<ApiUserDto> response = controllerFactory.update(apiUserBusiness, request, FunctionalityEnum.UPDATE_API_USER);
		log.info("end method /apiUser/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<ApiUserDto> delete(@RequestBody Request<ApiUserDto> request) {
    	log.info("start method /apiUser/delete");
        Response<ApiUserDto> response = controllerFactory.delete(apiUserBusiness, request, FunctionalityEnum.DELETE_API_USER);
		log.info("end method /apiUser/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<ApiUserDto> getByCriteria(@RequestBody Request<ApiUserDto> request) {
    	log.info("start method /apiUser/getByCriteria");
        Response<ApiUserDto> response = controllerFactory.getByCriteria(apiUserBusiness, request, FunctionalityEnum.VIEW_API_USER);
		log.info("end method /apiUser/getByCriteria");
        return response;
    }

    @RequestMapping(value = "/generate-token", method = RequestMethod.POST, consumes = { "application/json" }, produces = {
			"application/json;charset=UTF-8" })
	public Response<ApiUserDto> connexion(@RequestBody JsonNode requestData) {
		log.info("UserDto method /connexion/connexion");
		Response<ApiUserDto> response = new Response<ApiUserDto>();
		String languageID = (String) requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
		Locale locale = new Locale(languageID, "");
		Request<ApiUserDto> request = new Request<>();
		ApiUserDto apiUser = new ApiUserDto();
		apiUser.setLogin(requestData.get("login").asText());
		apiUser.setPassword(requestData.get("password").asText());
		request.setData(apiUser);
		try {
			response = Validate.validateObject(request, response, functionalError, locale);
			if (!response.isHasError()) {
				response = apiUserBusiness.connexion(request, locale);
			} else {
				slf4jLogger.info("Erreur| code: {} -  message: {}", response.getStatus().getCode(),
						response.getStatus().getMessage());
				return response;
			}
			if (!response.isHasError()) {
				slf4jLogger.info("end method connexion");
				slf4jLogger.info("code: {} -  message: {}", StatusCode.SUCCESS, StatusMessage.SUCCESS);
			} else {
				slf4jLogger.info("Erreur| code: {} -  message: {}", response.getStatus().getCode(),
						response.getStatus().getMessage());
			}
		} catch (CannotCreateTransactionException e) {
			exceptionUtils.CANNOT_CREATE_TRANSACTION_EXCEPTION(response, locale, e);
		} catch (TransactionSystemException e) {
			exceptionUtils.TRANSACTION_SYSTEM_EXCEPTION(response, locale, e);
		} catch (RuntimeException e) {
			exceptionUtils.RUNTIME_EXCEPTION(response, locale, e);
		} catch (Exception e) {
			exceptionUtils.EXCEPTION(response, locale, e);
		}
		slf4jLogger.info("end method /UserDto/connexion");
		return response;
	}

}
