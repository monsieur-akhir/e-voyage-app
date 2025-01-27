

/*
 * Java controller for entity table user 
 * Created on 2024-08-20 ( Time 10:24:22 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2017 Savoir Faire Linux. All Rights Reserved.
 */

package tech.dev.eVoyageBackend.rest.api;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.java.Log;
import tech.dev.eVoyageBackend.business.*;
import tech.dev.eVoyageBackend.rest.fact.ControllerFactory;
import tech.dev.eVoyageBackend.utils.*;
import tech.dev.eVoyageBackend.utils.contract.*;
import tech.dev.eVoyageBackend.utils.dto.*;
import tech.dev.eVoyageBackend.utils.enums.FunctionalityEnum;

/**
Controller for table "user"
 * 
 * @author SFL Back-End developper
 *
 */
@Log
@CrossOrigin("*")
@RestController
@RequestMapping(value="/user")
public class UserController {

	@Autowired
    private ControllerFactory<UserDto> controllerFactory;
	@Autowired
	private UserBusiness userBusiness;
//	@Autowired
//	private JwtTokenProvider jwtTokenProvider;


	@Autowired
	private HttpServletRequest requestBasic;
	@Autowired
	private FunctionalError functionalError;
	@Autowired
	private ExceptionUtils exceptionUtils;

	private Logger slf4jLogger = LoggerFactory.getLogger(getClass());

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<UserDto> create(@RequestBody Request<UserDto> request) {
    	log.info("start method /user/create");
        Response<UserDto> response = controllerFactory.create(userBusiness, request, FunctionalityEnum.CREATE_USER);
		log.info("end method /user/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<UserDto> update(@RequestBody Request<UserDto> request) {
    	log.info("start method /user/update");
        Response<UserDto> response = controllerFactory.update(userBusiness, request, FunctionalityEnum.UPDATE_USER);
		log.info("end method /user/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<UserDto> delete(@RequestBody Request<UserDto> request) {
    	log.info("start method /user/delete");
        Response<UserDto> response = controllerFactory.delete(userBusiness, request, FunctionalityEnum.DELETE_USER);
		log.info("end method /user/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<UserDto> getByCriteria(@RequestBody Request<UserDto> request) {
    	log.info("start method /user/getByCriteria");
        Response<UserDto> response = controllerFactory.getByCriteria(userBusiness, request, FunctionalityEnum.VIEW_USER);
		log.info("end method /user/getByCriteria");
        return response;
    }

    @RequestMapping(value = "/connexion", method = RequestMethod.POST, consumes = { "application/json" }, produces = {
			"application/json;charset=UTF-8" })
	public Response<UserDto> connexion(@RequestBody Request<UserDto> request) {
		log.info("UserDto method /connexion/connexion");
		Response<UserDto> response = new Response<UserDto>();
		String languageID = (String) requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
		Locale locale = new Locale(languageID, "");
		try {
			response = Validate.validateObject(request, response, functionalError, locale);
			if (!response.isHasError()) {
				response = userBusiness.connexion(request, locale);
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

	@RequestMapping(value = "/validationOtp", method = RequestMethod.POST, consumes = {
			"application/json" }, produces = { "application/json" })
	public Response<UserDto> validateNumberCodeOtp(@RequestBody Request<UserDto> request) {
		log.info("UserDto method /connexion/connexion");
		Response<UserDto> response = new Response<UserDto>();
		String languageID = (String) requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
		Locale locale = new Locale(languageID, "");
		try {
			response = Validate.validateObject(request, response, functionalError, locale);
			if (!response.isHasError()) {
				response = userBusiness.validateNumberCodeOtp(request, locale);
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

	@RequestMapping(value = "/validateInscription", method = RequestMethod.POST, consumes = {
			"application/json" }, produces = { "application/json" })
	public Response<UserDto> validateInscription(@RequestBody Request<UserDto> request) {
		log.info("UserDto method /connexion/validateInscription");
		Response<UserDto> response = new Response<UserDto>();
		String languageID = (String) requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
		Locale locale = new Locale(languageID, "");
		try {
			response = Validate.validateList(request, response, functionalError, locale);
			if (!response.isHasError()) {
				response = userBusiness.validationInscription(request, locale);
			} else {
				slf4jLogger.info("Erreur| code: {} -  message: {}", response.getStatus().getCode(),
						response.getStatus().getMessage());
				return response;
			}
			if (!response.isHasError()) {
				slf4jLogger.info("end method validateInscription");
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

	@PostMapping(value = "/blockUser", consumes = { "application/json" }, produces = { "application/json" })
	public Response<UserDto> lock(@RequestBody Request<UserDto> request) {
		log.info("UserDto method /connexion/connexion");
		Response<UserDto> response = new Response<UserDto>();
		String languageID = (String) requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
		Locale locale = new Locale(languageID, "");
		try {
			response = Validate.validateList(request, response, functionalError, locale);
			if (!response.isHasError()) {
				response = userBusiness.lock(request, locale);
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

	@PostMapping(value = "/unBlockUser", consumes = { "application/json" }, produces = { "application/json" })
	public Response<UserDto> unlock(@RequestBody Request<UserDto> request) {
		log.info("UserDto method /connexion/unBlockUser");
		Response<UserDto> response = new Response<UserDto>();
		String languageID = (String) requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
		Locale locale = new Locale(languageID, "");
		try {
			response = Validate.validateList(request, response, functionalError, locale);
			if (!response.isHasError()) {
				response = userBusiness.unlock(request, locale);
			} else {
				slf4jLogger.info("Erreur| code: {} -  message: {}", response.getStatus().getCode(),
						response.getStatus().getMessage());
				return response;
			}
			if (!response.isHasError()) {
				slf4jLogger.info("end method unBlock");
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

	@RequestMapping(value = "/logOut", method = RequestMethod.POST, consumes = { "application/json" }, produces = {
			"application/json" })
	public Response<UserDto> logOut(@RequestBody Request<UserDto> request) {
		log.info("start method /user/logOut");
		Response<UserDto> response = new Response<UserDto>();
		String languageID = (String) requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
		Locale locale = new Locale(languageID, "");
		try {
			response = Validate.validateObject(request, response, functionalError, locale);
			if (!response.isHasError()) {
				response = userBusiness.logOut(request, locale);
			} else {
				slf4jLogger.info("Erreur| code: {} -  message: {}", response.getStatus().getCode(),
						response.getStatus().getMessage());
				return response;
			}
			if (!response.isHasError()) {
				slf4jLogger.info("end method logOut");
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
		slf4jLogger.info("end method /user/logOut");
		return response;
	}

	@RequestMapping(value = "/getSessionUser", method = RequestMethod.POST, consumes = {
			"application/json" }, produces = { "application/json" })
	public Response<UserDto> getSessionUser(@RequestBody Request<UserDto> request) {
		log.info("start method /user/getSessionUser");
		Response<UserDto> response = new Response<UserDto>();
		String languageID = (String) requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
		Locale locale = new Locale(languageID, "");
		try {
			response = Validate.validateObject(request, response, functionalError, locale);
			if (!response.isHasError()) {
				response = userBusiness.getSessionUser(request, locale);
			} else {
				slf4jLogger.info("Erreur| code: {} -  message: {}", response.getStatus().getCode(),
						response.getStatus().getMessage());
				return response;
			}
			if (!response.isHasError()) {
				slf4jLogger.info("end method getSessionUser");
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
		slf4jLogger.info("end method /user/getSessionUser");
		return response;
	}

	@RequestMapping(value = "/deleteKeys", method = RequestMethod.POST, consumes = { "application/json" }, produces = {
			"application/json" })
	public Response<Map<String, Object>> deleteKeys(@RequestBody Request<UserDto> request) {
		slf4jLogger.info("start method /user/deleteKeys");
		Response<Map<String, Object>> response = new Response<>();

		String languageID = (String) requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
		Locale locale = new Locale(languageID, "");

		try {
			response = userBusiness.deleteKeys(locale);

			if (!response.isHasError()) {
				slf4jLogger.info("end method deleteKeys");
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
		slf4jLogger.info("end method /user/deleteKeys");
		return response;
	}

	@RequestMapping(value = "/getUserSessionTTL", method = RequestMethod.POST, consumes = {
			"application/json" }, produces = { "application/json" })
	public Response<UserDto> getUserSessionTTL(@RequestBody Request<UserDto> request) throws Exception {
		log.info("start method /user/getUserSessionTTL");
		Response<UserDto> response = new Response<UserDto>();
		String languageID = (String) requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
		Locale locale = new Locale(languageID, "");
		try {
			if (!response.isHasError()) {
				response = userBusiness.getUserSessionTTL(request, locale);
			} else {
				log.info(String.format("Erreur| code: {} -  message: {}", response.getStatus().getCode(),
						response.getStatus().getMessage()));
				return response;
			}
			if (!response.isHasError()) {
				log.info(String.format("code: {} -  message: {}", StatusCode.SUCCESS, StatusMessage.SUCCESS));
			} else {
				log.info(String.format("Erreur| code: {} -  message: {}", response.getStatus().getCode(),
						response.getStatus().getMessage()));
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
		log.info("end method getUserSessionTTL");
		return response;
	}
	
//	    public Response<JwtAuthenticationDto> login(@RequestBody Request<LoginRequestDto> request) {
//	        log.info("----begin login User via JWT-----");
//	        Response<JwtAuthenticationDto> response = userBusiness.connexionJwt(request, Locale.getDefault()); 
//	        log.info("----end login User via JWT-----");
//	        return response;
//	    }

	/**
	 * Envoi d'un OTP pour le changement de mot de passe.
	 */
	@PostMapping(value = "/sendOtp", consumes = "application/json")
	public Response<UserDto> sendOtp(@RequestBody Request<UserDto> request) {
		log.info("UserDto method /sendOtp");
		Response<UserDto> response = new Response<>();
		String languageID = (String) requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
		Locale locale = new Locale(languageID, "");

		try {
			// Validation du corps de la requête
			response = Validate.validateObject(request, response, functionalError, locale);
			if (!response.isHasError()) {
				response = userBusiness.sendOtpForPasswordChange(request, locale);
			} else {
				slf4jLogger.info("Erreur| code: {} -  message: {}", response.getStatus().getCode(),
						response.getStatus().getMessage());
				return response;
			}

			if (!response.isHasError()) {
				slf4jLogger.info("end method sendOtp");
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

		slf4jLogger.info("end method /sendOtp");
		return response;
	}

	/**
	 * Changement de mot de passe après validation de l'OTP.
	 */
	@PostMapping(value = "/changePassword", consumes = "application/json")
	public Response<UserDto> changePassword(@RequestBody Request<UserDto> request) {
		log.info("UserDto method /changePassword");
		Response<UserDto> response = new Response<>();
		String languageID = (String) requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
		Locale locale = new Locale(languageID, "");

		try {
			// Validation du corps de la requête
			response = Validate.validateObject(request, response, functionalError, locale);
			if (!response.isHasError()) {
				response = userBusiness.changePasswordWithOtp(request, locale);
			} else {
				slf4jLogger.info("Erreur| code: {} -  message: {}", response.getStatus().getCode(),
						response.getStatus().getMessage());
				return response;
			}

			if (!response.isHasError()) {
				slf4jLogger.info("end method changePassword");
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

		slf4jLogger.info("end method /changePassword");
		return response;
	}

}
